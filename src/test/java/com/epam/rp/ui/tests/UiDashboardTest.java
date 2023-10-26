package com.epam.rp.ui.tests;

import com.epam.rp.ui.pages.DashboardPage;
import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.util.Properties;

public class UiDashboardTest extends UiBaseTest {

    private String defaultUserName;
    private String defaultUserPassword;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private WebDriverWait wait;
    private String dashboardsPageUrl;

    @BeforeMethod
    @BeforeEach
    public void setUpTest() {
        Properties props = ConfigReader.readProperties();
        defaultUserName = props.getProperty("testUserName");
        defaultUserPassword = props.getProperty("testUserPassword");
        wait = new WebDriverWait(driver, 5);
        dashboardsPageUrl = baseUrl + "/ui/#" + projectName + "/dashboard";

        driver.get(baseUrl);
        dashboardPage = new DashboardPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.login(defaultUserName, defaultUserPassword);
        wait.until(ExpectedConditions.urlContains("dashboard"));

        if (!driver.getCurrentUrl().equalsIgnoreCase(dashboardsPageUrl)) {
            driver.get(dashboardsPageUrl);
        }
    }

    @Test(description = "UI: Update Dashboard name")
    @org.junit.jupiter.api.Test
    public void testUpdateDashboardName() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dashboardPage.searchField)));
        dashboardPage.selectFirstDashboard();
        dashboardPage.updateDashboardName("NEW NAME hehzheh2");
        Thread.sleep(2000);
        Assert.assertTrue(dashboardPage.getDashboardTitle().equalsIgnoreCase("NEW NAME hehz2"));

        Allure.addAttachment("Login screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}

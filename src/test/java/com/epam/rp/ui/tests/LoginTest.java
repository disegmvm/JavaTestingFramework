package com.epam.rp.ui.tests;

import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class LoginTest extends UiBaseTest {

    private String defaultUserName;
    private String defaultUserPassword;
    private String baseUrl;
    private LoginPage loginPage;
    private WebDriverWait wait;

    @BeforeMethod
    @BeforeEach
    public void setUpTest() {
        Properties props = ConfigReader.readProperties();
        baseUrl = props.getProperty("baseUrl");
        defaultUserName = props.getProperty("defaultUserName");
        defaultUserPassword = props.getProperty("defaultUserPassword");
    }

    @Test(description = "UI: Default user login")
    @org.junit.jupiter.api.Test
    public void testLogin() {
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, 5);
        driver.get(baseUrl);

        loginPage.login(defaultUserName, defaultUserPassword);
        String dashboardPage = String.format("%s/ui/#default_personal/dashboard", baseUrl);
        wait.until(ExpectedConditions.urlToBe(dashboardPage));
        assertEquals(dashboardPage, driver.getCurrentUrl());

        Allure.addAttachment("Login screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}

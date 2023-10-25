package com.epam.rp.ui.tests;

import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

import static org.testng.Assert.assertEquals;

@Feature("Login")
public class LoginTest extends UiBaseTest {

    private String defaultUserName;
    private String defaultUserPassword;
    private String baseUrl;

    @BeforeMethod
    public void setUpTest() {
        Properties props = ConfigReader.readProperties();
        baseUrl = props.getProperty("baseUrl");
        defaultUserName = props.getProperty("defaultUserName");
        defaultUserPassword = props.getProperty("defaultUserPassword");
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get(baseUrl);
        loginPage.login(defaultUserName, defaultUserPassword);

        String dashboardPage = String.format("%s/ui/#default_personal/dashboard", baseUrl);
        wait.until(ExpectedConditions.urlToBe(dashboardPage));
        assertEquals(dashboardPage, driver.getCurrentUrl());
    }
}

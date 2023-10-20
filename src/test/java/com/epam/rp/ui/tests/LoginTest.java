package com.epam.rp.ui.tests;

import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Login")
public class LoginTest {

    private WebDriver driver;
    private String defaultUserName;
    private String defaultUserPassword;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        Properties props = ConfigReader.readProperties();
        baseUrl = props.getProperty("baseUrl");
        defaultUserName = props.getProperty("defaultUserName");
        defaultUserPassword = props.getProperty("defaultUserPassword");

        driver.get(baseUrl);
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        loginPage.login(defaultUserName, defaultUserPassword);

        String dashboardPage = String.format("%s/ui/#default_personal/dashboard", baseUrl);
        wait.until(ExpectedConditions.urlToBe(dashboardPage));
        assertEquals(dashboardPage, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

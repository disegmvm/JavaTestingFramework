package com.epam.rp.ui.tests;

import com.epam.rp.utils.ConfigReader;
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Denis_Peganov\\drivers\\chromedriver-118.exe");
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
        WebDriverWait wait = new WebDriverWait(driver, 5);

        WebElement usernameField = driver.findElement(By.name("login"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='loginForm__login-button-container--1mHGW']//button[@type='submit']")));

        usernameField.sendKeys(defaultUserName);
        passwordField.sendKeys(defaultUserPassword);

        loginButton.click();

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

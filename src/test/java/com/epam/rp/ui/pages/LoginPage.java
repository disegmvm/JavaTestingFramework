package com.epam.rp.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By usernameField = By.name("login");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//div[@class='loginForm__login-button-container--1mHGW']//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        if(driver == null) {
            throw new RuntimeException("Driver passed to LoginPage is null!");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

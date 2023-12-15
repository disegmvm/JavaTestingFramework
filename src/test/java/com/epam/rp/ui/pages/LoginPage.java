package com.epam.rp.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    // Selenide selectors
    private SelenideElement usernameField = $(By.name("login"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement loginButton = $x("//div[@class='loginForm__login-button-container--KT9g6']//button[@type='submit']");

    public void enterUsername(String username) {
        usernameField.shouldBe(visible).setValue(username);
    }

    public void enterPassword(String password) {
        passwordField.shouldBe(visible).setValue(password);
    }

    public void clickLogin() {
        loginButton.shouldBe(enabled).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
}

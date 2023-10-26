package com.epam.rp.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public By searchField = By.xpath("//input[@placeholder='Search by name']");
    public By editButton = By.xpath("//span[text()='Edit']");
    private final By firstDashboard = By.xpath("(//a[@class='gridCell__grid-cell--EIqeC gridCell__align-left--DFXWN dashboardTable__name--t2a89'])[1]");

    public By currentDashboardTitle = By.xpath("//li[@class='pageBreadcrumbs__page-breadcrumbs-item--JhXKI']/span");

    By updateButton = By.xpath("//button[text()='Update']");
    public By editDashboardNameField = By.xpath("//input[@placeholder='Enter dashboard name']");

    public DashboardPage(WebDriver driver) {
        if (driver == null) {
            throw new RuntimeException("Driver passed to DashboardPage is null!");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    public void searchDashboard(String dashboardName) {
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(searchField)));
        driver.findElement(searchField).sendKeys(dashboardName);
    }

    public void selectDashboard(String dashboardName) {
        String textLocatorTemplate = "//div[@class='gridRow__grid-row--X9wIq']/a[text()='%s']";
        driver.findElement(By.xpath(String.format(textLocatorTemplate, dashboardName))).click();
    }

    public String getDashboardTitle() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentDashboardTitle));
        return driver.findElement(currentDashboardTitle).getText();
    }

    public void selectFirstDashboard() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstDashboard));
        driver.findElement(firstDashboard).click();
    }

    public void updateDashboardName(String newDashboardName) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(editButton))).click();
        WebElement editDashboardNameFieldWebElement = driver.findElement(editDashboardNameField);
        wait.until(ExpectedConditions.elementToBeClickable(editDashboardNameFieldWebElement)).click();
        editDashboardNameFieldWebElement.clear();
        editDashboardNameFieldWebElement.sendKeys(newDashboardName);
        driver.findElement(updateButton).click();
    }
}

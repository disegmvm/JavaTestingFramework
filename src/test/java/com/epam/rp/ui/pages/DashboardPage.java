package com.epam.rp.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DashboardPage {
    private SelenideElement searchField = $x("//input[@placeholder='Search by name']");
    private SelenideElement editButton = $x("//span[text()='Edit']");
    private SelenideElement deleteButton = $x("//button[@type='button']/span[text()='Delete']");
    private SelenideElement confirmDeleteButton = $x("//button[@class='bigButton__big-button--BmG4Q bigButton__color-tomato--jXOiC']");
    public ElementsCollection confirmationNotifications = $$x("//div[@class='notificationItem__message-container--eN8Rd notificationItem__success--Yvo7V']");
    private SelenideElement firstDashboard = $x("(//a[@class='gridCell__grid-cell--EIqeC gridCell__align-left--DFXWN dashboardTable__name--t2a89'])[1]");
    private SelenideElement currentDashboardTitle = $x("//li[@class='pageBreadcrumbs__page-breadcrumbs-item--JhXKI']/span");
    private SelenideElement addWidgetButton = $x("//button[@class='ghostButton__ghost-button--r7c9T ghostButton__color-topaz--Z_OvX with-icon ghostButton__filled-icon--HoBWw ghostButton__mobile-minified--d60VQ']/span[text()='Add new widget']");
    private SelenideElement deleteWidgetButton = $x("(//div[@class='widgetHeader__control--SQilp widgetHeader__mobile-hide--CFUwl'])[2]");
    private SelenideElement widgetHeader = $x("//div[@class='widgetHeader__info-block--Lp75m']");
    private SelenideElement overallStatisticsSelectButton = $x("//input[@value='overallStatistics']/following-sibling::span");
    private SelenideElement nextStepButton = $x("//i[@class='ghostButton__icon--Y8b6g ghostButton__icon-at-right--_kyf_']");
    private SelenideElement selectFilterButton = $x("//span[@class='inputRadio__toggler--ygpdQ inputRadio__at-top--NPpmC inputRadio__mode-default--VD2jF inputRadio__toggler-medium--iSkOd']");
    private String createdDashboardTitleWebElementTemplate = "//span[@title='%s']";
    private SelenideElement updateButton = $x("//button[text()='Update']");
    private SelenideElement editDashboardNameField = $x("//input[@placeholder='Enter dashboard name']");
    private SelenideElement editDashboardDescField = $x("//textarea[@class='inputTextArea__input-text-area--N0goa']");
    private SelenideElement addDashboardButton = $x("//span[@class='ghostButton__text--SjHtK']");
    private SelenideElement addButton = $x("//button[@class='bigButton__big-button--BmG4Q bigButton__color-booger--EpRlL']");

    public void searchDashboard(String dashboardName) {
        searchField.shouldBe(enabled).setValue(dashboardName);
    }

    public void selectDashboard(String dashboardName) {
        $x(String.format("//div[@class='gridRow__grid-row--X9wIq']/a[text()='%s']", dashboardName)).click();
    }

    public void clickAddDashboard(){
        addDashboardButton.shouldBe(visible, Duration.ofSeconds(10)).shouldBe(enabled);
        addDashboardButton.click();
    }

    public void clickAddWidget(){
        addWidgetButton.shouldBe(visible).shouldBe(enabled);
        addWidgetButton.click();
    }

    public void deleteFirstWidget(){
        widgetHeader.hover();
        deleteWidgetButton.shouldBe(visible);
        deleteWidgetButton.click();
        confirmDeleteButton.shouldBe(visible);

        // Using JavaScript to click the delete button
        executeJavaScript("arguments[0].click();", confirmDeleteButton);
    }

    public void moveWidgetDown(){
        widgetHeader.shouldBe(visible).shouldBe(enabled);
        Actions actions = new Actions(getWebDriver());
        actions.dragAndDropBy(widgetHeader.toWebElement(), 0, 500).perform();
    }

    public String getTopWidgetTitle() {
        widgetHeader.shouldBe(visible).shouldBe(enabled);
        String fullTitle = widgetHeader.getText();
        return fullTitle.split("\\s+")[0];
    }


    public void selectOverallStatisticsWidget(){
        overallStatisticsSelectButton.shouldBe(visible);
        overallStatisticsSelectButton.click();
    }

    public void clickNextStepButton(){
        nextStepButton.shouldBe(visible);
        nextStepButton.click();
    }

    public void selectFirstFilter(){
        selectFilterButton.shouldBe(visible);
        selectFilterButton.click();
    }

    public void clickAddButton(){
        addButton.shouldBe(visible);
        addButton.click();
    }

    public void createDashboard(String dbName) {
        clickAddDashboard();
        editDashboardNameField.clear();
        editDashboardNameField.setValue(dbName);
        addButton.click();
    }

    public String getDashboardTitle() {
        return currentDashboardTitle.shouldBe(visible).getText();
    }

    public String findDashboardTitle(String dbName) {
        return $x(String.format(createdDashboardTitleWebElementTemplate, dbName)).shouldBe(visible).getText();
    }

    public void selectFirstDashboard() {
        firstDashboard.shouldBe(visible).click();
    }

    public void clickEditDashboardBtn(){
        editButton.shouldBe(enabled).click();
    }

    public void clickDeleteDashboardBtn(){
        deleteButton.shouldBe(enabled).click();
    }

    public void clickConfirmDeleteDashboardBtn(){
        confirmDeleteButton.shouldBe(enabled).click();
    }

    public void updateDashboardName(String newDashboardName) {
        clickEditDashboardBtn();
        // Implicit waits are automatically handled in Selenide, however here I set it explicitly
        editDashboardNameField.shouldBe(visible, Duration.ofSeconds(10)).shouldBe(enabled);
        editDashboardNameField.click();
        editDashboardNameField.clear();
        editDashboardNameField.setValue(newDashboardName);
        clickUpdateDashboardBtn();
    }

    public void updateDashboardDesc(String newDashboardDesc){
        editDashboardDescField.shouldBe(enabled).click();
        editDashboardDescField.clear();
        editDashboardDescField.setValue(newDashboardDesc);
    }

    public void clickUpdateDashboardBtn(){
        updateButton.click();
    }
}

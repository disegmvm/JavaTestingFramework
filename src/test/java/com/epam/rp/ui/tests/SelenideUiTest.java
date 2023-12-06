package com.epam.rp.ui.tests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.epam.rp.ui.pages.DashboardPage;
import com.epam.rp.ui.pages.LoginPage;
import com.epam.rp.utils.ConfigReader;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@ExtendWith(ScreenShooterExtension.class)
public class SelenideUiTest extends UiBaseTest{

    private DashboardPage dashboardPage = page(DashboardPage.class);
    private LoginPage loginPage = page(LoginPage.class);

    @BeforeMethod
    public void setUpTest() {
        Properties props = ConfigReader.readProperties();
        String defaultUserName = props.getProperty("testUserName");
        String defaultUserPassword = props.getProperty("testUserPassword");
        String dashboardsPageUrl = baseUrl + "/ui/#" + projectName + "/dashboard";
        open(baseUrl);
        getWebDriver().manage().window().maximize();
        loginPage.login(defaultUserName, defaultUserPassword);

        if (!url().equalsIgnoreCase(dashboardsPageUrl)) {
            open(dashboardsPageUrl);
        }
    }

    @AfterMethod
    public void tearDownTest() {
        getWebDriver().close();
    }

    @Test
    public void testCreateDashboard() {
        String randomName = "Random dashboard name " + UUID.randomUUID();
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.createDashboard(randomName);

        Assert.assertTrue(dashboardPage.findDashboardTitle(randomName).equalsIgnoreCase(randomName));
    }

    @Test
    public void testUpdateDashboardName() {
        String randomName = "Random dashboard name " + UUID.randomUUID();
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.selectFirstDashboard();
        dashboardPage.updateDashboardName(randomName);
        sleep(1000);

        Assert.assertTrue(dashboardPage.getDashboardTitle().equalsIgnoreCase(randomName));
    }

    @Test
    public void testDeleteDashboard() {
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.selectFirstDashboard();
        dashboardPage.clickDeleteDashboardBtn();
        dashboardPage.clickConfirmDeleteDashboardBtn();
        List<String> confirmationMessages = new ArrayList<>();
        sleep(1000); // This is needed to collect all the notifications

        for (SelenideElement element : dashboardPage.confirmationNotifications) {
            confirmationMessages.add(element.getText());
        }

        Assert.assertTrue(confirmationMessages.contains("Dashboard has been deleted"));
    }

    @Test
    public void testAddWidgetToDashboard() {
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.selectFirstDashboard();
        dashboardPage.clickAddWidget();
        dashboardPage.selectOverallStatisticsWidget();
        dashboardPage.clickNextStepButton();
        dashboardPage.selectFirstFilter();
        dashboardPage.clickNextStepButton();
        dashboardPage.clickAddButton();
        List<String> confirmationMessages = new ArrayList<>();
        sleep(1000); // This is needed to collect all the notifications

        for (SelenideElement element : dashboardPage.confirmationNotifications) {
            confirmationMessages.add(element.getText());
        }

        Assert.assertTrue(confirmationMessages.contains("Widget has been added"));
    }

    @Test
    public void testMoveDashboardsWidgets() {
        String randomName = "Random dashboard name " + UUID.randomUUID();
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.createDashboard(randomName);
        dashboardPage.clickAddWidget();
        dashboardPage.selectOverallStatisticsWidget();
        dashboardPage.clickNextStepButton();
        dashboardPage.selectFirstFilter();
        dashboardPage.clickNextStepButton();
        dashboardPage.clickAddButton();
        dashboardPage.clickAddWidget();
        dashboardPage.selectOverallStatisticsWidget();
        dashboardPage.clickNextStepButton();
        dashboardPage.selectFirstFilter();
        dashboardPage.clickNextStepButton();
        dashboardPage.clickAddButton();
        String topWidgetTitle = dashboardPage.getTopWidgetTitle();
        dashboardPage.moveWidgetDown();

        Assert.assertFalse(dashboardPage.getTopWidgetTitle().equalsIgnoreCase(topWidgetTitle));
    }

    @Test
    public void testDeleteDashboardsWidget() {
        String randomName = "Random dashboard name " + UUID.randomUUID();
        open(baseUrl + "/ui/#" + projectName + "/dashboard");
        dashboardPage.createDashboard(randomName);
        dashboardPage.clickAddWidget();
        dashboardPage.selectOverallStatisticsWidget();
        dashboardPage.clickNextStepButton();
        dashboardPage.selectFirstFilter();
        dashboardPage.clickNextStepButton();
        dashboardPage.clickAddButton();
        dashboardPage.deleteFirstWidget();
        List<String> confirmationMessages = new ArrayList<>();
        sleep(1000); // This is needed to collect all the notifications

        for (SelenideElement element : dashboardPage.confirmationNotifications) {
            confirmationMessages.add(element.getText());
        }

        Assert.assertTrue(confirmationMessages.contains("Widget has been deleted"));
    }
}

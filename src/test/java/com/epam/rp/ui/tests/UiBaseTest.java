package com.epam.rp.ui.tests;

import com.epam.rp.common.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UiBaseTest extends BaseTest {
    protected WebDriver driver;

    @BeforeScenario
    @BeforeMethod
    @BeforeEach
    public void setup() {
        commonSetUp();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterScenario
    @AfterMethod
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        commonTearDown();
    }
}


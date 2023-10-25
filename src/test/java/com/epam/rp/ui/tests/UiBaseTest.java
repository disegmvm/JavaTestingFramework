package com.epam.rp.ui.tests;

import com.epam.rp.common.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import java.nio.file.StandardCopyOption;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UiBaseTest extends BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            takeScreenshot(result);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        if (driver != null) {
            driver.quit();
        }
        commonTearDown();
    }

    public void takeScreenshot(ITestResult result) {
        if (driver != null) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);

                File directory = new File("target/screenshots");
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                Path destinationPath = Paths.get("target/screenshots", result.getName() + ".png");

                if (Files.exists(destinationPath)) {
                    Files.delete(destinationPath);
                }

                Files.copy(source.toPath(), destinationPath);

                System.out.println("Screenshot taken for test case: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("Driver is null, skipping screenshot.");
        }
    }
}


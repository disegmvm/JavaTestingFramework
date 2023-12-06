package com.epam.rp.ui.tests;

import com.epam.rp.common.BaseTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UiBaseTest extends BaseTest {

    @BeforeTest
    public void setup() {
        commonSetUp();
    }

    @AfterTest
    public void tearDown() {
        commonTearDown();
        closeWebDriver();
    }
}


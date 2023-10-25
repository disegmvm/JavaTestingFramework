package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ApiBaseTest extends BaseTest {

    @BeforeSuite
    @BeforeEach
    public void setUpSuite() {
        commonSetUp();
    }

    @AfterSuite
    @AfterEach
    public void tearDownSuite() {
        commonTearDown();
    }
}

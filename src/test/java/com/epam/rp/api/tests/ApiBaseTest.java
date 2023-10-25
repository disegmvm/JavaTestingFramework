package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ApiBaseTest extends BaseTest {

    @BeforeSuite
    public void setUpSuite() {
        commonSetUp();
        // Additional setup for API tests
    }

    @AfterSuite
    public void tearDownSuite() {
        // Additional teardown for API tests
        commonTearDown();
    }
}

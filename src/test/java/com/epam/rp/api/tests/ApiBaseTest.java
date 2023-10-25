package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ApiBaseTest extends BaseTest {

    @BeforeSuite
    public void setUpSuite() {
        commonSetUp();
    }

    @AfterSuite
    public void tearDownSuite() {
        commonTearDown();
    }
}

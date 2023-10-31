package com.epam.rp.api.tests;

import com.epam.rp.common.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class ApiBaseTest extends BaseTest {

    protected static String apiToken;

    @BeforeSuite
    @BeforeEach
    public void setUpSuite() {
        commonSetUp();
        apiToken = props.getProperty("apiToken");
    }

    @AfterSuite
    @AfterEach
    public void tearDownSuite() {
        commonTearDown();
    }
}

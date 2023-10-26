package com.epam.rp.common;

import com.epam.rp.utils.ConfigReader;
import java.util.Properties;

public class BaseTest {
    protected static Properties props;
    protected static String projectName;
    protected static String baseUrl;

    // Common setup for both API and UI tests
    public void commonSetUp() {
        props = ConfigReader.readProperties();
        projectName = props.getProperty("rpProjectName");
        baseUrl = props.getProperty("baseUrl");
    }

    // Common teardown for both API and UI tests
    public void commonTearDown() {
    }
}

package com.epam.rp.common;

import com.epam.rp.utils.ConfigReader;
import java.util.Properties;

public class BaseTest {
    protected static Properties props;

    // Common setup for both API and UI tests
    public void commonSetUp() {
        props = ConfigReader.readProperties();
    }

    // Common teardown for both API and UI tests
    public void commonTearDown() {
    }
}

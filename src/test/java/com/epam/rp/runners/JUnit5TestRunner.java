package com.epam.rp.runners;

import com.epam.rp.api.tests.ApiUserTest;
import com.epam.rp.ui.tests.LoginTest;
import org.junit.platform.suite.api.*;

@SelectClasses({LoginTest.class, ApiUserTest.class})
@Suite
@SuiteDisplayName("A JUnit 5 Test Runner")
public class JUnit5TestRunner {
}



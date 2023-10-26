package com.epam.rp.runners;

import com.epam.rp.api.tests.ApiUserTest;
import com.epam.rp.ui.tests.UiLoginTest;
import org.junit.platform.suite.api.*;

//@SelectClasses({UiLoginTest.class, ApiUserTest.class})
@SelectPackages("com.epam.rp.ui.tests")
@Suite
@SuiteDisplayName("A JUnit 5 Test Runner")
public class JUnit5TestRunner {
}



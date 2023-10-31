package com.epam.rp.runners;

import com.epam.rp.api.tests.ApiDashboardTest;
import com.epam.rp.ui.tests.UiDashboardTest;
import org.junit.platform.suite.api.*;

@SelectClasses({ApiDashboardTest.class, UiDashboardTest.class})
@Suite
@SuiteDisplayName("A JUnit 5 Test Runner")
public class JUnit5TestRunner {
}

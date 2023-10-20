package com.epam.rp.api.tests;

import com.epam.rp.utils.Logger;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

@Feature("SampleFeature")
public class ApiSampleTest {
    @Test
    public void testUserLogin() {
        Logger.logDebugMessage("ALLURE debug API test message");
    }
}

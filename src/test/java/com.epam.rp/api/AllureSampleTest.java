package com.epam.rp.api;

import com.epam.rp.utils.Logger;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;

@Feature("SampleFeature")
public class AllureSampleTest {
    @Test
    public void testUserLogin() {
        Logger.logDebugMessage("ALLURE debug API test message");
    }
}

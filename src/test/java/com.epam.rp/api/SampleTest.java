package com.epam.rp.api;

import com.epam.rp.Logger;
import org.junit.jupiter.api.Test;

public class SampleTest {

    public static void main(String[] args) {
        Logger.logDebugMessage("debug message");
    }

    @Test
    public void myTestMethod() {
        Logger.logDebugMessage("debug test message");
    }
}

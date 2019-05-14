package com.halas.drivers.helper;

import com.halas.drivers.WebDriverManager;
import com.halas.drivers.WebDriverWaitManager;

public class SetNullDrivers {
    public static void setNullOnAllDrivers() {
        WebDriverManager.setNullWebDriver();
        WebDriverWaitManager.setNullWebDriverWait();
    }
}

package com.halas.drivers;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static com.halas.drivers.WebDriverManager.getWebDriver;

public class WebDriverWaitManager {
    private static WebDriverWait driverWait;

    private WebDriverWaitManager() {
    }

    public static WebDriverWait getWebDriverWait() {
        if (Objects.isNull(driverWait)) {
            createWebDriverWait();
        }
        return driverWait;
    }

    public static void setNullWebDriverWait() {
        driverWait = null;
    }

    private static void createWebDriverWait() {
        driverWait = new WebDriverWait(getWebDriver(), 30);
    }
}

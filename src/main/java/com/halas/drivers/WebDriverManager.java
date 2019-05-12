package com.halas.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.halas.property.HandleProperty.getValueProperty;

public class WebDriverManager {
    private static final String PATH_TO_DRIVER = getValueProperty("path");
    private static final String WEB_DRIVER_NAME = getValueProperty("name");

    private static WebDriver driver;

    static {
        System.setProperty(WEB_DRIVER_NAME, PATH_TO_DRIVER);
    }

    private WebDriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (Objects.isNull(driver)) {
            createWebDriver();
        }
        return driver;
    }

    private static void createWebDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}

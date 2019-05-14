package com.halas.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.halas.property.HandleProperty.getValueProperty;

public class WebDriverManager {
    private static final String PATH_TO_DRIVER = getValueProperty("path");
    private static final String WEB_DRIVER_NAME = getValueProperty("name");
    private static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    static {
        System.setProperty(WEB_DRIVER_NAME, PATH_TO_DRIVER);
    }

    private WebDriverManager() {
    }

    public static WebDriver getWebDriver() {
        if (Objects.isNull(DRIVER_POOL.get())) {
            createWebDriver();
        }
        return DRIVER_POOL.get();
    }

    public static void setNullWebDriver(){
        DRIVER_POOL.set(null);
    }

    private static void createWebDriver() {
        DRIVER_POOL.set(new ChromeDriver());
        DRIVER_POOL.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        DRIVER_POOL.get().manage().window().maximize();
    }
}

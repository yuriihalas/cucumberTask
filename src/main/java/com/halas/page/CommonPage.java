package com.halas.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.halas.driver.WebDriverManager.getWebDriver;
import static com.halas.property.HandleProperty.getValueProperty;

public abstract class CommonPage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public CommonPage() {
        initElements();
    }

    private void initElements() {
        driver = getWebDriver();
        driverWait = new WebDriverWait(driver, Integer.valueOf(getValueProperty("explicit-wait-time")));
        PageFactory.initElements(driver, this);
    }
}

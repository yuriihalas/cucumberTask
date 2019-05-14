package com.halas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.halas.drivers.WebDriverManager.getWebDriver;
import static com.halas.drivers.WebDriverWaitManager.getWebDriverWait;

public abstract class CommonPage {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    public CommonPage() {
        initElements();
    }

    private void initElements() {
        PageFactory.initElements(getWebDriver(), this);
        driver = getWebDriver();
        driverWait = getWebDriverWait();
    }
}

package com.halas.pages;

import org.openqa.selenium.support.PageFactory;

import static com.halas.drivers.WebDriverManager.getWebDriver;

public abstract class CommonPage {
    public CommonPage() {
        PageFactory.initElements(getWebDriver(), this);
    }
}

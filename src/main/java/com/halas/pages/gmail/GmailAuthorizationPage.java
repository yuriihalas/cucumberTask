package com.halas.pages.gmail;

import com.halas.pages.CommonPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailAuthorizationPage extends CommonPage {
    @FindBy(id = "identifierId")
    private WebElement loginField;
    @FindBy(id = "identifierNext")
    private WebElement nextButtonLogin;
    @FindBy(css = "input[name='password']")
    private WebElement passwordField;
    @FindBy(id = "passwordNext")
    private WebElement nextButtonPassword;

    public void fillLoginAreaAndClickNext(String login) {
        loginField.sendKeys(login);
        nextButtonLogin.click();
    }

    public void fillPasswordAreaAndClickNext(String password) {
        driverWait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
        nextButtonPassword.click();
    }
}

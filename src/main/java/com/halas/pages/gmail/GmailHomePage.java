package com.halas.pages.gmail;

import com.halas.pages.CommonPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.halas.drivers.WebDriverWaitManager.getWebDriverWait;

public class GmailHomePage extends CommonPage {
    @FindBy(css = "header a[href$='mail.google.com/mail&service=mail'][aria-label*='Google:'][role='button']")
    private WebElement accountCircle;
    @FindBy(css = "[jscontroller='DUNnfe'] [role='button']")
    private WebElement writeSomeoneButton;
    @FindBy(css = "a[href$='#drafts'][target='_top']")
    private WebElement prevoiusSavedMessage;
    @FindBy(css = "div.AO div[role='main'] tbody>tr:first-child")
    private WebElement lastSavedMessage;

    public WebElement getAccountCircle() {
        return accountCircle;
    }

    public void clickOnWriteSomeoneButton() {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(writeSomeoneButton));
        writeSomeoneButton.click();
    }

    public void clickOnPreviouslySavedMessages() {
        getWebDriverWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(prevoiusSavedMessage));
        prevoiusSavedMessage.click();
    }

    public void clickOnLastSavedMessage() {
        getWebDriverWait().until(ExpectedConditions.urlContains("drafts"));
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(lastSavedMessage));
        lastSavedMessage.click();
    }
}

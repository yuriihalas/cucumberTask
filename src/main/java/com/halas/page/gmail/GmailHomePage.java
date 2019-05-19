package com.halas.page.gmail;

import com.halas.decorator.element.realisation.Button;
import com.halas.decorator.element.realisation.Image;
import com.halas.page.CommonPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailHomePage extends CommonPage {
    @FindBy(css = "header a[href$='mail.google.com/mail&service=mail'][aria-label*='Google:'][role='button']")
    private Image accountCircle;
    @FindBy(css = "[jscontroller='DUNnfe'] [role='button']")
    private Button writeSomeone;
    @FindBy(css = "a[href$='#drafts'][target='_top']")
    private Button draftMessages;
    @FindBy(css = "div.AO div[role='main'] tbody>tr:first-child")
    private Button lastSavedMessage;

    public WebElement getAccountCircle() {
        return accountCircle.getElement();
    }

    public void clickOnWriteSomeoneButton() {
        driverWait.until(ExpectedConditions.elementToBeClickable(writeSomeone.getElement()));
        writeSomeone.click();
    }

    public void clickOnDraftsMessages() {
        driverWait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(draftMessages.getElement()));
        draftMessages.click();
        driverWait.until(ExpectedConditions.attributeToBe(draftMessages.getElement(), "tabindex", "0"));
    }

    public void clickOnFirstOnTopSavedMessage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(lastSavedMessage.getElement()));
        lastSavedMessage.click();
    }
}
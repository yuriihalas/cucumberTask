package com.halas.pages.gmail;

import com.halas.pages.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailFormSendMessage extends CommonPage {
    @FindBy(css = "*[name='to']")
    private WebElement fieldWhichEmailsSend;
    @FindBy(css = "[aria-label*='Ctrl –Shift –C']")
    private WebElement buttonEmailsCopy;
    @FindBy(name = "cc")
    private WebElement fieldEmailsCopySend;
    @FindBy(css = "[aria-label*='Ctrl –Shift –B']")
    private WebElement buttonEmailsHiddenCopy;
    @FindBy(name = "bcc")
    private WebElement fieldEmailsHiddenCopySend;
    @FindBy(name = "subjectbox")
    private WebElement fieldThemeSend;
    @FindBy(css = "div[role='textbox']")
    private WebElement fieldMessageSend;
    @FindBy(css = "*[class='Ha'][src='images/cleardot.gif']")
    private WebElement buttonSaveAndCloseFormMessage;
    @FindBy(css = "div[role='button'][data-tooltip*='(Ctrl –Enter)']")
    private WebElement sendMessage;
    @FindBy(css = "form[enctype='multipart/form-data']>*:nth-child(2)>*:first-child>*:nth-child(2)")
    private WebElement areaHiddenCopyAndCopyEmailsSend;

    public void fillFormSend(
            String emailsReceive,
            String emailsCopyReceive,
            String emailsHiddenCopyReceive,
            String theme,
            String message) {
        driverWait.until(ExpectedConditions.visibilityOf(fieldWhichEmailsSend));
        fieldWhichEmailsSend.click();
        buttonEmailsCopy.click();
        buttonEmailsHiddenCopy.click();
        fieldWhichEmailsSend.sendKeys(emailsReceive);
        fieldEmailsCopySend.sendKeys(emailsCopyReceive);
        fieldEmailsHiddenCopySend.sendKeys(emailsHiddenCopyReceive);
        fieldThemeSend.sendKeys(theme);
        fieldMessageSend.sendKeys(message);
    }

    public void clickOnButtonSaveAndCloseFormMessage() {
        buttonSaveAndCloseFormMessage.click();
    }

    public void clickOnShowHiddenCopyAndCopyMails() {
        driverWait.until(webDriver -> {
            areaHiddenCopyAndCopyEmailsSend.click();
            return true;
        });
    }

    public void clickOnSendMessage() {
        driverWait.until(ExpectedConditions.elementToBeClickable(sendMessage));
        sendMessage.click();
    }

    public String getTextFieldWhichEmailsSend() {
        return fieldWhichEmailsSend.getAttribute("value");
    }

    public String getTextFieldEmailsCopySend() {
        return fieldEmailsCopySend.getAttribute("value");
    }

    public String getTextFieldEmailsHiddenCopySend() {
        return fieldEmailsHiddenCopySend.getAttribute("value");
    }

    public String getTextFieldThemeSend() {
        return driver.findElement(
                By.cssSelector("form[enctype='multipart/form-data'] [name='subject']"))
                .getAttribute("value");
    }

    public String getTextFieldMessageSend() {
        return fieldMessageSend.getText();
    }

    public void waitUntilMessageSendingWasEnd() {
        driverWait.until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementWithText(
                By.cssSelector("[role='alert']>div>div:nth-child(2)>span:first-child>*:first-child"),
                "Надсилання…")));
    }
}

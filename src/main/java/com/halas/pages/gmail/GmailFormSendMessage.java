package com.halas.pages.gmail;

import com.halas.pages.CommonPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailFormSendMessage extends CommonPage {
    @FindBy(css = "form[enctype='multipart/form-data'] textarea[name='to']")
    private WebElement fieldWhichEmailsSend;
    @FindBy(css = "[aria-label*='Ctrl'][aria-label*='Shift'][aria-label*='C'][role='link']")
    private WebElement buttonEmailsCopy;
    @FindBy(name = "cc")
    private WebElement fieldEmailsCopySend;
    @FindBy(css = "[aria-label*='Ctrl'][aria-label*='Shift'][aria-label*='B'][role='link']")
    private WebElement buttonEmailsHiddenCopy;
    @FindBy(name = "bcc")
    private WebElement fieldEmailsHiddenCopySend;
    @FindBy(name = "subjectbox")
    private WebElement fieldThemeSend;
    @FindBy(css = "div[role='textbox']")
    private WebElement fieldMessageSend;
    @FindBy(css = "*[class='Ha'][src='images/cleardot.gif']")
    private WebElement buttonSaveAndCloseFormMessage;
    @FindBy(css = "div[role='button'][data-tooltip*='Ctrl'][data-tooltip*='Enter']")
    private WebElement sendMessage;
    @FindBy(css = "form[enctype='multipart/form-data']>div:nth-child(2)")
    private WebElement areaHiddenCopyAndCopyEmailsSend;

    public void fillFormSend(
            String emailsReceive,
            String emailsCopyReceive,
            String emailsHiddenCopyReceive,
            String theme,
            String message) {
        driverWait.until(ExpectedConditions.visibilityOf(buttonEmailsCopy));
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
        sendMessage.click();
    }

    public String getTextFieldWhichEmailsSend() {
        return driver.findElement(By.cssSelector("form[enctype='multipart/form-data'] input[name='to']"))
                .getAttribute("value");
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
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
                "[role='alert'] [role='link'][id='link_undo']")));
    }
}

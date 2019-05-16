package com.halas.pages.business;

import com.halas.pages.gmail.GmailAuthorizationPage;
import com.halas.pages.gmail.GmailFormSendMessage;
import com.halas.pages.gmail.GmailHomePage;

import static com.halas.parsers.JsonParser.getWhoReceiveMessage;
import static org.junit.Assert.assertEquals;

public class GmailBO {
    private GmailAuthorizationPage authorizationPage;
    private GmailFormSendMessage gmailFormSendMessage;
    private GmailHomePage gmailHomePage;

    public GmailBO() {
        authorizationPage = new GmailAuthorizationPage();
        gmailFormSendMessage = new GmailFormSendMessage();
        gmailHomePage = new GmailHomePage();
    }

    public void authoriseUser(String login, String password) {
        authorizationPage.fillLoginAreaAndClickNext(login);
        authorizationPage.fillPasswordAreaAndClickNext(password);
    }

    public void createDraftMessage(
            String emailsReceive,
            String emailsCopyReceive,
            String emailsHiddenCopyReceive,
            String theme,
            String message) {
        gmailHomePage.clickOnWriteSomeoneButton();
        gmailFormSendMessage.clickOnEmailsCopy();
        gmailFormSendMessage.clickOnEmailsHiddenCopy();
        gmailFormSendMessage.fillEmailField(emailsReceive);
        gmailFormSendMessage.fillEmailCopyField(emailsCopyReceive);
        gmailFormSendMessage.fillEmailHiddenCopyField(emailsHiddenCopyReceive);
        gmailFormSendMessage.fillThemeField(theme);
        gmailFormSendMessage.fillMessageField(message);
        gmailFormSendMessage.clickOnButtonSaveAndCloseFormMessage();
    }

    public void goToDraftMessagesAndCheckAllFields() {
        gmailHomePage.clickOnPreviouslySavedMessages();
        gmailHomePage.clickOnLastSavedMessage();
        gmailFormSendMessage.clickOnShowHiddenCopyAndCopyMails();
        String actualEmailSend = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        assertEquals(actualEmailSend, getWhoReceiveMessage());
    }

    public void sendMessage() {
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilMessageSendingWasEnd();
    }
}

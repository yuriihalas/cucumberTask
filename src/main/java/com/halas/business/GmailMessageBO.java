package com.halas.business;

import com.halas.model.Message;
import com.halas.page.gmail.GmailFormSendMessage;
import com.halas.page.gmail.GmailHomePage;

public class GmailMessageBO {
    private GmailFormSendMessage gmailFormSendMessage;
    private GmailHomePage gmailHomePage;

    public GmailMessageBO() {
        gmailFormSendMessage = new GmailFormSendMessage();
        gmailHomePage = new GmailHomePage();
    }

    public void createDraftMessage(final Message message) {
        gmailHomePage.clickOnWriteSomeoneButton();
        //якщо знайде що курсор є не на елементі email, а на якомусь іншому
        if (isMouseFocusOnNotEmailField()) {
            gmailFormSendMessage.clickOnEmailField();
        }
        //gmailFormSendMessage.clickOnEmail();
        gmailFormSendMessage.clickOnCC();
        gmailFormSendMessage.clickOnBCC();
        fillMessage(message);
        gmailFormSendMessage.clickOnButtonSaveAndCloseFormMessage();
    }

    private void fillMessage(final Message message) {
        gmailFormSendMessage.fillEmailField(message.getTo());
        gmailFormSendMessage.fillCCField(message.getCc());
        gmailFormSendMessage.fillBCCField(message.getBcc());
        gmailFormSendMessage.fillSubjectField(message.getSubject());
        gmailFormSendMessage.fillMessageField(message.getMessage());
    }

    private boolean isMouseFocusOnNotEmailField() {
        return gmailFormSendMessage.isDisplayedEmailFieldWithoutCcAndBcc();
    }

    public void goToDraftMessagesClickOnFirstOnTopMessage() {
        gmailHomePage.clickOnDraftsMessages();
        gmailHomePage.clickOnFirstOnTopSavedMessage();
        gmailFormSendMessage.clickOnEmailField();
    }

    public Message getMessage() {
        String actualTo = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        String actualCC = gmailFormSendMessage.getTextFromCcField();
        String actualBCC = gmailFormSendMessage.getTextFromBccField();
        String actualSubject = gmailFormSendMessage.getTextFieldThemeSend();
        String actualMess = gmailFormSendMessage.getTextFromMessage();
        return new Message(actualTo, actualCC, actualBCC, actualSubject, actualMess);
    }

    public void sendMessage() {
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilSendingMessageWillEnd();
    }
}

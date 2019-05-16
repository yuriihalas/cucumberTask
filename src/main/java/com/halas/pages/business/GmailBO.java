package com.halas.pages.business;

import com.halas.models.Message;
import com.halas.pages.gmail.GmailAuthorizationPage;
import com.halas.pages.gmail.GmailFormSendMessage;
import com.halas.pages.gmail.GmailHomePage;
import org.openqa.selenium.WebElement;

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

    public boolean checkSuccessAuthorisation(String userLogin) {
        WebElement accountCircle = gmailHomePage.getAccountCircle();
        return accountCircle.getAttribute("aria-label").contains(userLogin);
    }

    public void createDraftMessage(Message message) {
        gmailHomePage.clickOnWriteSomeoneButton();
        gmailFormSendMessage.clickOnCC();
        gmailFormSendMessage.clickOnBCC();
        gmailFormSendMessage.fillEmailField(message.getTo());
        gmailFormSendMessage.fillCCField(message.getCc());
        gmailFormSendMessage.fillBCCField(message.getBcc());
        gmailFormSendMessage.fillSubjectField(message.getSubject());
        gmailFormSendMessage.fillMessageField(message.getMessage());
        gmailFormSendMessage.clickOnButtonSaveAndCloseFormMessage();
    }

    public void goToDraftMessagesClickOnLastMessage() {
        gmailHomePage.clickOnPreviouslySavedMessages();
        gmailHomePage.clickOnLastSavedMessage();
        gmailFormSendMessage.clickOnShowEmailsFields();
    }

    public Message getMessage() {
        String actualTo = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        String actualCC = gmailFormSendMessage.getTextFieldEmailsCopySend();
        String actualBCC = gmailFormSendMessage.getTextFieldEmailsHiddenCopySend();
        String actualSubject = gmailFormSendMessage.getTextFieldThemeSend();
        String actualMess = gmailFormSendMessage.getTextFieldMessageSend();
        return new Message(actualTo, actualCC, actualBCC, actualSubject, actualMess);
    }

    public void sendMessage() {
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilMessageSendingWasEnd();
    }
}

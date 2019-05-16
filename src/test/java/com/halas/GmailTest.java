package com.halas;

import com.halas.drivers.WebDriverManager;
import com.halas.pages.gmail.GmailAuthorizationPage;
import com.halas.pages.gmail.GmailFormSendMessage;
import com.halas.pages.gmail.GmailHomePage;
import com.halas.pages.business.GmailBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.halas.parsers.JsonParser.*;
import static com.halas.parsers.JsonParser.getMessage;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailTest.class);

    @DataProvider(parallel = true)
    public Iterator<Object[]> usersLoginPassword() {
        return Stream.of(
                new Object[]{"paprika0020@gmail.com", "423489123789op"},
                new Object[]{"paprika0019@gmail.com", "423489123789op"},
                new Object[]{"paprika0018@gmail.com", "423489123789op"},
                new Object[]{"paprika0017@gmail.com", "423489123789op"},
                new Object[]{"paprika0015@gmail.com", "423489123789op"}).iterator();
    }

    @BeforeMethod
    void initUrlAndPages() {
        WebDriverManager.getWebDriver().get(getBaseUrl());
    }

    @Test(dataProvider = "usersLoginPassword")
    void testSingInAndGoToSavedMessageAndCheckCorrectAndSendMess(String userLogin, String userPassword) {
        GmailAuthorizationPage authorizationPage = new GmailAuthorizationPage();
        GmailHomePage gmailHomePage = new GmailHomePage();
        GmailFormSendMessage gmailFormSendMessage = new GmailFormSendMessage();
        GmailBO gmailBO = new GmailBO();

        gmailBO.authoriseUser(userLogin, userPassword);
        //assert to success login
        WebElement accountCircle = gmailHomePage.getAccountCircle();
        assertTrue(accountCircle.getAttribute("aria-label").contains(userLogin));
        LOG.info("Title page: " + WebDriverManager.getWebDriver().getTitle());
        //click on write someone button will open mailFormSendMessage
        gmailBO.createDraftMessage(
                getWhoReceiveMessage(),
                getWhoReceiveCopyMessage(),
                getWhoReceiveHiddenCopyMessage(),
                getThemeMessage(),
                getMessage());

        gmailBO.goToDraftMessagesAndCheckAllFields();
        //get all fields from formMessage
        String actualEmailSend = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        String actualEmailCopySend = gmailFormSendMessage.getTextFieldEmailsCopySend();
        String actualEmailHiddenCopySend = gmailFormSendMessage.getTextFieldEmailsHiddenCopySend();
        String actualThemeSend = gmailFormSendMessage.getTextFieldThemeSend();
        String actualMessageSend = gmailFormSendMessage.getTextFieldMessageSend();
        //assert actual and expected results
        assertEquals(actualEmailSend, getWhoReceiveMessage());
        LOG.info("Email FINE.");
        assertEquals(actualEmailCopySend, getWhoReceiveCopyMessage());
        LOG.info("Email copy FINE.");
        assertEquals(actualEmailHiddenCopySend, getWhoReceiveHiddenCopyMessage());
        LOG.info("Email hidden copy FINE.");
        assertEquals(actualThemeSend, getThemeMessage());
        LOG.info("Message subject FINE.");
        assertEquals(actualMessageSend, getMessage());
        LOG.info("Message FINE.");
        //send message
        gmailBO.sendMessage();
    }

    @AfterMethod
    void closeResources() {
        WebDriverManager.shutdown();
    }
}

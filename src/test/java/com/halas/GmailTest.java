package com.halas;

import com.halas.drivers.WebDriverManager;
import com.halas.pages.gmail.GmailAuthorizationPage;
import com.halas.pages.gmail.GmailFormSendMessage;
import com.halas.pages.gmail.GmailHomePage;
import com.halas.parsers.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailTest.class);
    private static final String BASE_URL_PAGE = JsonParser.getBaseUrl();
    private static final String EMAIL_SEND = JsonParser.getWhoReceiveMessage();
    private static final String EMAIL_COPY_SEND = JsonParser.getWhoReceiveCopyMessage();
    private static final String EMAIL_HIDDEN_COPY_SEND = JsonParser.getWhoReceiveHiddenCopyMessage();
    private static final String THEME_SEND = JsonParser.getThemeMessage();
    private static final String MESSAGE_SEND = JsonParser.getMessage();

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
        WebDriverManager.getWebDriver().get(BASE_URL_PAGE);
    }

    @Test(dataProvider = "usersLoginPassword")
    void testSingInAndGoToSavedMessageAndCheckCorrectAndSendMess(String userLogin, String userPassword) {
        GmailAuthorizationPage authorizationPage = new GmailAuthorizationPage();
        GmailHomePage gmailHomePage = new GmailHomePage();
        GmailFormSendMessage gmailFormSendMessage = new GmailFormSendMessage();
        //authorization block
        authorizationPage.fillLoginAreaAndClickNext(userLogin);
        authorizationPage.fillPasswordAreaAndClickNext(userPassword);
        WebElement accountCircle = gmailHomePage.getAccountCircle();
        assertTrue(accountCircle.getAttribute("aria-label").contains(userLogin));
        LOG.info("Title page: " + WebDriverManager.getWebDriver().getTitle());
        //click on write someone button will open mailFormSendMessage
        gmailHomePage.clickOnWriteSomeoneButton();
        gmailFormSendMessage.fillFormSend(EMAIL_SEND, EMAIL_COPY_SEND, EMAIL_HIDDEN_COPY_SEND, THEME_SEND, MESSAGE_SEND);
        gmailFormSendMessage.clickOnButtonSaveAndCloseFormMessage();
        gmailHomePage.clickOnPreviouslySavedMessages();
        gmailHomePage.clickOnLastSavedMessage();
        gmailFormSendMessage.clickOnShowHiddenCopyAndCopyMails();
        //get all fields from formMessage
        String actualEmailSend = gmailFormSendMessage.getTextFieldWhichEmailsSend();
        String actualEmailCopySend = gmailFormSendMessage.getTextFieldEmailsCopySend();
        String actualEmailHiddenCopySend = gmailFormSendMessage.getTextFieldEmailsHiddenCopySend();
        String actualThemeSend = gmailFormSendMessage.getTextFieldThemeSend();
        String actualMessageSend = gmailFormSendMessage.getTextFieldMessageSend();
        //assert actual and expected results
        assertEquals(actualEmailSend, EMAIL_SEND);
        LOG.info("Email FINE.");
        assertEquals(actualEmailCopySend, EMAIL_COPY_SEND);
        LOG.info("Email copy FINE.");
        assertEquals(actualEmailHiddenCopySend, EMAIL_HIDDEN_COPY_SEND);
        LOG.info("Email hidden copy FINE.");
        assertEquals(actualThemeSend, THEME_SEND);
        LOG.info("Message subject FINE.");
        assertEquals(actualMessageSend, MESSAGE_SEND);
        LOG.info("Message FINE.");
        //send message
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilMessageSendingWasEnd();
    }

    @AfterMethod
    void closeResources() {
        WebDriverManager.shutdown();
    }
}

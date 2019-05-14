package com.halas;

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

import static com.halas.drivers.WebDriverManager.getWebDriver;
import static com.halas.drivers.helper.SetNullDrivers.setNullOnAllDrivers;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailTest.class);
    private static final String BASE_URL_PAGE = JsonParser.getBaseUrl();
    private static final String USER_LOGIN = JsonParser.getUserLogin();
    private static final String USER_PASSWORD = JsonParser.getUserPassword();
    private static final String EMAIL_SEND = JsonParser.getWhoReceiveMessage();
    private static final String EMAIL_COPY_SEND = JsonParser.getWhoReceiveCopyMessage();
    private static final String EMAIL_HIDDEN_COPY_SEND = JsonParser.getWhoReceiveHiddenCopyMessage();
    private static final String THEME_SEND = JsonParser.getThemeMessage();
    private static final String MESSAGE_SEND = JsonParser.getMessage();

    @DataProvider
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
        getWebDriver().get(BASE_URL_PAGE);
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
        LOG.info("Title page: " + getWebDriver().getTitle());
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
        assertEquals(actualEmailCopySend, EMAIL_COPY_SEND);
        assertEquals(actualEmailHiddenCopySend, EMAIL_HIDDEN_COPY_SEND);
        assertEquals(actualThemeSend, THEME_SEND);
        assertEquals(actualMessageSend, MESSAGE_SEND);
        //send message
        gmailFormSendMessage.clickOnSendMessage();
        gmailFormSendMessage.waitUntilMessageSendingWasEnd();
    }

    @AfterMethod
    void closeResources() {
        getWebDriver().quit();
        setNullOnAllDrivers();
    }
}

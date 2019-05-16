package com.halas;

import com.halas.drivers.WebDriverManager;
import com.halas.models.Message;
import com.halas.pages.business.GmailBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.halas.parsers.JsonParser.*;
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
        GmailBO gmailBO = new GmailBO();
        Message message = new Message(getWhoReceiveMessage(), getWhoReceiveCopyMessage(), getWhoReceiveHiddenCopyMessage(), getThemeMessage(), getMessage());
        gmailBO.authoriseUser(userLogin, userPassword);
        //assert to success login
        assertTrue(gmailBO.checkSuccessAuthorisation(userLogin));
        LOG.info("Title page: " + WebDriverManager.getWebDriver().getTitle());
        //click on write someone button will open mailFormSendMessage
        gmailBO.createDraftMessage(message);
        gmailBO.goToDraftMessagesClickOnLastMessage();
        Message actualMessage = gmailBO.getMessage();
        //assert actual and expected results
        assertEquals(actualMessage, message);
        LOG.info("Message FINE.");
        //send message
        gmailBO.sendMessage();
    }

    @AfterMethod
    void closeResources() {
        WebDriverManager.shutdown();
    }
}

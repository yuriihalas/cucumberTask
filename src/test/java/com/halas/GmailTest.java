package com.halas;

import com.halas.business.GmailAuthorisationBO;
import com.halas.business.GmailMessageBO;
import com.halas.driver.WebDriverManager;
import com.halas.listener.MyTestListener;
import com.halas.model.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.stream.Stream;

import static com.halas.driver.WebDriverManager.getWebDriver;
import static com.halas.parser.JsonParser.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({MyTestListener.class})
public class GmailTest {
    private static final Logger LOG = LogManager.getLogger(GmailTest.class);

    @DataProvider(parallel = true)
    public Iterator<Object[]> usersLoginPassword() {
        return Stream.of(getUsers()).iterator();
    }

    @BeforeMethod
    void initUrlAndPages() {
        getWebDriver().get(getBaseUrl());
    }

    @Test(dataProvider = "usersLoginPassword")
    void testSingInAndGoToSavedMessageAndCheckCorrectAndSendMess(String userLogin, String userPassword) {
        GmailAuthorisationBO gmailAuthorisationBO = new GmailAuthorisationBO();
        GmailMessageBO gmailMessageBO = new GmailMessageBO();
        Message message = new Message(getWhoReceiveMessage(), getWhoReceiveCopyMessage(), getWhoReceiveHiddenCopyMessage(), getThemeMessage(), getMessage());
        gmailAuthorisationBO.authoriseUser(userLogin, userPassword);
        //assert to success login
        assertTrue(gmailAuthorisationBO.checkSuccessAuthorisation(userLogin));
        LOG.info("Title page: " + getWebDriver().getTitle());
        gmailMessageBO.createDraftMessage(message);
        gmailMessageBO.goToDraftMessagesClickOnLastMessage();
        Message actualMessage = gmailMessageBO.getMessage();
        //assert actual and expected fields from message
        assertEquals(actualMessage, message);
        LOG.info("Message FINE.");
        gmailMessageBO.sendMessage();
    }

    @AfterMethod
    void closeResources() {
        WebDriverManager.shutdown();
    }
}

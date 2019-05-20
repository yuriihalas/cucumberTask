package com.halas.business;

import com.halas.page.gmail.GmailAuthorizationPage;
import com.halas.page.gmail.GmailHomePage;
import org.openqa.selenium.WebElement;

public class GmailAuthorisationBO {
    private GmailAuthorizationPage authorizationPage;
    private GmailHomePage gmailHomePage;

    public GmailAuthorisationBO() {
        authorizationPage = new GmailAuthorizationPage();
        gmailHomePage = new GmailHomePage();
    }

    public void authoriseUser(final String login, final String password) {
        authorizationPage.fillLoginAreaAndClickNext(login);
        authorizationPage.fillPasswordAreaAndClickNext(password);
    }

    public boolean checkSuccessAuthorisation(final String userLogin) {
        WebElement accountCircle = gmailHomePage.getAccountCircle();
        return accountCircle.getAttribute("aria-label").contains(userLogin);
    }
}

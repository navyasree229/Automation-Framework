package com.loop.qa.pages;

import com.loop.qa.utils.AppConstants;
import com.loop.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class LoginPage {

    WebDriver driver;
    private final ElementUtil eleUtil;
    private By loginGoogleBtn = By.xpath("//span[text()=\"Continue with Google\"]");
    private By loginEmail = By.xpath("//input[@type = 'email']");
    private By passwordField = By.xpath("//input[@type = 'password']");
    private By submitLoginBtn = By.xpath("//span[text()=\"Next\"]");
    public LoginPage(WebDriver driver) {
        eleUtil = new ElementUtil(driver);
    }

    //TODO: Create Xpath locators and methods in here

    public String getLoginPageTitle() {
        String title = eleUtil.waitForTitleIs("Hr Dashboard", AppConstants.SHORT_TIME_OUT);
        System.out.println("Login Page title is: " + title);
        return title;
    }

    public HrdLogin loginToHrd(String username, String password) {
        eleUtil.waitForElementVisible(loginGoogleBtn, AppConstants.SHORT_TIME_OUT);
        String mainWindow = eleUtil.getCurrentWindowHandle();
        System.out.println(mainWindow);

        eleUtil.getCurrentTitle().equals("Sign in - Google Accounts");
        eleUtil.doClick(loginGoogleBtn);

        ArrayList<String> tabs = new ArrayList<>(eleUtil.getCurrentWindowHandles());
        System.out.println(tabs);
        eleUtil.switchWindow(tabs);
        eleUtil.waitForElementVisible(loginEmail, AppConstants.MEDIUM_TIME_OUT);

        eleUtil.doClick(loginEmail);
        setEmail(username);
//        wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        eleUtil.waitForElementsVisible(passwordField, AppConstants.SHORT_TIME_OUT);
        setPassword(password);
      //  eleUtil.doClick(submitLoginBtn);
        return new HrdLogin(driver);
    }

    public void setEmail(String username) {
        System.out.println("Check if Creds are entered");
        eleUtil.doSendKeys(loginEmail, username + Keys.ENTER);
    }

    public void setPassword(String password){
        eleUtil.doClick(passwordField);
        eleUtil.doSendKeys(passwordField, password + Keys.ENTER);
    }
}

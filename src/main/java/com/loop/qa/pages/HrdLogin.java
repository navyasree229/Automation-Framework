package com.loop.qa.pages;

import com.loop.qa.utils.AppConstants;
import com.loop.qa.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HrdLogin {
    private WebDriver driver;
    private ElementUtil eleUtil;

    private By signoutBtn = By.xpath("//div[contains(text(), 'Sign Out')]");


    public HrdLogin(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }



    public boolean isSignOutButtonExist(){
        return eleUtil.waitForElementPresence(signoutBtn, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
    }
}

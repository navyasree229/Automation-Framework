package com.qa.tests;

import com.loop.qa.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HrdLogintests extends BaseTest {

    @Test
    public void loginPageTitleTest() {
        String actualTitle = loginPage.getLoginPageTitle();
        logger.info("title:" + actualTitle);
        Assert.assertEquals(actualTitle, "Hr Dashboard");
    }

    @Test
    public void loginToHrdTest() {
        hrdLogin = loginPage.loginToHrd(prop.getProperty("username"), prop.getProperty("password"));

//        Assert.assertEquals(companyPage.isLogoutLinkExists(), true);
    }
}

package com.nopcommerce.tests;

import com.nopcommerce.base.*;
import com.nopcommerce.util.*;
import org.testng.*;
import org.testng.annotations.*;

public class LoginPageTest extends BaseTest {


    @BeforeMethod
    public void loginPageSetup(){
     loginPage = homePage.goToLoginPage();
    }


    @Test(priority = 1)
    public void verifyLoginPageNameTest(){
        Assert.assertEquals(loginPage.getLoginPageName(), Constants.LOGINPAGE_NAME);
    }

    @Test(priority = 2)
    public void verifyRegisterButtonStatusTest(){
        Assert.assertTrue(loginPage.verifyRegisterButtonStatus());
    }

    @Test(priority = 3)
    public void validateUserLoginTest() throws InterruptedException {
        loginPage.doLogin(prop.getProperty("id"),prop.getProperty("pass"));
        Thread.sleep(2000);
        Assert.assertTrue(homePage.verifyLogoutLink());
    }
}

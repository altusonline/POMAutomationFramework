package com.nopcommerce.tests;

import com.nopcommerce.base.*;
import com.nopcommerce.util.*;
import org.testng.*;
import org.testng.annotations.*;

public class RegisterPageTest extends BaseTest {

    @BeforeMethod
    public void registerPageSetup(){
      registerPage = homePage.goToRegisterPage();
    }

    @Test(dataProvider = "getUserData")
    public void registerNewUserTest(String gender, String firstName, String lastName, String day, String month,
                                    String year, String email, String company, String password){
        registerPage.registerNewUser(gender,firstName,lastName,day,month,year,email,company,password);
        Assert.assertTrue(homePage.verifyMyAccountLink());
    }

    @DataProvider
    public Object[][] getUserData(){
        Object data[][] = ExcelUtil.getTestData("Sheet1");
        return data;
    }
}

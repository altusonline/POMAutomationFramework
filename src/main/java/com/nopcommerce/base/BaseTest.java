package com.nopcommerce.base;

import com.nopcommerce.pages.*;
import org.openqa.selenium.*;
import org.testng.annotations.*;

import java.util.*;

public class BaseTest {
    public WebDriver driver;
    public BasePage basePage;
    public Properties prop;
    public HomePage homePage;
    public LoginPage loginPage;
    public RegisterPage registerPage;
    public SearchPage searchPage;

    @BeforeTest
    public void setup(){
      basePage = new BasePage();
      prop = basePage.init_prop();
      String browserType = prop.getProperty("browser");
      driver = basePage.init_driver(browserType);
      homePage = new HomePage(driver);
      driver.get(prop.getProperty("url"));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}

package com.nopcommerce.pages;

import com.nopcommerce.base.*;
import com.nopcommerce.util.*;
import org.openqa.selenium.*;

public class LoginPage extends BasePage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    // identify elements
    private By loginPageName = By.xpath("//div[@class='page-title']/h1");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By rememberMeCheckbox = By.id("RememberMe");
    private By loginBtn = By.xpath("//button[text()='Log in']");
    private By registerBtn = By.xpath("//button[text()='Register']");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getLoginPageName(){
      //  return driver.findElement(loginPageName).getText();
        return elementUtil.doGetText(loginPageName);
    }

    //page chaining - if an action takes you to a new page
    //return type of the action must object of landing page
    public HomePage doLogin(String userID, String password){
        elementUtil.doSendKeys(emailField, userID);
        elementUtil.doSendKeys(passwordField,password);
        elementUtil.doClick(loginBtn);

     //   driver.findElement(emailField).sendKeys(userID);
     //   driver.findElement(passwordField).sendKeys(password);
     //   driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }

    public boolean verifyRegisterButtonStatus(){
        return elementUtil.doIsDisplayed(registerBtn);
        //return driver.findElement(registerBtn).isDisplayed();
    }

}

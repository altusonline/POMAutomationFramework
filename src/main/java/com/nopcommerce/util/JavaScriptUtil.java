package com.nopcommerce.util;

import org.openqa.selenium.*;

public class JavaScriptUtil {

    WebDriver driver;

    public JavaScriptUtil(WebDriver driver){
        this.driver=driver;
    }

    public void flash(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String bgColor = element.getCssValue("backgroundColor");
        for(int i=0; i<10; i++){
            js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "background: yellow; border: 2px solid red");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            js.executeScript("arguments[0].setAttribute('style',arguments[1]);",element,"");
        }
    }

}

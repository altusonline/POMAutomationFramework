package com.nopcommerce.base;

import com.nopcommerce.util.*;
import io.github.bonigarcia.wdm.*;
import org.apache.commons.io.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class BasePage {


    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    public WebDriver init_driver(String browser){

        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            if(Boolean.parseBoolean(prop.getProperty("remote"))){
                init_remoteDriver("chrome");
            }else {
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
            }
        }else if(browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
           if(Boolean.getBoolean(prop.getProperty("remote"))){
               init_remoteDriver("firefox");
           }else {
               tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
           }
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return getDriver();
    }

    private void init_remoteDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            dc.setCapability(ChromeOptions.CAPABILITY, optionsManager.getChromeOptions());
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if(browser.equalsIgnoreCase("firefox")){
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            dc.setCapability(FirefoxOptions.FIREFOX_OPTIONS,optionsManager.getFirefoxOptions());
            try {
                tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),dc));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

    }

    public static synchronized WebDriver getDriver(){
        return tlDriver.get();
    }

    public Properties init_prop(){
        prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./src/main/java/com/nopcommerce/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return prop;
    }

    public String getScreenshot(){
        File src =  ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}

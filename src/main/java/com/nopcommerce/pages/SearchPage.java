package com.nopcommerce.pages;

import com.nopcommerce.base.*;
import com.nopcommerce.util.*;
import org.openqa.selenium.*;

import java.util.*;

public class SearchPage extends BasePage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public SearchPage(WebDriver driver){
        this.driver=driver;
        elementUtil = new ElementUtil(driver);
    }


    private By searchPageName = By.xpath("//div[@class='page-title']/h1");
    private By searchField = By.name("q");
    private By searchKeywordField = By.id("q");
    private By advancedSearchCheckbox = By.id("advs");
    private By manufactureDropdown = By.id("mid");
    private By searchBtn = By.xpath("//button[text()='Search']");
    private By categoriesList = By.xpath("//div[@class='side-2']/div[1]/div[2]/ul/li/a");


    public String getSearchPageName(){
        return elementUtil.doGetText(searchPageName);
    }

    public boolean verifyManufactureDropdownList(){
        elementUtil.doClick(advancedSearchCheckbox);
        return elementUtil.compareDropDownOption(manufactureDropdown,Constants.MANUFACTURE_DROPDOWN);
    }

    public List<String> getCategoriesItem(){
        List<String> categoryItemName = new ArrayList<>();
        List<WebElement> categoriesListElement = elementUtil.getElements(categoriesList);
        for(WebElement e : categoriesListElement){
            categoryItemName.add(e.getText());
        }
        return categoryItemName;
    }
}

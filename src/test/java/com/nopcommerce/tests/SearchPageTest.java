package com.nopcommerce.tests;

import com.nopcommerce.base.*;
import com.nopcommerce.util.*;
import org.testng.*;
import org.testng.annotations.*;

public class SearchPageTest extends BaseTest {

    @BeforeMethod
    public void searchPageSetup(){
      searchPage = homePage.doSearch(prop.getProperty("searchItem"));
    }

    @Test(priority = 1)
    public void verifySearchPageNameTest(){
        Assert.assertEquals(searchPage.getSearchPageName(), Constants.SEARCHPAGE_NAME);
    }

    @Test(priority = 2)
    public void verifyManufactureDropdownListTest(){
        Assert.assertTrue(searchPage.verifyManufactureDropdownList());
    }

    @Test(priority = 3)
    public void validateCategoryListTest(){
        Assert.assertEquals(searchPage.getCategoriesItem(),Constants.actualCategoryList());
    }
}

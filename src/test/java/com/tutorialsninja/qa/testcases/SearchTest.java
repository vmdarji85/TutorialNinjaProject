package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends Base {
    public WebDriver driver ;
    SearchPage searchPage;
    HomePage homePage;

    public SearchTest(){
        super();
    }

    @BeforeMethod
    public void SetUp(){
        driver = initializeBrowserAndOpenApplicationURL(properties.getProperty("browserName"));
        homePage = new HomePage(driver);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {
//        homePage.enterProductIntoSearchTextBox(testdataProps.getProperty("validProduct"));
//        homePage.clickSearchButton();
        searchPage = homePage.searchForProduct(testdataProps.getProperty("validProduct"));
        Assert.assertTrue(searchPage.isValidHPProductDisplayed(), "Valid Product HP is not displayed");
    }
    @Test(priority = 2)
    public void verifySearchWithInvalidProduct() {

//        homePage.enterProductIntoSearchTextBox(testdataProps.getProperty("invalidProduct"));
//        searchPage = homePage.clickSearchButton();
//
        searchPage = homePage.searchForProduct(testdataProps.getProperty("invalidProduct"));

        String actualSearchMessage = searchPage.getProductNotSearchWarningMessage();
        Assert.assertEquals(actualSearchMessage, testdataProps.getProperty("productNotSearchWarning"), "Search message not displayed for Invalid product");
    }
    @Test(priority = 3)
    public void verifySearchWithAnyProduct() {
        searchPage = homePage.clickSearchButton();
        String actualSearchMessage = searchPage.getProductNotSearchWarningMessage();
        Assert.assertEquals(actualSearchMessage, testdataProps.getProperty("productNotSearchWarning"), "Search message not displayed for No product");
    }



}
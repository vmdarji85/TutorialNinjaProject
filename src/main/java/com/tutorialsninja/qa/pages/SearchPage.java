package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;


    @FindBy(xpath = "//div[@class='caption']/h4")
    private WebElement validHPProduct;
    @FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
    private WebElement productNotSearchWarning;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public boolean isValidHPProductDisplayed() {
        return validHPProduct.isDisplayed();
    }

    public String getProductNotSearchWarningMessage() {
        return productNotSearchWarning.getText();
    }
}
package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
    WebDriver driver;


    @FindBy(css = "#content h1")
    private WebElement accountSuccessHeading;

    public AccountSuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String retrieveAccountSuccessHeading(){
        return accountSuccessHeading.getText();
    }
}
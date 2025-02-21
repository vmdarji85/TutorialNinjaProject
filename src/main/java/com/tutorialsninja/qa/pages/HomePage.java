package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    //Objects

    WebDriver driver;
    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu-right')]//a[text()='Login']")
    private WebElement loginOption;
    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu-right')]//a[text()='Register']")
    private WebElement registerOption;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchTextBox;

    @FindBy(xpath = "//div[@id='search']/descendant::button")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Actions
//
    public void clickOnMyAccountDropMenu() {
        myAccountDropMenu.click();
    }

    public LoginPage selectLoginOption(){
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage selectRegisterOption() {
        registerOption.click();
        return new RegisterPage(driver);
    }

    public void enterProductIntoSearchTextBox(String text) {
        searchTextBox.sendKeys(text);
    }
    public SearchPage clickSearchButton() {
        searchButton.click();
        return new SearchPage(driver);
    }
//
    public LoginPage navigateToLoginPage() {
        myAccountDropMenu.click();
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegistrationPage() {
        myAccountDropMenu.click();
        registerOption.click();
        return new RegisterPage(driver);
    }

    public SearchPage searchForProduct(String text) {
        searchTextBox.sendKeys(text);
        searchButton.click();
        return new SearchPage(driver);

    }






}
package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "input-email")
    private WebElement emailAddressFiled;

    @FindBy(id = "input-password")
    private WebElement passwordFiled;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(css = ".alert-dismissible")
    private WebElement emailPasswordNotMatchingWarning;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Actions

    public void enterEmailAddress(String email){
        emailAddressFiled.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordFiled.sendKeys(password);
    }

    public AccountPage clickLoginButton() {
        loginButton.click();
        return new AccountPage(driver);
    }

    public String retrieveEmailPasswordNotMatchingWarning() {
       return emailPasswordNotMatchingWarning.getText();
    }

    public AccountPage login(String email, String password){
        emailAddressFiled.sendKeys(email);
        passwordFiled.sendKeys(password);
        loginButton.click();
        return new AccountPage(driver);
    }

}
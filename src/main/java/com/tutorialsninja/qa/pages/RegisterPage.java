package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    @FindBy(id="input-firstname")
    private WebElement firstNameField;
    @FindBy(id="input-lastname")
    private WebElement lastNameField;
    @FindBy(id="input-email")
    private WebElement emailFiled;
    @FindBy(id="input-telephone")
    private WebElement telephoneNumberField;
    @FindBy(id = "input-password")
    private WebElement passwordField;
    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordField;
    @FindBy(name = "agree")
    private WebElement agreementCheckBox;
    @FindBy(css = "input[value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@name='newsletter'][@value=1]")
    private WebElement yesNewsletterRadioButton;

    @FindBy(css = "div[class*='alert-dismissible']")
    private WebElement duplicateEmailWarningText;

    @FindBy(css = "div[class*='alert-dismissible']")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarningMessage;
    @FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarningMessage;
    @FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarningMessage;
    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarningMessage;
    @FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarningMessage;


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Action Method

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailFiled.sendKeys(email);
    }
    public void enterTelephoneNumber(String telephoneNumber){
        telephoneNumberField.sendKeys(telephoneNumber);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void checkAgreementCheckBox() {
        agreementCheckBox.click();
    }
    public AccountSuccessPage clickContinueButton() {
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public void clickOnYesNewsLetterRadioButton() {
        yesNewsletterRadioButton.click();
    }

    public String getAccountCreationWarningMessage() {
        return duplicateEmailWarningText.getText();
    }

    public String getFirstNameWarningMessage(){
        return firstNameWarningMessage.getText();
    }

    public String getLastNameWarningMessage() {
        return lastNameWarningMessage.getText();
    }

    public String getEmailWarningMessage() {
        return emailWarningMessage.getText();
    }

    public String getTelephoneWarningMessage() {
        return telephoneWarningMessage.getText();
    }
    public String getPasswordWarningMessage(){
        return  passwordWarningMessage.getText();
    }
    public String getPrivacyPolicyWarning() {
        return  privacyPolicyWarning.getText();
    }

    public AccountSuccessPage registerWithMandatoryFields(String firstName, String lastName, String email, String telephoneNumber, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailFiled.sendKeys(email);
        telephoneNumberField.sendKeys(telephoneNumber);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        agreementCheckBox.click();
        continueButton.click();
        return new AccountSuccessPage(driver);

    }

    public AccountSuccessPage registerWithAllFields(String firstName, String lastName, String email, String telephoneNumber, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailFiled.sendKeys(email);
        telephoneNumberField.sendKeys(telephoneNumber);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(password);
        agreementCheckBox.click();
        yesNewsletterRadioButton.click();
        continueButton.click();
        return new AccountSuccessPage(driver);

    }

    
}
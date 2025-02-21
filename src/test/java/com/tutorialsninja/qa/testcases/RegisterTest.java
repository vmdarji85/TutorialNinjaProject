package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends Base {

    public WebDriver driver;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public RegisterTest(){
        super();
    }

    @BeforeMethod
    public void setUp() {
        driver = initializeBrowserAndOpenApplicationURL(properties.getProperty("browserName"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegistrationPage();

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void verifyRegistrationAnAccountWithMandatoryField() {
//        registerPage.enterFirstName(testdataProps.getProperty("firstName"));
//        registerPage.enterLastName(testdataProps.getProperty("lastName"));
//        registerPage.enterEmail(Utilities.generateTimestampWithEmail());
//        registerPage.enterTelephoneNumber(testdataProps.getProperty("telephoneNumber"));
//        registerPage.enterPassword(properties.getProperty("validPassword"));
//        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
//        registerPage.checkAgreementCheckBox();
        accountSuccessPage =  registerPage.registerWithMandatoryFields(testdataProps.getProperty("firstName"),testdataProps.getProperty("lastName"),
                Utilities.generateTimestampWithEmail(),testdataProps.getProperty("telephoneNumber"),properties.getProperty("validPassword"));
        String accountSuccessHeading =  accountSuccessPage.retrieveAccountSuccessHeading();
        Assert.assertEquals(accountSuccessHeading, testdataProps.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");

    }

    @Test(priority = 2)
    public void verifyRegistrationAnAccountWithAllField() {

//        registerPage.enterFirstName(testdataProps.getProperty("firstName"));
//        registerPage.enterLastName(testdataProps.getProperty("lastName"));
//        registerPage.enterEmail(Utilities.generateTimestampWithEmail());
//        registerPage.enterTelephoneNumber(testdataProps.getProperty("telephoneNumber"));
//        registerPage.enterPassword(properties.getProperty("validPassword"));
//        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
//        registerPage.clickOnYesNewsLetterRadioButton();
//        registerPage.checkAgreementCheckBox();
        accountSuccessPage =  registerPage.registerWithAllFields(testdataProps.getProperty("firstName"),testdataProps.getProperty("lastName"),
                Utilities.generateTimestampWithEmail(),testdataProps.getProperty("telephoneNumber"),properties.getProperty("validPassword"));

        String accountSuccessHeading =  accountSuccessPage.retrieveAccountSuccessHeading();
        Assert.assertEquals(accountSuccessHeading, testdataProps.getProperty("accountSuccessfullyCreatedHeading"), "Account Success page is not displayed");

    }


    @Test(priority = 3)
    public void verifyRegistrationAnAccountWithExistingEmailAddress() {

//        registerPage.enterFirstName(testdataProps.getProperty("firstName"));
//        registerPage.enterLastName(testdataProps.getProperty("lastName"));
//        registerPage.enterEmail(properties.getProperty("validEmail"));
//        registerPage.enterTelephoneNumber(testdataProps.getProperty("telephoneNumber"));
//        registerPage.enterPassword(properties.getProperty("validPassword"));
//        registerPage.enterConfirmPassword(properties.getProperty("validPassword"));
//        registerPage.checkAgreementCheckBox();
//        registerPage.clickContinueButton();

        registerPage.registerWithAllFields(testdataProps.getProperty("firstName"),testdataProps.getProperty("lastName"),
                properties.getProperty("validEmail"),testdataProps.getProperty("telephoneNumber"),properties.getProperty("validPassword"));

        String duplicateEmailWarningText = registerPage.getAccountCreationWarningMessage();

        Assert.assertEquals(duplicateEmailWarningText, testdataProps.getProperty("duplicateEmailWarning"), "New Account Creation Failure page is not displayed");

    }

    @Test(priority = 4)
    public void verifyRegistrationAnAccountWithoutAnyField() {


        registerPage.clickContinueButton();

        String accountCreationWarning =   registerPage.getPrivacyPolicyWarning();
        Assert.assertTrue(accountCreationWarning.contains(testdataProps.getProperty("privacyPolicyWarning")), "Privacy Policy Warning message not displayed");

        String actualFirstNameWarningMessage = registerPage.getFirstNameWarningMessage();
        Assert.assertEquals(actualFirstNameWarningMessage, testdataProps.getProperty("firstNameWarning"), "FirstName Warning message not displayed");

        String actualLastNameWarningMessage = registerPage.getLastNameWarningMessage();
        Assert.assertEquals(actualLastNameWarningMessage, testdataProps.getProperty("lastNameWarning"), "LastName Warning message not displayed");


        String actualEmailWarningMessage = registerPage.getEmailWarningMessage();
        Assert.assertEquals(actualEmailWarningMessage, testdataProps.getProperty("emailWarning"), "Email Warning message not displayed");

        String actualPhoneWarningMessage = registerPage.getTelephoneWarningMessage();
        Assert.assertEquals(actualPhoneWarningMessage, testdataProps.getProperty("telephoneWarning"), "Phone Warning message not displayed");

        String actualPasswordWarningMessage = registerPage.getPasswordWarningMessage();
        Assert.assertEquals(actualPasswordWarningMessage, testdataProps.getProperty("passwordWarning"), "Password Warning message not displayed");

    }
}
package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base {
    LoginPage loginPage;
    AccountPage accountPage;
    public WebDriver driver;
    public LoginTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        driver = initializeBrowserAndOpenApplicationURL(properties.getProperty("browserName"));
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name="testdata")
    public Object[][] supplyTestData() {
//        Object[][] data = {{"vivekdarThu_Jan_16_08_18_14_EST_2025@gmail.com", "12345"}
//                ,{"vivekdarThu_Jan_16_08_19_55_EST_2025@gmail.com","12345"},
//                {"vivekdarThu_Jan_16_08_20_38_EST_2025@gmail.com","12345"}};
//
        return Utilities.readTestDataFromExcelSheet("Login");
    }
    @Test(priority = 1, dataProvider = "testdata")
    public void verifyLoginWithValidCredentials(String email, String password) {
//        loginPage.enterEmailAddress(email);
//        loginPage.enterPassword(password);
//        accountPage = loginPage.clickLoginButton();
        accountPage= loginPage.login(email,password);

        Assert.assertTrue(accountPage.getDisplayedStatusOfEditYourAccountInformationOption(),"Edit your account information");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials() {
//        loginPage.enterEmailAddress(Utilities.generateTimestampWithEmail());
//        loginPage.enterPassword(testdataProps.getProperty("invalidPassword"));
//        loginPage.clickLoginButton();
        loginPage.login(Utilities.generateTimestampWithEmail(),testdataProps.getProperty("invalidPassword"));
        String warningText = loginPage.retrieveEmailPasswordNotMatchingWarning();
        String expectedWarningMessage = testdataProps.getProperty("emailPasswordNotMatchWarning");
        Assert.assertTrue(warningText.contains(expectedWarningMessage), "Expected Warning Message is not displayed");

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword() {


//        loginPage.enterEmailAddress(Utilities.generateTimestampWithEmail());
//        loginPage.enterPassword(properties.getProperty("validPassword"));
//        loginPage.clickLoginButton();
        loginPage.login(Utilities.generateTimestampWithEmail(),properties.getProperty("validPassword"));
        String warningText = loginPage.retrieveEmailPasswordNotMatchingWarning();
        String expectedWarningMessage = testdataProps.getProperty("emailPasswordNotMatchWarning");
        Assert.assertTrue(warningText.contains(expectedWarningMessage), "Expected Warning Message is not displayed");

    }

    @Test (priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword() {

//        loginPage.enterEmailAddress(properties.getProperty("validEmail"));
//        loginPage.enterPassword(testdataProps.getProperty("invalidPassword"));
//        loginPage.clickLoginButton();
        loginPage.login(properties.getProperty("validEmail"),testdataProps.getProperty("invalidPassword"));
        String warningText = loginPage.retrieveEmailPasswordNotMatchingWarning();
        String expectedWarningMessage = testdataProps.getProperty("emailPasswordNotMatchWarning");
        Assert.assertTrue(warningText.contains(expectedWarningMessage), "Expected Warning Message is not displayed");

    }

    @Test (priority = 5)
    public void verifyLoginWithoutEmailAndPassword() {

        loginPage.clickLoginButton();
        String warningText = loginPage.retrieveEmailPasswordNotMatchingWarning();
        String expectedWarningMessage = testdataProps.getProperty("emailPasswordNotMatchWarning");
        Assert.assertTrue(warningText.contains(expectedWarningMessage), "Expected Warning Message is not displayed");
    }


}
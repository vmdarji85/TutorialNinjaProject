package com.tutorialsninja.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReport;
    ExtentTest extentTest;
    String testName;

    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.setUpExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName + " started executing");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.log(Status.PASS, testName + " successfully passed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.getStackTrace();
        }
        String destinationScreenshotFile = Utilities.captureScreenshot(driver, testName);
        extentTest.addScreenCaptureFromPath(destinationScreenshotFile);
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName + " got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + " got skipped");
    }


    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\extentreport\\extenetreport.html";
        File extentReport = new File(pathOfExtentReport);
        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
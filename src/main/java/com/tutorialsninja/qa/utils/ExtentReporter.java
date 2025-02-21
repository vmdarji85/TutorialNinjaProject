package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Properties;

public class ExtentReporter {
    public static ExtentReports setUpExtentReport() {
        ExtentReports extentReports = new ExtentReports();

        File extentReportFile = new File("target\\test-output\\extentreport\\extentreport.html");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialNinja Test Automation Report");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("MM-dd-yyyy hh:mm:ss");


        extentReports.attachReporter(sparkReporter);

        Properties properties = Utilities.getProperties("src/main/java/com/tutorialsninja/qa/config/config.properties");

        extentReports.setSystemInfo("Application URL", properties.getProperty("url"));
        extentReports.setSystemInfo("Browser Name", properties.getProperty("browserName"));
        extentReports.setSystemInfo("Email", properties.getProperty("validEmail"));
        extentReports.setSystemInfo("Password", properties.getProperty("validPassword"));
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Username", System.getProperty("user.name"));
        extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReports;

    }




}
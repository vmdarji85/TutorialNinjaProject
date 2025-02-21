package com.tutorialsninja.qa.base;

import com.tutorialsninja.qa.utils.Utilities;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Base {
    WebDriver driver ;
    public Properties properties;
    public Properties testdataProps;
//    public FileInputStream fis;
//    public XSSFWorkbook workbook;

    public Base() {
        properties = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
        try {
            FileInputStream fis = new FileInputStream(propFile);
            properties.load(fis);
        } catch (Throwable e) {
            e.getStackTrace();
        }

        testdataProps = new Properties();
        File testdataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
        try {
            FileInputStream testdataFis = new FileInputStream(testdataPropFile);
            testdataProps.load(testdataFis);
        } catch (Throwable t) {
            t.getStackTrace();
        }


    }

    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {

        if(browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if(browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
        driver.get(properties.getProperty("url"));

        return driver;
    }
    




}
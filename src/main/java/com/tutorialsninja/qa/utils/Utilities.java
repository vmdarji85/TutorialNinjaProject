package com.tutorialsninja.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Utilities {
    public static final int IMPLICIT_WAIT_TIME=10;
    public static final int PAGE_LOAD_TIME=5;

    public static long generateTimestampInEpoch() {
        Date date = new Date();
        return  date.getTime();
    }

    public static String generateTimestampWithEmail() {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
        return  "vivekdar" + timeStamp + "@gmail.com";
    }

    public static Object[][] readTestDataFromExcelSheet(String sheetName) {
       // File file  = new File(System.getProperty("user.dir") + "src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialNinjaTestData.xlsx");
      File file = new File("C:\\Users\\vivek\\OneDrive\\Desktop\\TutorialNinjaTestData.xlsx");
        XSSFWorkbook workbook = null;
        Object[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }


       // assert workbook != null;
        if (workbook != null) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rows = sheet.getLastRowNum();
            int cols = sheet.getRow(0).getLastCellNum();
            data = new Object[rows][cols];

            for (int i = 0; i < rows; i++) {
                XSSFRow row = sheet.getRow(i + 1);
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell = row.getCell(j);
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            data[i][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            data[i][j] = cell.getBooleanCellValue();
                            break;
                    }

                }
            }
        }

        return data;

    }


    public static Properties getProperties(String fileLocation ) {
        Properties properties = new Properties();
        File file = new File(fileLocation);
        try {
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;

    }

    public static String captureScreenshot(WebDriver driver , String testName) {

        String destinationScreenshotFile = "C:\\Users\\vivek\\OneDrive\\Documents\\Arun Sir\\HybridTestNGFramework\\TutorialNinjaProj\\Screenshot\\" + testName + ".png";
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcScreenshot, new File(destinationScreenshotFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return destinationScreenshotFile;
    }



}
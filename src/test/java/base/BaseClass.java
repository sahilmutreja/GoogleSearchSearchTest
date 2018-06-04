package base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Sahil Mutreja
 * @Desc: Base class provides all the members & functions to be made visible for test classes and page objects
 */
public class BaseClass {

    public WebDriver driver;
    public WebDriverWait wait;
    String browser, os;

    public static final Logger log = Logger.getLogger(BaseClass.class.getName());

    @BeforeClass
    @Parameters({"browser", "os"})
    public void setUp(@Optional("chrome") String browser, @Optional("windows") String os) {
        /*Initializing log4j.*/
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        log("Getting url environment variable and setting default value.");
        String url = System.getProperty("url", "https://www.google.com");

        log("Initializing webdriver.");
        this.browser = browser;
        this.os = os;
        driver = WebDriverFactory.init(browser, os);
        driver.manage().window().maximize();

        log("Navigating to Google enterSearchQuery page.");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10, 50);
    }

    @AfterClass(alwaysRun = true)
    public void endTest() {
        closeBrowser();
    }

    private void closeBrowser() {
        driver.quit();
        log("Browser Closed");
    }

    /**
     * Method to be used for getting the status of the test execution
     */
    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenShot(result.getMethod().getMethodName());
        }
    }

    /**
     * Method used for getting the screen capture with the name in a particular format
     * @param methodName
     */
    public void takeScreenShot(String methodName) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
                    + "/src/test/resources/failure_screencaptures/";
            File destFile = new File(String.format("%sFailure_ScreenCaptures_%s_%s_%s_%s.png", reportDirectory, os, browser, methodName, formater.format(calendar.getTime())));
            FileHandler.copy(srcFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
                    + "' height='100' width='100'/> </a");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logging method so that the same log is added in logger as well as in TestNG Report
     * @param data
     */
    public void log(String data) {
        log.info(data);
        Reporter.log(data + "\n");
    }

    /**
     * Common method to wait for visibility of elements in all test classes
     * @param element
     * @return
     */
    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


}

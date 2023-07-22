package com.seleniumsimplified.webdriver.screenshots;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static org.junit.Assert.fail;

public class Ex2_Answer_PersistScreenshotsTest {

    // this is the code for the video in section Taking Screenshots
    // TakesScreenshot Exercise 2

    @Before
    public void configureBrowser(){

        // uncomment this line if you want to use firefox
        // Driver.set(Driver.BrowserName.FIREFOX);
        // or chromedriver
        // Driver.set(Driver.BrowserName.GOOGLECHROME);

    }

    @Test
    public void testIfWeCanTakeScreenshots(){

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        //if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
        if(CanBrowser.takeScreenshots(driver)){
            System.out.println("Browser Can Take Screenshots");
        }else{
            System.out.println("Browser Can Not Take Screenshots");
        }
    }

    @Test
    public void persistOutputTypeFile() throws IOException {
        // OutputType.FILE works well testing on a local machine

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        if(CanBrowser.takeScreenshots(driver)){
            // create screenshot as FILE
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            Assert.assertTrue(tempImageFile.length() > 0);

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();

            // I could use existing name which is random
            String fileName = tempImageFile.getName();

            // or to help remind me when the screenshot was taken I can use
            // the current date time as a string
            String newImageFileName =
                        "file_" +
                        new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                        ".png";

            // create a File Object to use
            File testTempImage = new File(testTempDir, newImageFileName);

            // move screenshot to our local store
            FileUtils.moveFile(tempImageFile, testTempImage);
            Assert.assertTrue(testTempImage.length() > 0);
            //assertThat(testTempImage.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());

        }else{
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void persistOutputTypeBase64() throws IOException {
        // this works well testing on remote driver because
        // screenshot returned as a string to local machine

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        if(CanBrowser.takeScreenshots(driver)){

            // create screenshot
            TakesScreenshot snapper = (TakesScreenshot)driver;
            String tempImageFileAsBase64 = snapper.getScreenshotAs(OutputType.BASE64);

            Assert.assertTrue(tempImageFileAsBase64.length() > 0);

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = "base64_" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            byte[] imgBytes = Base64.getDecoder().decode(tempImageFileAsBase64);
            //File testTempImage = new File(System.getProperty("user.dir"), "base64image.png");
            File testTempImage = new File(testTempDir, newImageFileName);
            FileOutputStream osf = new FileOutputStream(testTempImage);
            osf.write(imgBytes);
            osf.flush();

            // screenshot in our local store
            Assert.assertTrue(testTempImage.length() > 0);

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
        }else{
            fail("Driver did not support screenshots");
        }
    }

    @Test
    public void persistOutputTypeBytes() throws IOException {
        // this works well testing on remote driver because
        // screenshot returned as a string to local machine

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        if(CanBrowser.takeScreenshots(driver)){

            // create screenshot
            TakesScreenshot snapper = (TakesScreenshot)driver;
            byte[] tempImageFileAsBytes = snapper.getScreenshotAs(OutputType.BYTES);

            Assert.assertTrue(tempImageFileAsBytes.length > 0);

            // create file and dirs to store for our test locally
            File testTempDir = createATempDirectoryForScreenshots();
            String newImageFileName = "bytes_" +
                    new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +
                    ".png";

            File testTempImage = new File(testTempDir,newImageFileName);
            FileOutputStream osf = new FileOutputStream(testTempImage);
            osf.write(tempImageFileAsBytes);
            osf.flush();

            // screenshot in our local store
            Assert.assertTrue(testTempImage.length() > 0);

            // use these lines in debug mode
            System.out.println("Temp file written to " + testTempImage.getAbsolutePath());
        }else{
            fail("Driver did not support screenshots");
        }
    }

    private File createATempDirectoryForScreenshots() {

        String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        File testTempDir = new File(System.getProperty("user.dir"), "screenshots");
        testTempDir.mkdirs();

        File currentExecDir = new File(testTempDir, currentTime);
        currentExecDir.mkdirs();

        return currentExecDir;
    }

    @After
    public void quitDriver(){
        Driver.quit();
    }

    /*
    Because these tests change the driver, when run from IDE
    We want to remember the current driver and restore after all tests are run
    */
    private static Driver.BrowserName rememberDriver;

    @BeforeClass
    public static void storeCurrentBrowser(){
        rememberDriver = Driver.currentDriver;
    }

    @AfterClass
    public static void restoreDriver(){
        Driver.set(rememberDriver);
    }
}

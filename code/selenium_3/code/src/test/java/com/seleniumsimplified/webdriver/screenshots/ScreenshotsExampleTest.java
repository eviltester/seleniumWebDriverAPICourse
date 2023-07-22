package com.seleniumsimplified.webdriver.screenshots;


import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class ScreenshotsExampleTest {

    WebDriver driver;

    @Before
    public void configureBrowser(){
        // early versions of these examples used to set the browser to Firefox
        // most browsers can take screenshots
        // uncomment this line if you want to use firefox specifically
        //Driver.set(Driver.BrowserName.FIREFOX);
    }

    @Test
    public void gotoPage() throws IOException {

        // if you want to use a normal driver then create this here
        // and comment out the Driver.get line below
        //driver = new FirefoxDriver();
        //driver.get(SiteUrls.rootUrl());

        //
        driver = Driver.get(SiteUrls.rootUrl());

        // cast the driver to a TakesScreenshot
        TakesScreenshot snapper = (TakesScreenshot)driver;

        // creates a temporary file which is a screenshot of current page
        File tempScreenshot =
                    snapper.getScreenshotAs(OutputType.FILE);

        // show the path for the screenshot
        System.out.println(tempScreenshot.getAbsolutePath());

        // create a directory called screenshots at top level
        // of the project if it does not exist
        File myScreenshotDirectory = new File(System.getProperty("user.dir"),
                                        "screenshots");
        myScreenshotDirectory.mkdirs();

        // Apache commons FileUtils is part of the WebDriver dependencies
        // so we can use this in our code to copy, move and delete files
        // copy the temporary file to the screenshots folder, using its temporary name
        // this can be useful if we don't really care about naming and don't want to
        // generate our own random file names
        FileUtils.copyFile(tempScreenshot,
                    new File(myScreenshotDirectory, tempScreenshot.getName()));

        // I am also going to move the temporary file to a specific name
        File myScreenshot = new File(myScreenshotDirectory,
                                "gotoPageScreen.png");

        // I will delete the file if it already exists - I don't really need to do this
        // because move should overwrite, but I like to be sure
        if(myScreenshot.exists()){
            FileUtils.deleteQuietly(myScreenshot);
        }

        // move temporary file and rename at same time
        FileUtils.moveFile(tempScreenshot, myScreenshot);

        // check file length to assert that there is content in file
        Assert.assertTrue(myScreenshot.length()>0);

        // using hamcrest for the assertion
        //assertThat(myScreenshot.length(), is(greaterThan(0L)));

        // open image file in browser
        // - mainly to support debugging, don't do this in production code
        driver.get("file://" + myScreenshot.getAbsolutePath());

    }

    @Test
    public void exampleByteArrayHandling() throws IOException {

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        TakesScreenshot snapper = (TakesScreenshot)driver;

        final byte[] tempImageFileAsBytes =
                    snapper.getScreenshotAs(OutputType.BYTES);

        File myScreenshotDirectory = new File(System.getProperty("user.dir"),
                "screenshots");
        myScreenshotDirectory.mkdirs();


        File testTempImage = new File(myScreenshotDirectory,
                                    "byteimage.png");
        FileOutputStream osf = new FileOutputStream(testTempImage);
        osf.write(tempImageFileAsBytes);
        osf.flush();

        // screenshot in our local store
        assertThat(testTempImage.length(), is(greaterThan(0L)));
    }

    @Test
    public void exampleBase64Handling() throws IOException {

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        TakesScreenshot snapper = (TakesScreenshot)driver;
        String tempImageFileAsBase64 =
                    snapper.getScreenshotAs(OutputType.BASE64);

        File myScreenshotDirectory = new File(System.getProperty("user.dir"),
                                    "screenshots");
        myScreenshotDirectory.mkdirs();

        byte[] imgBytes = Base64.getDecoder().
                            decode(tempImageFileAsBase64);

        File testTempImage = new File(myScreenshotDirectory,
                                "base64image.png");
        FileOutputStream osf = new FileOutputStream(testTempImage);
        osf.write(imgBytes);
        osf.flush();

        // screenshot in our local store
        assertThat(testTempImage.length(), is(greaterThan(0L)));
    }


    @After
    public void quitDriver(){

        // if you instantiated a browser using driver = new ChromeDriver
        // then use driver.quit instead
        // driver.quit();
        Driver.quit();
    }
}

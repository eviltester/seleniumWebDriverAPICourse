package com.seleniumsimplified.webdriver.screenshots;


import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.File;

import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class Ex1_Answer_CanWeTakeScreenshotsTest {

    // this is the code for the video in section Taking Screenshots
    // TakesScreenshot Exercise 1

    @Test
    public void demo_takes_screenshot_capability_doesNotAccuratelyReportCapabilities(){

        Driver.set(Driver.BrowserName.GOOGLECHROME);

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        HasCapabilities capabilityDriver = (HasCapabilities)driver;

        // Firefox can take screenshots, but the capabilities say that it can't
        // We can no longer use this approach to test if the browser takes screenshots
//        if(capabilityDriver.getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
//            System.out.println("Firefox Driver can take screenshots");
//        }else{
//            System.out.println("Firefox Driver can not take screenshots");
//        }
    }

    @Test
    public void canWeTakeAScreenshotExceptionStyle(){

        Driver.set(Driver.BrowserName.GOOGLECHROME);

        WebDriver driver = Driver.get(SiteUrls.rootUrl());

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            File tempImageFile = snapper.getScreenshotAs(OutputType.FILE);

            assertThat(tempImageFile.length(), is(greaterThan(0L)));

            // use these lines in debug mode
            System.out.println("Temp file written to " + tempImageFile.getAbsolutePath());
            Driver.get("File://"+ tempImageFile.getAbsolutePath());

        }catch(ClassCastException e){
            // if we cannot cast it to TakesScreenshot we will get a ClassCastException
            System.out.println(e);
            fail("Driver did not support screenshots");
        }

    }


    @Test
    public void htmlUnitDoesNotDoScreenshotsViaException(){

        WebDriver driver = new HtmlUnitDriver();
        driver.get(SiteUrls.rootUrl());

        try{
            TakesScreenshot snapper = (TakesScreenshot)driver;
            fail("Expected htmlunit to not cast to takes_screenshot");

        }catch(ClassCastException e){
            System.out.println(e);
        }

        driver.quit();
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

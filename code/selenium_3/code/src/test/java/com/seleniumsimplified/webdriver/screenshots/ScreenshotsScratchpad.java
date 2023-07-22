package com.seleniumsimplified.webdriver.screenshots;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotsScratchpad {

    // this is the base code for the section Taking Screenshots
    // video - Create TakeScreenshot Live Example
    // the final code from that video is in the
    // source code archive as ScreenshotsExampleTest

    @Test
    public void gotoPage() throws IOException {

        WebDriver driver;

        //driver = new ChromeDriver();
        //driver.get(SiteUrls.rootUrl());

        driver = Driver.get(SiteUrls.rootUrl());


        // driver.quit();
        Driver.quit();
    }
}

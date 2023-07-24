package com.seleniumsimplified.webdriver.screenshots;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotCaptureNotWrittenTest {
    /* created a screenshot test to include in CI -
     it just doesnt write the screenshot */

    @Test
    public void canCaptureScreenshot(){
        final WebDriver driver = Driver.get(SiteUrls.rootUrl());

        // cast the driver to a TakesScreenshot
        TakesScreenshot snapper = (TakesScreenshot)driver;

        // creates a base64 string version of screenshot of current page
        String tempScreenshot =
                snapper.getScreenshotAs(OutputType.BASE64);

        Assert.assertTrue("expect a screenshot", tempScreenshot.length() > 0);

    }
}

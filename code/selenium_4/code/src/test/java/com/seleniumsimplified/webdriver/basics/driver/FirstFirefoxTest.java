package com.seleniumsimplified.webdriver.basics.driver;

import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertTrue;

public class FirstFirefoxTest {

    @Test
    /*
      * Physical browsers have to be closed, otherwise you will
      * have a bunch of browsers open on your screen.
      *
      * .close to close single window and browser if it is the last window
      * .quit to close browser and all windows
      */
    public void firefoxIsSupportedByWebDriver(){

        // For versions of firefox v47.1 and lower
        // https://www.mozilla.org/en-US/firefox/organizations/all/
        // you have to instantiate FirefoxDriver like this
        //FirefoxOptions options = new FirefoxOptions().setLegacy(true);
        //WebDriver driver = new FirefoxDriver(options);

        /**
         * if you do not add the driver to the path then you need to configure the location
         */
        //String currentDir = System.getProperty("user.dir");
        //String driverLocation = currentDir + "/../tools/marionette/geckodriver.exe";
        //System.setProperty("webdriver.firefox.driver", driverLocation);

        WebDriver driver = new FirefoxDriver();

        driver.get(SiteUrls.rootUrl());

        assertTrue(driver.getTitle().contains(
                "Test Pages"));

        driver.close();

        // for early version combinations of Firefox and WebDriver we didn't need
        // .quit - I have added this line for the combination of WebDriver 2.31.0
        // and Firefox 20. According to the API we should not need to do a .quit
        // after a .close if there was only 1 window open. But sometimes the browser
        // version advances ahead of the WebDriver version and minor incompatibilities
        // happen.
        // Added the line below because of incompatibilite between 2.31.0 and Firefox 20
        // where a single window browser does not close when run from the IDE.
        // Update 20191108
        // Firefox driver now only wants either a .close or a .quit, but not both
        //driver.quit();
    }

}

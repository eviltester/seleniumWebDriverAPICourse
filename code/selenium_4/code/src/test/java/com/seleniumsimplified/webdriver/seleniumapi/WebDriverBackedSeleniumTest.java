package com.seleniumsimplified.webdriver.seleniumapi;


// the import changed at some point, but only caused issues in 2_42_2
// changed from import org.openqa.selenium.WebDriverBackedSelenium;
// to import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

//import com.thoughtworks.selenium.Selenium;
//import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
//import org.junit.Assert;
//import org.junit.Test;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.Assert;
import org.junit.Test;

public class WebDriverBackedSeleniumTest {

    @Test
    public void theTestHasMoved(){
        // because the  WebDriverBackedSelenium has been deprecated by the Selenium project
        // I have moved the code out of the main code base and into a separate
        // github repo
        // the original code has been left here but commented out
        // this avoid the deprecation messages confusing anyone approaching this code for
        // the first time
        //
        // You can find the code on github:
        // https://github.com/eviltester/webDriverBackedExample
        //
        // and the main test code java file is at:
        // https://github.com/eviltester/webDriverBackedExample/blob/master/src/test/java/com/seleniumsimplified/webdriver/seleniumapi/WebDriverBackedSeleniumTest.java

        Assert.assertTrue("The test has moved", true);
    }


//    @Test
//    /*
//      * Should be able to inject a driver into the WebDriverBackedSelenium object
//      * and then use the Selenium interface.
//      */
//    public void SeleniumOneAPI_IsSupportedByWebDriverFirefox(){
//
//        WebDriver driver = new FirefoxDriver();
//
//        String baseUrl = "http://www.compendiumdev.co.uk";
//        Selenium selenium = new WebDriverBackedSelenium(driver, baseUrl);
//
//        selenium.open("/selenium");
//
//
//        Assert.assertEquals(true, selenium.getTitle().startsWith("Selenium Simplified"));
//
//        // according to official docs need to use .stop() otherwise JVM continues to run
//        selenium.stop();
//
//
//        // after a quit, you cannot use the Firefox driver
//        driver.quit();
//    }
}

package com.seleniumsimplified.webdriver.basics.interrogate;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DriverInterrogateTest {

    @Test
    public void driverLevelPageInterrogateMethods(){

        WebDriver driver;

        final String theTestPageURL =
                "//www.compendiumdev.co.uk/selenium/basic_web_page.html";

        driver = Driver.get();

        driver.navigate().to("http:" + theTestPageURL);

        // in the videos you might see these the wrong way round
        // always use expected, actual
        assertEquals("Basic Web Page Title", driver.getTitle());

        // Changed this from
        // assertEquals(theTestPageURL, driver.getCurrentUrl());
        // to cope with the pages being served by either http or https
        assertTrue( driver.getCurrentUrl().endsWith(theTestPageURL));

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("A paragraph of text"));

        System.out.println(pageSource);

    }

}

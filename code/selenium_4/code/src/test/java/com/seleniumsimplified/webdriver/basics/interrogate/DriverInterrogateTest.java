package com.seleniumsimplified.webdriver.basics.interrogate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DriverInterrogateTest {

    @Test
    public void driverLevelPageInterrogateMethods(){

        WebDriver driver = Driver.get();

        driver.navigate().to(SiteUrls.basicWebPageUrl());

        // in the videos you might see these the wrong way round
        // always use expected, actual
        assertEquals("Basic Web Page Title", driver.getTitle());

        // Changed this from
        // assertEquals(theTestPageURL, driver.getCurrentUrl());
        // to cope with the pages being served by either http or https
        // and to cope with different domains and the legacy unstyled pages had this url
        //assertTrue( driver.getCurrentUrl().endsWith("basic_web_page.html"));
        // new styled pages have this url
        assertTrue( driver.getCurrentUrl().endsWith("basic-web-page-test.html"));

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("A paragraph of text"));

        System.out.println(pageSource);

    }

}

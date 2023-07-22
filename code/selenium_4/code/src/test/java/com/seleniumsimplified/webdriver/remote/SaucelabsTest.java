package com.seleniumsimplified.webdriver.remote;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class SaucelabsTest {

    public static WebDriver driver=null;

    @BeforeClass
    public static void setupSauce(){

        // prior to v 3.14 we used capabilities static definition for firefox
        //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        // now set them specifically
        DesiredCapabilities capabilities = new DesiredCapabilities("firefox", "", Platform.ANY);
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setCapability("platform", Platform.MAC);


        try {
            // add url to environment variables to avoid releasing with source
            String sauceURL = System.getenv("SAUCELABS_URL");
            driver = new RemoteWebDriver(
                    new URL(sauceURL),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleInteraction(){
       driver.get(SiteUrls.basicHtmlFormPageUrl());

        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse("Starts not selected",
                checkBox1.isSelected());

        checkBox1.click();

        assertTrue("Click selects",
                checkBox1.isSelected());
    }



    @AfterClass
    public static void stopSauce(){
        driver.quit();
    }
}

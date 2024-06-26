package com.seleniumsimplified.webdriver.basics.navigation;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;


public class NavigationExampleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        //driver = new FirefoxDriver();
        driver = Driver.get();
    }

    @Test
    public void navigateWithNavigateTo(){
        driver.navigate().to(SiteUrls.searchPageUrl());
        assertTrue(driver.getTitle().
                startsWith("Selenium Simplified Search Engine"));
    }

    @AfterClass
    public static void quitDriver(){
        //driver.quit();
    }
}

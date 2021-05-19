package com.seleniumsimplified.webdriver.basics.navigation;

import com.seleniumsimplified.webdriver.manager.Driver;
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
        driver.navigate().to(
                "http://www.compendiumdev.co.uk/selenium/search.php");
        assertTrue(driver.getTitle().
                startsWith("Selenium Simplified Search Engine"));
    }

    @AfterClass
    public static void quitDriver(){
        //driver.quit();
    }
}

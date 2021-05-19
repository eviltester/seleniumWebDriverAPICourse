package com.seleniumsimplified.webdriver.basics.manipulate.oldwindows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowsExampleTest {


    @Before
    public void quitToRestart(){
        // I need to make sure that only one window open so...
        Driver.quit();
    }

    @Test
    public void switchToNewWindow(){

        // Current bug open with chrome driver
        // http://code.google.com/p/chromedriver/issues/detail?id=107

        WebDriver driver = Driver.get(
                    "http://www.compendiumdev.co.uk/selenium/frames");

        String framesWindowHandle = driver.getWindowHandle();
        assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
        assertEquals("Expected a New Window opened", 2, driver.getWindowHandles().size());

        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";

        for(String aHandle : myWindows){
            if(!framesWindowHandle.contentEquals(aHandle)){
                newWindowHandle = aHandle; break;
            }
        }

        driver.switchTo().window(newWindowHandle);

        // for Marionette Geckodriver need to switchTo defaultContent to check title
        driver.switchTo().defaultContent();

        assertTrue("Expected Selenium Simplified site",
                driver.getTitle().contains("Selenium Simplified"));
    }

    @After
    public void quitToClean(){

        // I need to make sure that only one window open for other tests in CI
        Driver.quit();
    }
}

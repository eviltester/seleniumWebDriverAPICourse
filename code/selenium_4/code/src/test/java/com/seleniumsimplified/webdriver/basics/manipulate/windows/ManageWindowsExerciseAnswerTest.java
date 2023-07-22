package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageWindowsExerciseAnswerTest {

    /*

    The page 'styled/css-media-queries.html' renders differently
    depending on the width of the browser.

    e.g. if the page is less than 1800 pixels then the `h2`
    element with class `s1800` should not be visible.

    Write some test code to resize the browser and
    check that the various `h2` elements disappear

    | class | vanishes at less than |
    |---|---|
    | s1800 | 1800px |
    | s1600 | 1600px |
    | s1400 | 1400px |
    | s1200 | 1200px |
    | s1000 | 1000px |
    | s800 | 800px |
    | s600 | 600px |
    | s400 | 400px |

    Note: Not all browsers may resize low enough.

     */

    @Test
    public void resizeVanish(){

        String pageUrl = SiteUrls.windowSizeMediaQueriesPageUrl();
        WebDriver driver;
        //driver = new ChromeDriver();
        //driver.get(pageUrl);
        driver = Driver.get(pageUrl);

        // set to full size
        driver.manage().window().maximize();

        final Dimension size = driver.manage().window().getSize();

        // only do the assertions for the dimensions available
        WebElement elem;
        if(size.width>1800){
            elem = driver.findElement(By.cssSelector("h2.s1800"));
            Assert.assertTrue(elem.isDisplayed());
            driver.manage().window().setSize(new Dimension(1700, size.height));
            Assert.assertFalse(elem.isDisplayed());
        }

        if(size.width>1600){
            elem = driver.findElement(By.cssSelector("h2.s1600"));
            Assert.assertTrue(elem.isDisplayed());
            driver.manage().window().setSize(new Dimension(1500, size.height));
            Assert.assertFalse(elem.isDisplayed());
        }

        // etc.
        driver.quit();
        //Driver.quit();
    }
}

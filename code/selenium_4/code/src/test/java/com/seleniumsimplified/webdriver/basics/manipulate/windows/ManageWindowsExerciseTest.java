package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageWindowsExerciseTest {

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

        String pageUrl = "https://testpages.herokuapp.com/styled/css-media-queries.html";
        WebDriver driver;
        //driver = new ChromeDriver()
        //driver.get(pageUrl);
        driver = Driver.get(pageUrl);

        // etc.
        //driver.quit();
        Driver.quit();
    }
}

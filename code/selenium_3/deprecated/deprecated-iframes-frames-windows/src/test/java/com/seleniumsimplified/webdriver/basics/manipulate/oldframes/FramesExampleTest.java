package com.seleniumsimplified.webdriver.basics.manipulate.oldframes;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class FramesExampleTest {

    private static WebDriver driver;


    @Before
    public void setup(){

        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");
    }

    @Test
    public void switchToFrameExample(){

        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/frames");

        assertEquals("Frameset Example Title (Example 6)",driver.getTitle());

        driver.switchTo().frame("menu");

        driver.findElement(By.cssSelector("a[href='frames_example_1.html']")).click();

        String titleForExample1 = "Frameset Example Title (Example 1)";

        // added for Marionette Driver to force moving frame
        // not needed for other drivers but it does no harm for other drivers
        // Note - this is only needed if we are checking the title, not for
        // any other action
        driver.switchTo().defaultContent();

        new WebDriverWait(driver,Driver.DEFAULT_TIMEOUT_SECONDS).
                until(ExpectedConditions.titleIs(titleForExample1));

        assertEquals(titleForExample1,driver.getTitle());
    }
}

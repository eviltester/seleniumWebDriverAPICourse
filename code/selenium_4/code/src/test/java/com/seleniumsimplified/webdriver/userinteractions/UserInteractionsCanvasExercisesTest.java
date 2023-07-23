package com.seleniumsimplified.webdriver.userinteractions;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class UserInteractionsCanvasExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.canvasScribblePageUrl());
    }

    @Before
    public void resetPage(){
        driver.navigate().refresh();

        // Added additional Synchronisation for Opera as the refresh did
        // not block in any way making the test
        // intermittent, but this is a good example that sometimes
        // extra sync is required, but doesn't impact the speed or readability
        // of the code
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("canvas")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("keyeventslist")));

        // Appium does not support ExpectedConditions.elementToBeClickable @20160608
        // So after checking that the elements are present, we will assume we are synchronised
        if(Driver.currentDriver == Driver.BrowserName.APPIUM){
            return;
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.id("canvas")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("keyeventslist")));

        // user interactions can be intermittent
        // so click on the html to force focus to the page
        // but this workaround no longer works on Chrome in 2.46.0 and driver 2.16
        if(Driver.currentBrowser() != Driver.BrowserName.GOOGLECHROME) {
            driver.findElement(By.tagName("html")).click();
        }
    }

    @Test
    public void drawSomethingOnCanvas(){
        WebElement canvas = driver.findElement(By.id("canvas"));
        WebElement eventList = driver.findElement(By.id("keyeventslist"));

        int eventCount = eventList.findElements(By.tagName("li")).size();

        new Actions(driver).
                // click doesn't do it, need to click and hold
                //click(canvas).
                clickAndHold(canvas).
                moveByOffset(10,10).
                release().
                perform();

        assertTrue("we should have had some draw events",
                eventCount < eventList.findElements(By.tagName("li")).size());

    }
}

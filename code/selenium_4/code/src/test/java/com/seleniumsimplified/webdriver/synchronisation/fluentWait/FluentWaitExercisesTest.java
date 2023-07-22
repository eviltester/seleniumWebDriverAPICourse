package com.seleniumsimplified.webdriver.synchronisation.fluentWait;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FluentWaitExercisesTest {

    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.javascriptCountdownPageUrl());
    }


    @Before
    public void setupTest(){

        driver.navigate().refresh();

        countdown = driver.findElement(By.id("javascript_countdown_time"));

        new WebDriverWait(driver,Duration.ofSeconds(10)).
                until(ExpectedConditions.visibilityOf(countdown));
    }

    @Test
    public void waitForWebElementFluently(){

        // Fluent wait constructor has changed for version 3.12
        // we used to use
        // withTimeout(20, TimeUnit.SECONDS).
        //                pollingEvery(10,TimeUnit.MILLISECONDS).
        // now we use Duration
        String theTime = new FluentWait<WebElement>(countdown).
                withTimeout(Duration.ofSeconds(20)).
                pollingEvery(Duration.ofMillis(100)).
                until(new Function<WebElement, String>() {
                    @Override
                    public String apply(WebElement element) {
                        // amended from
                        // return element.getText().endsWith("04") ? element.getText() : null;
                        // because on grid, the time between the two getText calls was enough
                        // to allow the time to have advanced further than necessary
                        // so store the getText and use that in the condition and return
                        String elementText = element.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    @Test
    public void waitForTimeWithWebDriverWaitFunction(){

        String theTime = new WebDriverWait(driver,Duration.ofSeconds(20),Duration.ofMillis(100)).
                until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));

                        // amended from
                        // return countDown.getText().endsWith("04") ? countDown.getText() : null;
                        // because on grid, the time between the two getText calls was enough
                        // to allow the time to have advanced further than necessary
                        // so store the getText and use that in the condition and return
                        String elementText = countDown.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    @Test
    public void waitForTimeWithWebDriverWaitExpectedCondition(){

        String theTime = new WebDriverWait(driver,Duration.ofSeconds(20),Duration.ofMillis(100)).
                until(new ExpectedCondition<String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        WebElement countDown = driver.findElement(By.id("javascript_countdown_time"));

                        // amended from
                        // return countDown.getText().endsWith("04") ? countDown.getText() : null;
                        // because on grid, the time between the two getText calls was enough
                        // to allow the time to have advanced further than necessary
                        // so store the getText and use that in the condition and return
                        String elementText = countDown.getText();
                        return elementText.endsWith("04") ? elementText : null;
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }


}

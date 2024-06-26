package com.seleniumsimplified.webdriver.synchronisation.conditions;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverWaitFunctionAndExpectedConditionsExampleTest {

    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.javascriptCountdownPageUrl());
    }

    @Test
    public void returnAStringFromWebDriverWaitFunction(){

        String theTime = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(100)).
                until(new Function<WebDriver, String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        return "01:01:04";
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    @Test
    public void returnAStringFromWebDriverWaitExpectedCondition(){

        String theTime = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(100)).
                until(new ExpectedCondition<String>() {
                    @Override
                    public String apply(WebDriver driver) {
                        return "01:01:04";
                    }
                }
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }


    @Test
    public void returnAStringFromWebDriverWaitExpectedConditionAsMethod(){

        String theTime = new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(100)).
                until(timeHasChangedTo("01:01:04")
                );

        assertEquals("Expected a different time", "01:01:04", theTime);
    }

    private ExpectedCondition<String> timeHasChangedTo(final String timeToReturn) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                return timeToReturn;
            }
        };
    }

}

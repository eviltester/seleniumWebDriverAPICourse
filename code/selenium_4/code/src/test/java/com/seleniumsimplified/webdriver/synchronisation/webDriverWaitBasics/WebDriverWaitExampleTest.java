package com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class WebDriverWaitExampleTest {

    @Test
    public void exampleUsingExpectedConditions(){

        WebDriver driver;

        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    @Test
    public void exampleWithSleepTime(){

        WebDriver driver;

        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());

        new WebDriverWait(driver,Duration.ofSeconds(10),Duration.ofMillis(50)).until(
                ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }
}

package com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.fail;

public class WebDriverWaitFaqTest {

    @Test
    public void whatIfTheExpectedConditionDoesNotMatch(){

        WebDriver driver;

        driver = Driver.get("https://testpages.herokuapp.com/basic_html_form.html");

        try{
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.titleIs("HTML Form Elementals"));

            fail("Expected a org.openqa.selenium.TimeoutException");

        }catch(TimeoutException e){
            // ignore the timeout exception
        }
    }
}

package com.seleniumsimplified.webdriver.basics.manipulate.alerts;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;


import static org.junit.Assert.*;

public class AlertHandlingFAQsTest {

    private WebDriver driver;

    @Before
    public void setup(){
        // I could instantiate a driver directly if I wasn't using a Driver manager
        //driver = new ChromeDriver();
        driver = Driver.get(SiteUrls.basicAlertsPageUrl());
    }


    // try to switch to an alert if not present throws an exception
    private boolean doesAlertExist() {
        try{
            driver.switchTo().alert();
            return true;
        }catch(Exception e){
            System.out.println("Exception thrown: " + e.getClass().getName());
            return false;
        }
    }


    @Test
    public void checkIfAlertExists(){

        // try to switch to an alert if not present throws an exception

        driver.get(SiteUrls.basicAlertsPageUrl());

        // not triggered any alerts yet
        Assert.assertFalse(doesAlertExist());

        // triggered alert
        driver.findElement(By.id("alertexamples")).click();

        // alert should exist
        Assert.assertTrue(doesAlertExist());

        // close the alert
        driver.switchTo().alert().accept();

        // alert should have closed
        Assert.assertFalse(doesAlertExist());
    }


    @Test
    public void whyIsMyAlertNotWorking(){
        // Q: Why is my alert not working?
        // A: Because it is not a div

        driver.get(SiteUrls.fakeAlertsPageUrl());

        driver.findElement(By.id("fakealert")).click();

        By okButton = By.id("dialog-ok");

        WebElement button = driver.findElement(okButton);

        button.click();

        // check that the text is in the DOM but not displayed
        // because the 'alert' has been closed
        Assert.assertEquals(false,
                driver.findElement(By.id("dialog-text")).isDisplayed());
    }


    @Test
    public void whatDifferenceDismissAccept(){

        driver.get(SiteUrls.basicAlertsPageUrl());

        //What is the difference between `dismiss` and `accept`?

        // For an `alert`, a `dismiss`
        // and an `accept` have the same effect.
        driver.findElement(By.id("alertexamples")).click();
        driver.switchTo().alert().dismiss();

        driver.findElement(By.id("alertexamples")).click();
        driver.switchTo().alert().accept();

        // For a `confirm` a `dismiss` generates a `false` response,
        // and `accept` generates a `true` response.

        driver.findElement(By.id("confirmexample")).click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals("false",
                driver.findElement(By.id("confirmretval")).getText());

        driver.findElement(By.id("confirmexample")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals("true",
                driver.findElement(By.id("confirmretval")).getText());


        // For a `prompt`, a `dismiss` returns `null`,
        // and `accept` returns the text in the prompt.
        driver.findElement(By.id("promptexample")).click();
        driver.switchTo().alert().dismiss();
        Assert.assertEquals("null",
                driver.findElement(By.id("promptretval")).getText());

        driver.findElement(By.id("promptexample")).click();
        driver.switchTo().alert().accept();
        Assert.assertEquals("change me",
                driver.findElement(By.id("promptretval")).getText());

    }


    // Q: what happens if I send text to alert?
    // A: an exception is thrown
    // A: ElementNotInteractableException in Firefox
    // A: ElementNotInteractableException in Chrome
    @Test
    public void basicAlertHandlingSendKeysExceptionTest(){

        driver.get(SiteUrls.basicAlertsPageUrl());

        driver.findElement(By.id("alertexamples")).click();

        assertEquals("I am an alert box!",
                driver.switchTo().alert().getText());

        try{
            driver.switchTo().alert().sendKeys("Hello keys Accept");
            fail("expected an Exception thrown");
        }catch(Exception e){
            System.out.println("Exception thrown: " + e.getClass().getName());
            assertTrue("Expected to get an exception", true);
        }

    }

    @After
    public void tearDown(){

        //close any rogue alerts
        if(doesAlertExist()){
            driver.switchTo().alert().dismiss();
        }

        // if I instantiated a ChromeDriver or FirefoxDriver
        // above then I should close it here
        //driver.close();
    }
}

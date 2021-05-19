package com.seleniumsimplified.webdriver.basics.driver;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * FAQ - what happens if, based on fundamental information
 */
public class FundamentalWhatHappensIfTest {

    @Test
    public void whatHappensIfIQuitThenClose(){
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

        driver.quit();
        try{
            driver.close();
            fail("expected an Exception - NoSuchSessionException");
        }catch(NoSuchSessionException e){
            // we used to get an UnreachableBrowserException,
            // at July 2016 we get a SessionNotFoundException - but that is deprecated so I'm not going to assert on that
            // update 20191108 NoSuchSessionException is now used
            // added that as the exception
            assertTrue("We should get an Exception if we close after quiting", true);
        }
    }


    @Test
    public void whatHappensIfICloseWithNothingOpen(){
        WebDriver driver = new FirefoxDriver();

        driver.get("http://www.compendiumdev.co.uk/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

        driver.close();

        try {
            driver.close();
            fail("Driver should not allow you to close when there is no browser to close");
        }catch(NoSuchSessionException e){
            // firefox driver no longer allows you to close
            // if there is no browser tab to close
        }

        // earlier versions of FirefoxDriver allowed you to close
        // as many times as you wanted
//        assertTrue("Nothing happens", true);
//
//        driver.close();
//        driver.close();
//        driver.close();
//        driver.close();
//        driver.close();
//        driver.close();
//        driver.close();
//        driver.close();
//
//        assertTrue("See, Nothing happens, I can close as often as I want", true);
    }

    @Test
    public void whatHappensIfIForgetToNavigate(){
        WebDriver driver = new FirefoxDriver();

        //Firefox used to have an empty title
        //assertTrue("Empty Title", driver.getTitle().isEmpty());
        // 20191108 Firefox now returns an ""
        // Assert.assertEquals("Problem loading page", driver.getTitle());
        Assert.assertEquals("", driver.getTitle());

        driver.quit();

    }
}

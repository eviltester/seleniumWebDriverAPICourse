package com.seleniumsimplified.webdriver.synchronisation.fluentWait;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FluentWaitForWebElementExampleTest {

    /**
     * Fluent wait can wait for WebElements not just driver
     * see http://code.google.com/p/guava-libraries/wiki/FunctionalExplained
     * for more info on Predicate and Function
     */
    private static WebDriver driver;
    WebElement countdown;

    @BeforeClass
    public static void setup(){
        driver = Driver.get("http://compendiumdev.co.uk/selenium/javascript_countdown.html");
    }


    @Before
    public void setupTest(){

        driver.navigate().refresh();

        countdown = driver.findElement(By.id("javascript_countdown_time"));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(countdown));
    }

    @Test
    public void waitForWebElementFluently(){

        // function can return anything and take anything
        // so the first argument is the parameter and the
        // second is the return type
        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100,TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().endsWith("04");
                    }
                }
                );
    }

/*
    20170922 - WebDriver no longer supports Predicate, but now uses Function - see the test below Predicate for new usage

  */
//    @Test
//    public void waitForWebElementFluentlyPredicate(){
//
//        // predicate always returns true or false
//        // the generic is to define the argument
//        // to apply predicate function to
//        new FluentWait<WebElement>(countdown).
//                withTimeout(10, TimeUnit.SECONDS).
//                pollingEvery(100, TimeUnit.MILLISECONDS).
//                until(new Predicate<WebElement>() {
//                    @Override
//                    public boolean apply(WebElement element) {
//                        return element.getText().endsWith("04");
//                    }
//                }
//                );
//    }


    @Test
    public void waitForWebElementFluentlyPredicate(){

        // predicate always returns true or false
        // the generic is to define the argument
        // to apply predicate function to
        new FluentWait<WebElement>(countdown).
                withTimeout(10, TimeUnit.SECONDS).
                pollingEvery(100, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                          @Override
                          public Boolean apply(WebElement element) {
                              return element.getText().endsWith("04");
                          }
                      }
                );
    }

}

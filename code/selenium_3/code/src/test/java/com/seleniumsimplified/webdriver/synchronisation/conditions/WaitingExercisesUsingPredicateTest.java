package com.seleniumsimplified.webdriver.synchronisation.conditions;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.function.Function;
import static org.junit.Assert.assertNotEquals;

public class WaitingExercisesUsingPredicateTest {

    static WebDriver driver;


    /*****************************
     * Using a Function
     ****************************/
    /*
     TODO: WebDriver has changed so that it no longer takes a predicate and now takes a Function

     - still need to update the videos
     

     this example shows the 'new' approach using a Function instead of a Predicate
     All that has changed is that instead of:

     `implements Function<WebDriver, Boolean>`

     we now have

     `implements Function<WebDriver, Boolean>`

     And the apply method returns `Boolean` rather than `boolean`

     and the return value from `titleDoesNotContain`  changed to:

     `Function<WebDriver, Boolean>` to match the declaration of the class `TitleDoesNotContain`


    */
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingClass(){

        driver = Driver.get(SiteUrls.redirectPageUrl());

        driver.findElement((By.id("delaygotobasic"))).click();

        new WebDriverWait(driver,8).until(
                new TitleDoesNotContain("Redirects"));

        // we don't need to assert because if the wait fails it throws an exception
    }




    private class TitleDoesNotContain implements Function<WebDriver, Boolean> {
        private String titleExcludes;

        public TitleDoesNotContain(String notContainThisString) {
            this.titleExcludes = notContainThisString;
        }

        @Override
        public Boolean apply(WebDriver webDriver) {
            return !webDriver.getTitle().contains(this.titleExcludes);
        }
    }


    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethod(){

        driver = Driver.get(SiteUrls.redirectPageUrl());
        String originalTitle = driver.getTitle();

        driver.findElement((By.id("delaygotobasic"))).click();

        new WebDriverWait(driver,8).until(titleDoesNotContain("Redirects"));

        // we don't really need to assert because if the wait fails it throws an exception
        assertNotEquals(originalTitle, driver.getTitle());
    }

    private Function<WebDriver, Boolean> titleDoesNotContain(String stringNotInTitle) {
        return new TitleDoesNotContain(stringNotInTitle);
    }

}

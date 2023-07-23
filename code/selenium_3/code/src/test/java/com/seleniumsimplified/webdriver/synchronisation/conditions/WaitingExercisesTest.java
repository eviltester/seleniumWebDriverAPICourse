package com.seleniumsimplified.webdriver.synchronisation.conditions;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class WaitingExercisesTest {

    static WebDriver driver;

    @Test
    public void canReturnAWebElementInsteadOfABooleanUsingAnonymousClass(){

        driver = Driver.get(SiteUrls.basicAjaxPageUrl());

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // Wait for Java to be available to select
        WebElement elly = new WebDriverWait(driver,10).until(
                new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {

                        WebElement eli = webDriver.findElement(
                                By.cssSelector("option[value='23']"));

                        if(eli.isDisplayed()){
                            return eli;
                        }else{
                            return null;
                        }
                    }
                }
        );

        // select java
        elly.click();
        assertEquals("Expected Java", "Java", elly.getText());

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }


    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingClass(){

        driver = Driver.get(SiteUrls.redirectPageUrl());

        driver.findElement((By.id("delaygotobasic"))).click();

        new WebDriverWait(driver,8).until(
                new TitleDoesNotContain("Redirects"));
    }


    private class TitleDoesNotContain implements ExpectedCondition<String> {
        private String titleExcludes;

        public TitleDoesNotContain(String notContainThisString) {
            this.titleExcludes = notContainThisString;
        }

        @Override
        public String apply(WebDriver webDriver) {
            String title = webDriver.getTitle();

            if(title.contains(this.titleExcludes)){
                return null;
            }else{
                return title;
            }
        }
    }


    // Note: I am also assigning wait to a variable
    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethod(){

        WebDriverWait wait;

        driver = Driver.get(SiteUrls.redirectPageUrl());
        wait = new WebDriverWait(driver,8);

        driver.findElement((By.id("delaygotobasic"))).click();

        // we don't need to assert because if the wait fails it throws an exception
        wait.until(titleDoesNotContainF("Redirects"));
    }

    private ExpectedCondition<String> titleDoesNotContainF(String stringNotInTitle) {
        return new TitleDoesNotContain(stringNotInTitle);
    }


    @Test
    public void customExpectedConditionForTitleDoesNotContainUsingMethodAC(){

        WebDriverWait wait;

        driver = Driver.get(SiteUrls.redirectPageUrl());
        wait = new WebDriverWait(driver,8);

        driver.findElement((By.id("delaygotobasic"))).click();

        // we don't need to assert because if the wait fails it throws an exception
        wait.until(titleDoesNotContainAC("Redirects"));
    }

    private ExpectedCondition<String> titleDoesNotContainAC(final String stringNotInTitle) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver webDriver) {
                String title = webDriver.getTitle();

                if(title.contains(stringNotInTitle)){
                    return null;
                }else{
                    return title;
                }
            }
        };
    }
}

package com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class SynchronisationWithWebDriverWaitTest {

    private WebDriver driver;

    @Test
    // Exercise: Feel The Pain
    public void chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample(){

        try{
            WebDriver driver;

            driver = Driver.get(SiteUrls.basicAjaxPageUrl());

            // select Server
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            // then select Java
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // Submit the form
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());

        }catch(Exception e){
            assertTrue("Expected some sort of exception thrown",true);
        }
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        startBrowserAndSelectServer();

        // wait until the ajax symbol has gone
        // because then the drop down has populated
        new WebDriverWait(driver,10).until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.id("ajaxBusy")));

        selectJavaSubmitFormAndCheckResult();


    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionPresentExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is present
        new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();

    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionVisibleExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is visible
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();

    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnOptionClickableExample(){

        startBrowserAndSelectServer();

        // wait until the option I want to click is clickable
        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("option[value='23']")));

        // then select Java
        selectJavaSubmitFormAndCheckResult();

    }

    private void startBrowserAndSelectServer() {
        driver = Driver.get(SiteUrls.basicAjaxPageUrl());

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();
    }

    private void selectJavaSubmitFormAndCheckResult() {
        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }



}

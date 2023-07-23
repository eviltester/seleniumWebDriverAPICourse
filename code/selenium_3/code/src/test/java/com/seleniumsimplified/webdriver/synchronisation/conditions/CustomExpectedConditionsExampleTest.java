package com.seleniumsimplified.webdriver.synchronisation.conditions;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class CustomExpectedConditionsExampleTest {

    @Test
    public void customSynchronisationWithExpectedCondition(){

            WebDriver driver;

            driver = Driver.get(SiteUrls.basicAjaxPageUrl());

            // select Server
            WebElement categorySelect = driver.findElement(By.id("combo1"));
            categorySelect.findElement(By.cssSelector("option[value='3']")).click();

            new WebDriverWait(driver,10).until(
                    new SelectContainsText(By.id("combo2"),"Java")
            );

            // then select Java
            WebElement languageSelect = driver.findElement(By.id("combo2"));
            languageSelect.findElement(By.cssSelector("option[value='23']")).click();

            // Submit the form
            WebElement codeInIt = driver.findElement(By.name("submitbutton"));
            codeInIt.click();

            // don't have to synchronise with other browsers but do with GeckoDriver
            //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
            WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
            assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }


    private class SelectContainsText implements ExpectedCondition<Boolean> {
        private String textToFind;
        private By findBy;

        public SelectContainsText(final By comboFindBy, final String textToFind) {
            this.findBy = comboFindBy;
            this.textToFind = textToFind;
        }

        @Override
        public Boolean apply(WebDriver webDriver) {
            WebElement combo = webDriver.findElement(this.findBy);
            List<WebElement> options = combo.findElements(By.tagName("option"));

            for(WebElement anOption : options){
                try{
                    if(anOption.getText().equals(this.textToFind))
                        return true;
                }catch(StaleElementReferenceException e){
                    // too fast, need to refresh the list so trigger a poll
                    // and wait for next time
                    return false;
                }
            }

            return false;
        }

        @Override
        public String toString() {
            return "select " + this.findBy + " to contain " + this.textToFind;
        }
    }

}

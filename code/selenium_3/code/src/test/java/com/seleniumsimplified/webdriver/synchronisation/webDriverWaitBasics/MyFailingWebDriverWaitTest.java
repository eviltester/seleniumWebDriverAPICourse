package com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class MyFailingWebDriverWaitTest {


    @Ignore("With no waits, this test fails locally - use this as an example and exercise for waits, ignore it from the CI process")
    // Grid is likely to be slow enough that this test passes
    @Test
    // Exercise: Feel The Pain
    public void chooseToCodeInJavaOnTheServerFromCombosNoSynchronisationExample(){

            WebDriver driver;

            driver = Driver.get("http://compendiumdev.co.uk/selenium/" +
                                "basic_ajax.html");

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
    }


}

package com.seleniumsimplified.webdriver.basics.manipulate;

import com.google.common.base.Predicate;
import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ManipulationFirstTryExampleTest {

    static WebDriver driver;

    @Test
    @Ignore("without waits this will only work in debug mode")
    public void manipulation1stTry(){

        driver = Driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

        // select Server
        driver.findElement(By.cssSelector("option[value='3']")).click();

        // click Java in the language dropdown
        driver.findElement(By.cssSelector("option[value='23']")).click();

        driver.findElement(By.name("submitbutton")).click();

        WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }


}

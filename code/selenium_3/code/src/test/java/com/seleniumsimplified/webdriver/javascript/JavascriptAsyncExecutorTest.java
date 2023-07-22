package com.seleniumsimplified.webdriver.javascript;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class JavascriptAsyncExecutorTest {


    private static WebDriver driver;


    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.basicAjaxPageUrl());
    }

    @Before
    public void resetBeforeTest(){

        driver.navigate().refresh();
    }

    // for hints see http://stackoverflow.com/questions/2857900/onhide-type-event-in-

    @Test
    public void syncOnAjaxGifRemovalViaAsync(){

        WebDriver driver;

        driver = Driver.get(SiteUrls.basicAjaxPageUrl());


        JavascriptExecutor js =(JavascriptExecutor)driver;

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        js.executeScript("window.webdrivercallback = function(){};" +
                //extend the hide method to call our callback when it hides the gif
                "var _oldhide = hideSpinner;" +
                "hideSpinner = function() {" +
                "    _oldhide();" +
                "    window.webdrivercallback.apply();" +
                "};"
        );

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        // need to wait in here, can do it with async javascript
        js.executeAsyncScript("window.webdrivercallback = arguments[arguments.length - 1];");

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

}

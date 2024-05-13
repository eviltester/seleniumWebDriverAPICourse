package com.seleniumsimplified.webdriver.javascript;


import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;


public class JavascriptExecuteAsyncExerciseTest {

    private static WebDriver driver;


    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.basicAjaxPageUrl());
    }

    @Before
    public void resetBeforeTest(){

        driver.navigate().refresh();
    }

    @Test
    public void waitInBrowserForTimeSample(){

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        long start = System.currentTimeMillis();
        ((JavascriptExecutor) driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");

        System.out.println(
                "Elapsed time: " + (System.currentTimeMillis() - start));

        assertTrue("Elapsed time should be greater than 500 milli",
                (System.currentTimeMillis() - start) > 500);

    }

    @Test
    public void useXMLHttpRequest(){


        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        Object response = ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var xhr = new XMLHttpRequest();" +
                        "xhr.open('GET', '/selenium/ajaxselect.php?id=2', true);" +
                        "xhr.onreadystatechange = function() {" +
                        "  if (xhr.readyState == 4) {" +
                        "    callback(xhr.responseText);" +
                        "  }" +
                        "};" +
                        "xhr.send();");

        System.out.println((String)response);

        assertThat((String) response,
                containsString("{\"optionValue\":10, \"optionDisplay\": \"C++\"}"));

    }



}



package com.seleniumsimplified.webdriver.basics.driver;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class FirstTest {

    @Test
    /**
     * We need to create a driver before we can do anything.
     *
     * HtmlUnitDriver is a headless browser implemented using HtmlUnit
     *
     * http://htmlunit.sourceforge.net/
     *
     * Advantages:
     * - Fast
     * - good for simple testing
     *
     * Disadvantages:
     * - does not handle JavaScript as well as a full browser - but it is getting better
     * - you need to add HtmlUnitDriver to your pom.xml
     *   https://github.com/SeleniumHQ/htmlunit-driver
     *
         <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>htmlunit-driver</artifactId>
            <version>${htmlunitdriver.version}</version>
         </dependency>
     *
     */
    public void driverIsTheKing(){
        // running HtmlUnit Driver with JavaScript can be tricky between versions
        // safest to run HtmlUnitDriver without JavaScript
        WebDriver driver = new HtmlUnitDriver();    // create with 'true' to enable JavaScript

        driver.get("https://testpages.herokuapp.com");

        assertTrue(driver.getTitle().contains(
                "Test Pages"));

        // don't need to close an HtmlUnitDriver, garbage
        // collection will take care of it
    }

}

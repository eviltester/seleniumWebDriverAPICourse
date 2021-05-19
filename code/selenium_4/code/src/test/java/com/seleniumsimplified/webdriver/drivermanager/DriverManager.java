package com.seleniumsimplified.webdriver.drivermanager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DriverManager {


    public static final String SELENIUM2_BASICS_DRIVER = "selenium2Basics.driver";

    public static WebDriver get() {

        String browserToUse="";

        if(System.getProperties().containsKey(SELENIUM2_BASICS_DRIVER)){
            browserToUse = System.getProperty(SELENIUM2_BASICS_DRIVER);
        }

        /* Code below requires Java 1.7 to allow Strings in switch statement
        *  Normally I would use this syntax.
        *  But since people sometimes run this code against Java 1.6 or have the
        *  IDE set to 1.6 I have reduced the instance of FAQs by writing the code
        *  so that it works with Java 1.7 and doesn't require Java 1.7
        *  Instead of the switch statement below. I have implemented
        *  the functionality with a series of if statements to achieve the same effect.
        */
        /*
        switch(browserToUse){
            case "firefox":
                return new FirefoxDriver();
            case "htmlunit":
                return new HtmlUnitDriver();
            default:
                return new HtmlUnitDriver();
        }
        */

        if("firefox".contentEquals(browserToUse))
            return new FirefoxDriver();

        if("htmlunit".contentEquals(browserToUse))
            return new HtmlUnitDriver();

        /* case default */
        return new HtmlUnitDriver();

    }
}

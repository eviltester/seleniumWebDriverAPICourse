package com.seleniumsimplified.webdriver.basics.interrogate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class DriverInterrogateRefactoredTest {

    public static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        driver = Driver.get();
        //driver = new FirefoxDriver();
    }

    @Test
    public void driverLevelPageInterrogateMethods(){

        driver.navigate().to(SiteUrls.basicWebPageUrl());

        assertThat(driver.getTitle(), is("Basic Web Page Title"));

        // changed from and equals assert to an endswith to cope for move from http to https
        // assertThat(driver.getCurrentUrl(), is(theTestPageURL));
        // to
        assertThat(driver.getCurrentUrl(), endsWith("basic-web-page-test.html"));
        assertThat(driver.getPageSource(), containsString("A paragraph of text"));
    }

    @AfterClass
    public static void stopDriver(){
        //driver.quit();
    }

}

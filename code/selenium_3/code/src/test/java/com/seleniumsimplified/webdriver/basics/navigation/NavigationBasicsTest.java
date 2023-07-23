package com.seleniumsimplified.webdriver.basics.navigation;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationBasicsTest {

    static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        //driver = new FirefoxDriver();
        driver = Driver.get();
    }

    @Test
    public void navigateWithGet(){
        driver.get(SiteUrls.rootUrl());

        assertTrue(driver.getTitle().startsWith(
                    "Selenium"));
    }

    @Test
    public void navigateWithNavigateTo(){
        driver.navigate().to(SiteUrls.searchPageUrl());

        assertTrue(driver.getTitle().startsWith(
                   "Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateToURL() throws MalformedURLException {
        URL searchPage = new URL(SiteUrls.searchPageUrl());

        driver.navigate().to(searchPage);

        assertTrue(driver.getTitle().startsWith(
                   "Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateBackAndForward(){

        driver.navigate().to(SiteUrls.basicHtmlFormPageUrl());
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));


        driver.navigate().to(SiteUrls.basicWebPageUrl());
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));

        driver.navigate().back();
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().forward();
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }

    @Test
    public void navigateWithRefresh(){
        driver.navigate().to(SiteUrls.refreshPage());

        final String refreshTitleConstant = "Refreshed Page on ";
        String pageTitle = driver.getTitle();

        assertTrue(pageTitle.
                            startsWith(refreshTitleConstant));

        long startTime = Long.parseLong(
                        pageTitle.
                            replaceFirst(refreshTitleConstant, ""));

        // synchronise using sleep to guarantee time moves on
        // the only time we sleep is waiting for time
        // never sleep waiting for page objects
        try{Thread.sleep(2000);}
        catch(InterruptedException e){/*ignore interrupt */};

        driver.navigate().refresh();

        // in opera the refresh did not block or wait for the page to change so
        // needed a synchronisation check, not an assert
        //        assertTrue(driver.getTitle().
        //              startsWith(refreshTitleConstant));

        new WebDriverWait(driver,10).until(titleIsNoLonger(pageTitle));


        long endTime = Long.parseLong(
                            driver.getTitle().
                                replaceFirst(refreshTitleConstant, ""));

        assertTrue("expected " + endTime + " > " + startTime, endTime > startTime);

        assertThat(endTime, greaterThan(startTime));
    }


    private ExpectedCondition<Boolean> titleIsNoLonger(final String pageTitle) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !(pageTitle.equals(input.getTitle()));
            }
        };
    }

    @AfterClass
    public static void quitDriver(){
        //driver.quit();
    }
}

package com.seleniumsimplified.webdriver.basics.navigation;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationBasicsTest {

    static WebDriver driver;
    final private String PROTOCOL = "https";
    final private String DOMAIN = "testpages.herokuapp.com";
    final private String ROOT_URL = PROTOCOL + "://" + DOMAIN;

    @BeforeClass
    public static void createDriver(){
        //driver = new FirefoxDriver();
        driver = Driver.get();
    }

    @Test
    public void navigateWithGet(){
        driver.get(ROOT_URL + "/styled/index.html");

        assertTrue(driver.getTitle().startsWith(
                    "Selenium"));
    }

    @Test
    public void navigateWithNavigateTo(){
        driver.navigate().to(ROOT_URL + "/search.php");

        assertTrue(driver.getTitle().startsWith(
                   "Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateToURL() throws MalformedURLException {
        URL searchPage = new URL(PROTOCOL,DOMAIN,"/search.php");

        driver.navigate().to(searchPage);

        assertTrue(driver.getTitle().startsWith(
                   "Selenium Simplified Search Engine"));
    }

    @Test
    public void navigateWithNavigateBackAndForward(){
        driver.navigate().to(ROOT_URL + "/basic_html_form.html");
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().to(ROOT_URL + "/basic_web_page.html");
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));

        driver.navigate().back();
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().forward();
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }

    @Test
    public void navigateWithRefresh(){
        driver.navigate().to(ROOT_URL + "/refresh.php");

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

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(titleIsNoLonger(pageTitle));


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

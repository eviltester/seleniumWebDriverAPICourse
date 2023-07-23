package com.seleniumsimplified.webdriver.cookies;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

// this is different from CookiesExercisesTest,
// this has additional synchronisation to allow it to work on Opera but a good
// example of extra synchronisation you might need
public class CookiesExercisesTestWorkWithExtraSync {


    private static WebDriver driver;
    public static final String SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS = "seleniumSimplifiedSearchNumVisits";
    private WebElement queryInput;
    private WebElement submitButton;

    @Before
    public void setup(){

        driver = Driver.get(SiteUrls.searchPageUrl());

        //seleniumSimplifiedSearchLastVisit
        //seleniumSimplifiedSearchNumVisits
        //seleniumSimplifiedLastSearch

        //clear any cookies so it is
        // always the first time we have been here
        driver.manage().deleteAllCookies();

        refreshPageObjects();

    }

    private void refreshPageObjects(){
        queryInput = driver.findElement(By.name("q"));
        submitButton = driver.findElement(By.name("btnG"));
    }

    @Test
    public void doASearchAndCheckForCookies(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie aCookie : cookies){
            if(aCookie.getName().contentEquals(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS)){
                assertEquals(   "Should be my first visit",
                                String.valueOf(1),
                                aCookie.getValue());
            }
        }
    }

    @Test
    public void getCookieDirectly(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        waitForResultsToDisplay();

        Cookie aCookie = driver.manage().getCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);

        assertEquals(   "Should be my first visit",
                        1,
                        Integer.parseInt(aCookie.getValue()));
    }

    public void waitForResultsToDisplay(){
        // Also needed to do this to have the results pass on FirefoxMarionette
        // extra sync was needed otherwise Marionette would not refresh the DOM correctly
        // needed to do this otherwise Opera races ahead and throws
        // null pointer exceptions on the cookies
        new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("resultList")));
        waitForCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);
    }

    private Cookie waitForCookieNamed(String cookieName) {
        return new WebDriverWait(driver,10).until(cookieExistsNamed(cookieName));
    }



    private ExpectedCondition<Cookie> cookieExistsNamed(final String cookieName) {
        return new ExpectedCondition<Cookie>() {
            @Override
            public Cookie apply(WebDriver input) {
                return input.manage().getCookieNamed(cookieName);
            }
        };
    }


    private Cookie waitForCookieWithValue(String cookieName, String cookieValue) {
        return new WebDriverWait(driver,10).until(cookieWithValueExists(cookieName, cookieValue));
    }

    private ExpectedCondition<Cookie> cookieWithValueExists(final String cookieName, final String cookieValue){
        return new ExpectedCondition<Cookie>(){

            @Override
            public Cookie apply(WebDriver input) {
                Cookie cookie = waitForCookieNamed(cookieName);

                // Because ChromeDriver creates cookies with a '.' prefixed on the domain
                // there may actually be multiple cookies with the same name
                for(Cookie aCookie : driver.manage().getCookies()){
                    if(aCookie.getName().contentEquals(cookieName)) {
                        if (aCookie.getValue().equals(cookieValue)) {
                            return aCookie;
                        }
                    }
                }
                return null;
            };
        };
    }


    @Test
    public void changeCookieVisitsCount(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        waitForResultsToDisplay();

        Cookie aCookie = driver.manage().
                                getCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);

        assertEquals(   "Should be my first visit",
                        1,
                        Integer.parseInt(aCookie.getValue()));

        // clone cookie and set value to what I want
        Cookie aNewCookie = null;

            aNewCookie = new Cookie( aCookie.getName(),
                    String.valueOf(42),
                    aCookie.getDomain(),
                    aCookie.getPath(),
                    aCookie.getExpiry(),
                    aCookie.isSecure());

        int cookieCount = seleniumSimplifiedCookies().size();
        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);


        assertEquals("we added the cookie correctly", cookieCount, seleniumSimplifiedCookies().size());

        // needed to do this after cookie processing in Opera otherwise it threw a stale element exception
        refreshPageObjects();

        queryInput.clear();
        queryInput.sendKeys("Cookie Changed Test");
        submitButton.click();

        waitForResultsToDisplay();
        aCookie = waitForCookieWithValue(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS, "43");

        assertEquals("I should be a frequent visitor", 43, Integer.parseInt(aCookie.getValue()));
    }

    @Test
    public void changeCookieVisitsCountUsingCookieBuilder(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        waitForResultsToDisplay();

        Cookie aCookie = driver.manage().getCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);
        assertEquals("Should be my first visit", 1, Integer.parseInt(aCookie.getValue()));



        Cookie aNewCookie = null;
        // clone cookie and set value to what I want

            aNewCookie = new Cookie.Builder( aCookie.getName(), String.valueOf(29))
                    .domain(aCookie.getDomain())
                    .path( aCookie.getPath())
                    .expiresOn(aCookie.getExpiry())
                    .isSecure(aCookie.isSecure()).build();


        int cookieCount = seleniumSimplifiedCookies().size();
        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);


        assertEquals("we added the cookie correctly", cookieCount, seleniumSimplifiedCookies().size());


        refreshPageObjects();

        queryInput.clear();
        queryInput.sendKeys("Cookie Changed Test");
        submitButton.click();

        waitForResultsToDisplay();
        aCookie = waitForCookieWithValue(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS, "30");

        assertEquals("I should be a frequent visitor", 30, Integer.parseInt(aCookie.getValue()));
    }




    @Test
    public void changeCookieVisitsCountSimply(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        waitForResultsToDisplay();


        driver.manage().deleteCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);

        String path = "/styled";

        driver.manage().addCookie(
                new Cookie.Builder(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS,
                                    String.valueOf(402)).
                        path(path).build());

        refreshPageObjects();
        queryInput.clear();
        queryInput.sendKeys("Simple Cookie Change Test");
        submitButton.click();

        waitForResultsToDisplay();
        waitForCookieWithValue(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS, "403");

        Cookie aCookie = driver.manage().getCookieNamed(SELENIUM_SIMPLIFIED_SEARCH_NUM_VISITS);
        assertEquals("I should be a very frequent visitor", 403, Integer.parseInt(aCookie.getValue()));
    }


    private Set<Cookie> seleniumSimplifiedCookies() {
        Set<Cookie> myCookies = new HashSet<Cookie>();

        for(Cookie cookie : driver.manage().getCookies()){
            if(cookie.getName().startsWith("seleniumSimplified")){
                myCookies.add(cookie);
            }
        }
        return myCookies;
    }

}

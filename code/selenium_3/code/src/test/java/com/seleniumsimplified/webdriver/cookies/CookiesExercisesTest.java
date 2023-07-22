package com.seleniumsimplified.webdriver.cookies;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CookiesExercisesTest {


    private static WebDriver driver;
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
            if(aCookie.getName().contentEquals("seleniumSimplifiedSearchNumVisits")){
                assertEquals(   "Should be my first visit",
                                String.valueOf(1),
                                aCookie.getValue());
            }
        }

        //if this was a production test then I'd make sure that I'd also have an 'if' at the end of the method to fail if we had not found the cookie at all.
    }

    @Test
    public void getCookieDirectly(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();

        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals(   "Should be my first visit",
                        1,
                        Integer.parseInt(aCookie.getValue()));
    }

    @Test
    public void changeCookieVisitsCount(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();



        Cookie aCookie = driver.manage().
                                getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals(   "Should be my first visit",
                        1,
                        Integer.parseInt(aCookie.getValue()));

        // clone cookie and set value to what I want
        Cookie aNewCookie = new Cookie( aCookie.getName(),
                String.valueOf(42),
                aCookie.getDomain(),
                aCookie.getPath(),
                aCookie.getExpiry(),
                aCookie.isSecure());

        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);

        refreshPageObjects();

        queryInput.clear();
        queryInput.sendKeys("Cookie Changed Test");
        submitButton.click();

        aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("I should be a frequent visitor", 43, Integer.parseInt(aCookie.getValue()));
    }

    @Test
    public void changeCookieVisitsCountUsingCookieBuilder(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();



        Cookie aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");
        assertEquals("Should be my first visit", 1, Integer.parseInt(aCookie.getValue()));

        // clone cookie and set value to what I want
        Cookie aNewCookie = new Cookie.Builder( aCookie.getName(), String.valueOf(29))
                                    .domain(aCookie.getDomain())
                                    .path( aCookie.getPath())
                                    .expiresOn(aCookie.getExpiry())
                                    .isSecure(aCookie.isSecure()).build();


        driver.manage().deleteCookie(aCookie);
        driver.manage().addCookie(aNewCookie);

        refreshPageObjects();

        queryInput.clear();
        queryInput.sendKeys("Cookie Changed Test");
        submitButton.click();

        aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("I should be a frequent visitor", 30, Integer.parseInt(aCookie.getValue()));
    }

    // NOTE: When I first wrote this test I did not add the
    // trailing '/' in the path, but the test started to fail on firefox
    // because a 'duplicate' cookie was created. Adding the path, fixed it
    // It is generally more robust to clone the cookies than create from scratch
    // but sometimes you have to create from scratch
    @Test
    public void changeCookieVisitsCountSimply(){

        queryInput.clear();
        queryInput.sendKeys("Cookie Test");
        submitButton.click();



        driver.manage().deleteCookieNamed("seleniumSimplifiedSearchNumVisits");

        String path = "/";

        driver.manage().addCookie(
                new Cookie.Builder("seleniumSimplifiedSearchNumVisits",
                        String.valueOf(402)).
                        path(path).build());

        refreshPageObjects();

        queryInput.clear();
        queryInput.sendKeys("Simple Cookie Change Test");
        submitButton.click();

        Cookie aCookie = null;
        aCookie = driver.manage().getCookieNamed("seleniumSimplifiedSearchNumVisits");

        assertEquals("I should be a very frequent visitor", 403, Integer.parseInt(aCookie.getValue()));
    }




}

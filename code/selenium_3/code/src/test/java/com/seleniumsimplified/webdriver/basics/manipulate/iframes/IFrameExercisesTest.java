package com.seleniumsimplified.webdriver.basics.manipulate.iframes;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.WebDriver;


public class IFrameExercisesTest {

    /*

    # Using the nested frames page:

    - https://testpages.eviltester.com/styled/iframes-test.html
    - switch to the second iframe by number (1) and assert on something on the page e.g. h1 is equal to "Nested Page Example"
    - switch to the first iframe by id "dynamic-iframe" and find and assert on something in the list e.g. "Iframe List Item 3"
    - switch to the second iframe by WebElement and find and assert on the h1 is equal to "Nested Page Example"
    - switch to an iframe, assert you are in the iframe, and switch to the main page using `defaultContent()`
    - switch to an iframe, assert you are in the iframe, and switch to the main page using `parentWindow()`

     */

    static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        //driver = new ChromeDriver();
    }

    @Before
    public void loadPage(){
        // because we are switching between iframes I will 'get' the page prior to every test
        driver = Driver.get(SiteUrls.iframesTestPageUrl());
    }

    @Test
    public void switchToX(){
        Assert.assertTrue("write the tests and assertions below", true);
    }

    // switch to the second iframe by number (1) and assert on
    // something on the page e.g. h1 is equal to "Nested Page Example"


    // switch to the first iframe by id "thedynamichtml" and
    // find and assert on something in the list e.g. "iFrame List Item 3"


    // switch to the second iframe by WebElement and find
    // and assert on the h1 is equal to "Nested Page Example"

    // switch to an iframe, assert you are in the frame,
    // and switch to the main page using `defaultContent()`

    // switch to an iframe, assert you are in the frame,
    // and switch to the main page using `parentFrame()`

    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }
}

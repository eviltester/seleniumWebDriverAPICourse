package com.seleniumsimplified.webdriver.basics.manipulate.frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameExercisesTest {

    /*

    # Using the nested frames page:

    - https://testpages.eviltester.com/styled/frames/frames-test.html
    - switch to the middle frame by number (2) and assert on something in the list e.g. find "Middle List Item 1"
    - switch to the "left" frame by name and find and assert on something in the list e.g. "Left List Item 2"
    - switch to the right frame by WebElement and find and assert on something in the list e.g. "Right List Item 5"
    - switch to a frame, assert you are in the frame, and switch to the main page using `defaultContent()`
    - switch to a frame, assert you are in the frame, and switch to the main page using `parentFrame()`

     */

    static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        // driver = new ChromeDriver();
    }

    @Before
    public void loadPage(){
        // because we are switching between frames I will 'get' the page prior to every test
        driver = Driver.get(SiteUrls.framesTestPageUrl());
    }


    // switch to the middle frame by number (2) and assert on something in the list e.g. find "Middle List Item 1"
    // switch to the "left" frame by name and find and assert on something in the list e.g. "Left List Item 2"
    // switch to the right frame by WebElement and find and assert on something in the list e.g. "Right List Item 5"
    // switch to a frame, assert you are in the frame, and switch to the main page using `defaultContent()`
    // switch to a frame, assert you are in the frame, and switch to the main page using `parentFrame()`
    @Test
    public void switchToX(){
        Assert.assertTrue("write the tests and assertions above", true);
    }

    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }

}

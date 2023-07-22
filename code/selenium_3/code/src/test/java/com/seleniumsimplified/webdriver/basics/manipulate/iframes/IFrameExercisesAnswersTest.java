package com.seleniumsimplified.webdriver.basics.manipulate.iframes;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class IFrameExercisesAnswersTest {

    /*

    # Using the nested frames page:

    - https://testpages.herokuapp.com/styled/iframes-test.html
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
        //driver.get(SiteUrls.iframesTestPageUrl());
        driver = Driver.get(SiteUrls.iframesTestPageUrl());
    }

    // switch to the second iframe by number (1) and assert on
    // something on the page e.g. h1 is equal to "Nested Page Example"

    @Test
    public void switchToFrameByNumber(){
        driver.switchTo().frame(1);
//        System.out.println(
//                driver.findElement(By.tagName("h1")).getText());

        final WebElement elem = driver.findElement(By.tagName("h1"));
        Assert.assertEquals("Nested Page Example", elem.getText());
    }

    // switch to the first iframe by id "thedynamichtml" and
    // find and assert on something in the list e.g. "iFrame List Item 3"

    @Test
    public void switchToByName(){
        driver.switchTo().frame("thedynamichtml");
        final WebElement elem =
                driver.findElement(By.cssSelector("li#iframe3"));
        Assert.assertEquals("iFrame List Item 3", elem.getText());
    }

    // switch to the second iframe by WebElement and find
    // and assert on the h1 is equal to "Nested Page Example"

    @Test
    public void switchToWebElement(){
        WebElement frameElem = driver.findElement(
                By.cssSelector("iframe#theheaderhtml"));
        driver.switchTo().frame(frameElem);

        final WebElement elem = driver.findElement(By.tagName("h1"));
        Assert.assertEquals("Nested Page Example", elem.getText());
    }

    // switch to an iframe, assert you are in the frame,
    // and switch to the main page using `defaultContent()`
    @Test
    public void switchToDefaultContent(){
        driver.switchTo().frame(0);
        Assert.assertEquals("iFrame",
                driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".page-footer"));
    }

    // switch to an iframe, assert you are in the frame,
    // and switch to the main page using `parentFrame()`
    @Test
    public void switchToParentWindow(){
        driver.switchTo().frame(0);
        Assert.assertEquals("iFrame",
                driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector(".page-footer"));
    }

    @AfterClass
    public static void closeDriver(){
        //driver.quit();
    }
}

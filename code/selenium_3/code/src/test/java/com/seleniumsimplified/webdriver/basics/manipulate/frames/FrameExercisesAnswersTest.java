package com.seleniumsimplified.webdriver.basics.manipulate.frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameExercisesAnswersTest {

    /*

    # Using the nested frames page:

    - https://testpages.herokuapp.com/styled/frames/frames-test.html
    - switch to the middle frame by number (2) and assert on something in the list e.g. find "Middle List Item 1"
    - switch to the "left" frame by name and find and assert on something in the list e.g. "Left List Item 2"
    - switch to the right frame by WebElement and find and assert on something in the list e.g. "Right List Item 5"
    - switch to a frame, assert you are in the frame, and switch to the main page using `defaultContent()`
    - switch to a frame, assert you are in the frame, and switch to the main page using `parentFrame()`

     */

    static WebDriver driver;

    @BeforeClass
    public static void createDriver(){
        //driver = new ChromeDriver();
    }

    @Before
    public void loadPage(){
        // because we are switching between frames I will 'get' the page prior to every test
        //driver.get(SiteUrls.framesTestPageUrl());
        driver = Driver.get(SiteUrls.framesTestPageUrl());
    }


    // switch to the middle frame by number (2)
    // and assert on something in the list e.g. find "Middle List Item 1"

    @Test
    public void switchToFrameByNumber(){
        driver.switchTo().frame(2);
//        System.out.println(
//                driver.findElement(By.tagName("h1")).getText());

        final WebElement elem = driver.findElement(By.id("middle1"));
        Assert.assertEquals("Middle List Item 1", elem.getText());
    }

    // switch to the "left" frame by name and
    // find and assert on something in the list e.g. "Left List Item 2"

    @Test
    public void switchToByName(){
        driver.switchTo().frame("left");
        final WebElement elem =
                driver.findElement(By.cssSelector("li#left2"));
        Assert.assertEquals("Left List Item 2", elem.getText());
    }

    // switch to the right frame by WebElement and
    // find and assert on something in the list e.g. "Right List Item 5"

    @Test
    public void switchToWebElement(){
        WebElement frameElem = driver.findElement(
                By.cssSelector("frame[name='right']"));
        driver.switchTo().frame(frameElem);

        final WebElement elem = driver.findElement(
                By.cssSelector("li#right5"));
        Assert.assertEquals("Right List Item 5", elem.getText());
    }

    // switch to a frame, assert you are in the frame,
    // and switch to the main page using `defaultContent()`
    @Test
    public void switchToDefaultContent(){
        driver.switchTo().frame("left");
        Assert.assertEquals("Left",
                driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("frame[name='right']"));
    }

    // switch to a frame, assert you are in the frame,
    // and switch to the main page using `parentFrame()`
    @Test
    public void switchToParentFrame(){
        driver.switchTo().frame("left");
        Assert.assertEquals("Left",
                driver.findElement(By.tagName("h1")).getText());

        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("frame[name='right']"));
    }

    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }
}

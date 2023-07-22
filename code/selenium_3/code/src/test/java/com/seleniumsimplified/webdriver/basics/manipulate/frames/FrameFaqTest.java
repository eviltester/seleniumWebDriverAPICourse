package com.seleniumsimplified.webdriver.basics.manipulate.frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FrameFaqTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        // driver = new ChromeDriver();
        //driver.get(SiteUrls.framesTestPageUrl());
        driver = Driver.get(SiteUrls.framesTestPageUrl());
    }

    @Test
    public void whatHappensIfIDoNotSwitchTo(){

        // if you remove the switchTo then it won't find the element below
        // driver.switchTo().frame("left");

        try{
            final WebElement firstListItem = driver.findElement(By.id("left0"));
            fail("I did not expect to find this");

        }catch(NoSuchElementException e){
            // ignore the exception we expected
            e.printStackTrace();
        }
    }

    @Test
    public void whatDoesParentFrameSwitchTo(){

        System.out.println("On Main Page");
        System.out.println(driver.getTitle());

        System.out.println("Switch to Left");

        driver.switchTo().frame("left");

        System.out.println(driver.findElement(By.tagName("h1")).getText());
        System.out.println("Can not find and access footer because I am in a frame");
        // can't show footer source when in a frame as this is not there
        // this would throw a No Such Element Exception if enabled
        //System.out.println(driver.findElement(By.cssSelector("frame[name='footer']")).getAttribute("src"));

        System.out.println("Switch to ParentFrame");

        driver.switchTo().parentFrame();

        // footer is only available in main page
        System.out.println("Can find and access footer because I am on the main page");
        System.out.println(driver.findElement(By.cssSelector("frame[name='footer']")).getAttribute("src"));

        System.out.println("Switch to Left Frame");

        driver.switchTo().frame("left");

        System.out.println(driver.findElement(By.tagName("h1")).getText());

        System.out.println("Switch to DefaultContent");

        driver.switchTo().defaultContent();

        System.out.println("Can find and access footer because I am on the main page");
        System.out.println(driver.findElement(By.cssSelector("frame[name='footer']")).getAttribute("src"));
    }

    @Test
    public void whatDoesTitleShowWhenSwitchedToFrame(){
        // main page
        System.out.println("On Main Page Title Is:");
        System.out.println(driver.getTitle());

        // left frame
        System.out.println("In Left Frame Title Is:");
        driver.switchTo().frame("left");
        System.out.println(driver.getTitle());

        // title is the title of the page, not the frame

    }

    @AfterClass
    public static void closeDriver(){
        //driver.quit();
    }

}

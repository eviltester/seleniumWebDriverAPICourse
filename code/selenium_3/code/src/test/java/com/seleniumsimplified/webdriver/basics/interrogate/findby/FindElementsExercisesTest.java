package com.seleniumsimplified.webdriver.basics.interrogate.findby;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FindElementsExercisesTest {

    static WebDriver driver;

    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        //driver = new FirefoxDriver();
        driver = Driver.get(SiteUrls.findByPlaygroundPageUrl());
    }

    @Test
    public void doesFindElementsThrowAnExceptionIfNoMatch(){

        List<WebElement> elements;
        elements = driver.findElements(
                By.tagName("bob"));

        assertEquals(0,elements.size());
    }

    @Test
    public void assertDivElementsCount(){

        // the page format changed when I moved to the styled page
        // so I have to focus in on the specific divs within and including the wrapper

        // find the top level elements that wrap the divs, exluding any extra stuff on the page
        List<WebElement> parentElements = driver.findElements(By.className("specialDiv"));
        // find only the divs that are wrapped by the specialDiv test markers
        List<WebElement> divElements = driver.findElements(By.cssSelector("div.specialDiv div"));

        assertEquals(19,parentElements.size() + divElements.size());
    }


    @Test
    public void assert25LocalHrefLinks(){

        List<WebElement> elements;
        elements = driver.findElements(
                By.partialLinkText("jump to para"));

        assertEquals(25,elements.size());
    }

    @Test
    public void assertNumberOfParagraphs(){

        List<WebElement> elements;
        elements = driver.findElements(
                By.tagName("p"));

        int nestedCount = 0;
        for(WebElement e : elements){
            if(e.getText().contains("nested para")){
                nestedCount++;
            }
        }
        assertEquals(16,nestedCount);
        assertEquals(41, elements.size());
    }

    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }


}

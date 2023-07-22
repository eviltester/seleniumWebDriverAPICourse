package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowsExampleTest {

    String pageUrl = SiteUrls.windowLinksPageUrl();
    WebDriver driver;

    @BeforeClass
    public static void quitToRestart(){
        // I need to make sure that only one window open so, if using Driver Manager...
        Driver.quit();
    }

    @Before
    public void startDriver(){
        //driver = new ChromeDriver();
        //driver.get(pageUrl);
        driver = Driver.get(pageUrl);
    }

    @Test
    public void usingHandles(){

        Assert.assertEquals("Expected only 1 current window",
                1, driver.getWindowHandles().size());

        final String currentHandle = driver.getWindowHandle();

        System.out.println("Current Window Handle : " + currentHandle);

        driver.findElement(By.id("gobasicajax")).click();

        Assert.assertEquals("Expected a New Window opened",
                2, driver.getWindowHandles().size());
    }


    @Test
    public void switchToNewWindowName(){

        final String currentHandle = driver.getWindowHandle();

        driver.findElement(By.id("gobasicajax")).click();

        driver.switchTo().window(currentHandle);

        // then switch to new window
        driver.switchTo().window("basicajax");

        Assert.assertEquals("Test Page For Basic Ajax Example",
                driver.getTitle());
    }

    @Test
    public void switchToNewWindowHandle(){

        String currentHandle = driver.getWindowHandle();

        driver.findElement(By.id("gobasicajax")).click();

        // get handle of new window
        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle="";

        for(String aHandle : myWindows){
            if(!currentHandle.equals(aHandle)){
                newWindowHandle = aHandle; break;
            }
        }

        driver.switchTo().window(newWindowHandle);

        Assert.assertEquals("Test Page For Basic Ajax Example",
                driver.getTitle());
    }

    @After
    public void closeDriver(){

        //driver.quit();
        Driver.quit();
    }

}

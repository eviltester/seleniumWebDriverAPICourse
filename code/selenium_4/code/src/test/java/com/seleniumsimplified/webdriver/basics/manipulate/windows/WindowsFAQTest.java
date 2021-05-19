package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

public class WindowsFAQTest {

    String pageUrl = "https://testpages.herokuapp.com/styled/windows-test.html";
    WebDriver driver;

    @BeforeClass
    public static void quitToRestart() {
        // I need to make sure that only one window open so, if using Driver Manager...
        Driver.quit();
    }

    @Before
    public void startDriver() {
        //driver = new ChromeDriver();
        //driver.get(pageUrl);
        driver = Driver.get(pageUrl);
    }

    @Test
    public void whatHappensIfIOpenLotsOfWindows() {

        final String currentHandle = driver.getWindowHandle();

        // open 5 windows by clicking link
        for (int windows = 0; windows < 5; windows++) {
            driver.findElement(By.id("gobasicajax")).click();
            driver.switchTo().window(currentHandle);
        }

        Assert.assertEquals("Expected New Windows opened",
                6, driver.getWindowHandles().size());

        // A: you have a lot of windows open
    }

    @Test
    public void whatHappensIfISwitchToAWindowIHaveAlreadyClosed() {

        final String handle1 = driver.getWindowHandle();

        driver.findElement(By.id("gobasicajax")).click();

        // remember to switch to window
        driver.switchTo().window("basicajax");
        final String handle2 = driver.getWindowHandle();

        // close last opened window
        driver.close();

        Assert.assertEquals("one window is open",
                1, driver.getWindowHandles().size());

        // but we already closed this handle we can't switch to it
        try {
            driver.switchTo().window(handle2);
            Assert.fail("Expected a NoSuchWindowException");

        }catch(NoSuchWindowException e){
            // we already closed that handle so we expect
            // a NoSuchWindowException to be thrown
            System.out.println("Expected exception thrown");
        }

    }


    @After
    public void closeDriver(){
        //driver.quit();
        Driver.quit();
    }
}

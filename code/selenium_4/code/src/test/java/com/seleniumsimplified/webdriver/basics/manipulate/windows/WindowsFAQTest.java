package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WindowsFAQTest {

    String pageUrl = SiteUrls.windowLinksPageUrl();
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



        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.
                        numberOfWindowsToBe(2)
        );

        final String handle2 = driver.getWindowHandle();

        // ensure on first window
        driver.switchTo().window(handle1);

        // remember to switch to window
        // using handle because it is more reliable
        // than using the name
        driver.switchTo().window(handle2);

        // close last opened window
        driver.close();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.
                        numberOfWindowsToBe(1)
        );

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

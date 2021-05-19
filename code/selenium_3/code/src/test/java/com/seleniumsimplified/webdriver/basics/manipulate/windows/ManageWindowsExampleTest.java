package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageWindowsExampleTest {

    String pageUrl = "https://testpages.herokuapp.com/styled/css-media-queries.html";
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
    public void canManageWindows(){

        Dimension size = driver.manage().window().getSize();
        Point position = driver.manage().window().getPosition();

        // make window half the size
        driver.manage().window().setSize(
                new Dimension(size.width/2, size.height/2));

        // move the window to center of screen
        driver.manage().window().setPosition(
                new Point(size.width/4, size.height/4 ));

        // take over screen
        driver.manage().window().fullscreen();

        driver.manage().window().setSize(
                new Dimension(size.width/2, size.height/2));

        // maximise it
        driver.manage().window().maximize();

        driver.manage().window().setSize(
                new Dimension(size.width/2, size.height/2));

        // set to original size and position
        driver.manage().window().setSize(size);
        driver.manage().window().setPosition(position);
    }


    @After
    public void closeDriver(){

        driver.quit();
        //Driver.quit();
    }
}

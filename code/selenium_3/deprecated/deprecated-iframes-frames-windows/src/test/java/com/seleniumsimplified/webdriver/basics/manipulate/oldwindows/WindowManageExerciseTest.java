package com.seleniumsimplified.webdriver.basics.manipulate.oldwindows;


import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowManageExerciseTest {

    @Test
    public void maximizeTheWindow(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html",false);

        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        // the awt dimension checks will fail on grid because it uses local AWT dimensions
        // do not perform the checks if running on GRID
        if(Driver.currentDriver == Driver.BrowserName.GRID || Driver.currentDriver == Driver.BrowserName.SAUCELABS)
            return;

        java.awt.Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();

        String expected="";

        expected = ((int)screenDimension.getWidth()) + " approx (90% tolerance) " + fullScreenSize.getWidth();
        assertTrue(expected, (screenDimension.getWidth()*0.9) < fullScreenSize.getWidth());
        expected = ((int)screenDimension.getHeight()) + " approx (90% tolerance) " + (fullScreenSize.getHeight()*0.9);
        assertTrue(expected,(screenDimension.getHeight()*0.9) < fullScreenSize.getHeight());
    }

    @Test
    public void halfSizeTheWindow(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
        driver.manage().window().maximize();

        Dimension fullScreenSize = driver.manage().window().getSize();

        driver.manage().window().setSize(new Dimension(fullScreenSize.getWidth()/2, fullScreenSize.getHeight()/2));

        assertEquals("Width Half Equals", fullScreenSize.getWidth()/2, driver.manage().window().getSize().getWidth());
        assertEquals("Height Half Equals", fullScreenSize.getHeight() / 2, driver.manage().window().getSize().getHeight());
    }

    @Test
    public void moveHalfSizeToCenterTheWindow(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
        driver.manage().window().maximize();

        Dimension fullScreenSize = driver.manage().window().getSize();
        Point pos = driver.manage().window().getPosition();

        driver.manage().window().setSize(new Dimension(fullScreenSize.getWidth() / 2, fullScreenSize.getHeight() / 2));

        driver.manage().window().setPosition(new Point(fullScreenSize.getWidth()/4, fullScreenSize.getHeight()/4));
    }

        @Test
    public void bounceTheWindow(){
        WebDriver driver = Driver.get("http://www.compendiumdev.co.uk/selenium/bounce.html");
        driver.manage().window().maximize();
        Dimension fullScreenSize = driver.manage().window().getSize();

        int changeWidth = fullScreenSize.getWidth();
        int changeHeight = fullScreenSize.getHeight();

        // 20180611 changed to use variables to make easier to amend
        int desiredWidth = 400;
        int desiredHeight = 300;

        while(changeWidth > desiredWidth){
            changeWidth = changeWidth - 50;

            if(changeHeight>desiredHeight)
                changeHeight = changeHeight - 50;

            System.out.println(String.format("Window size %d, %d", changeWidth, changeHeight));
            // potential bug in FireFox driver causes the setSize command to 'hang' when set too low
            // so on 20180611 changed the width size to allow this test to pass
            driver.manage().window().setSize(new Dimension(changeWidth, changeHeight));
            try {Thread.sleep(50);} catch (InterruptedException e) {}
        }

        int xDir = 10;
        int yDir = 10;

        Point position = driver.manage().window().getPosition();

        // original examples used 1000 for MAX_ITERATIONS
        // 1000 seems too long for CI, so dropped it down to 50 to make it faster
        int MAX_ITERATIONS = 50;

        for(int bounceIterations = 0; bounceIterations < MAX_ITERATIONS; bounceIterations ++){

            position = position.moveBy(xDir,yDir);

            driver.manage().window().setPosition(position);

            if(position.getX()>(fullScreenSize.getWidth() - changeWidth)){
                xDir = -10;
            }
            if(position.getX()<0){
                xDir = 10;
            }

            if(position.getY()>(fullScreenSize.getHeight() - changeHeight)){
                yDir = -10;
            }
            if(position.getY()<0){
                yDir = 10;
            }
        }

    }
}

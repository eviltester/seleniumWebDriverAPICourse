package com.seleniumsimplified.webdriver.basics.manipulate.oldwindows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WindowsManageExampleTest {

    // TODO: remove window moving from course
    // This is really a historic example and we shouldn't need to move windows about the screen with expected exact results
    @Ignore("Moving windows is no longer an 'exact' science and we probably shouldn't really care about this")
    @Test
    public void manageWindow(){

        final int widthToSet = 500;

        WebDriver driver = Driver.get(
                    "http://www.compendiumdev.co.uk/selenium/frames");

        // moved to 40 for Y to cope with Mac drop down menu
        driver.manage().window().setPosition(new Point(10,40));
        Point pos = driver.manage().window().getPosition();

        assertEquals(10, pos.getX());
        assertEquals(40, pos.getY());

        // when originally written screens and browsers were smaller
        Dimension originalWinSizes = driver.manage().window().getSize();
        int originalWidth = originalWinSizes.getWidth();


        driver.manage().window().setSize(new Dimension(widthToSet,400));
        Dimension winSizes = driver.manage().window().getSize();

        Assert.assertTrue("Expected window width to change", originalWidth != winSizes.getWidth());

        // ideally window should be `widthToSet` but I'm less concerned about the exact size and more that it isn't the default
        // TODO make widthToSet a value that works on modern browsers and resolutions
        assertEquals(widthToSet, winSizes.getWidth());
        assertEquals(400, winSizes.getHeight());
    }
}

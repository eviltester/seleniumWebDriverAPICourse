package com.seleniumsimplified.webdriver.basics.manipulate.frames;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class FramesExampleTest {

    @Test
    public void switchToFrameExample(){

        final String framesPage =
                "https://testpages.herokuapp.com/styled/frames/frames-test.html";

        // WebDriver driver = new ChromeDriver();
        // driver.get(framesPage);

        WebDriver driver = Driver.get(framesPage);

        assertEquals("Nested Frames Simple Example", driver.getTitle());

        driver.switchTo().frame("left");

        final WebElement firstListItem = driver.findElement(By.id("left0"));

        Assert.assertEquals("Left List Item 0", firstListItem.getText());

        //driver.quit();
    }
}

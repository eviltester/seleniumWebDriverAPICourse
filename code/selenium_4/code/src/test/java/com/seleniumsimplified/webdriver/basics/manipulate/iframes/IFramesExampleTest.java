package com.seleniumsimplified.webdriver.basics.manipulate.iframes;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class IFramesExampleTest {

    @Test
    public void switchToiFrameExample(){

        final String iframesPage =
                "https://testpages.herokuapp.com/styled/iframes-test.html";

//         WebDriver driver = new ChromeDriver();
//         driver.get(iframesPage);

        WebDriver driver = Driver.get(iframesPage);

        assertEquals("iFrames Example", driver.getTitle());

        // One of the below switchTo approaches
        // needs to be enabled

        // by id
        driver.switchTo().frame(0);

        // by element of the iframe
//         WebElement frameElem = driver.findElement(By.id("thedynamichtml"));
//         driver.switchTo().frame(frameElem);

        // by name - which is actually nameOrId iframes use id, frames tend to use name
//        driver.switchTo().frame("thedynamichtml");

        final WebElement h1 = driver.findElement(By.tagName("h1"));

        Assert.assertEquals("iFrame", h1.getText());

        //driver.quit();
    }


}

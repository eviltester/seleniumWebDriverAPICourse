package com.seleniumsimplified.webdriver.basics.interrogate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WebElementInterrogationTest {

    @Test
    public void WebElementInterrogationBasics(){

        final WebDriver driver;
        final String theTestPageURL = SiteUrls.basicWebPageUrl();

        //driver = new FirefoxDriver();
        driver = Driver.get();

        driver.navigate().to(theTestPageURL);

        WebElement para1 = driver.findElement(By.id("para1"));

        assertEquals(para1.getText(),"A paragraph of text");

        //driver.close();
    }
}

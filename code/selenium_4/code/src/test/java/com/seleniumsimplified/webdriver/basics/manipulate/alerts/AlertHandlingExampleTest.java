package com.seleniumsimplified.webdriver.basics.manipulate.alerts;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlertHandlingExampleTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        // I could instantiate a driver directly if I wasn't using a Driver manager
        //driver = new ChromeDriver();
        driver = Driver.get(SiteUrls.basicAlertsPageUrl());
    }

    @Test
    public void basicAlertHandlingExample(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("alertexamples"));

        alertButton.click();

        String alertMessage = "I am an alert box!";

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alertMessage, alert.getText());

        alert.accept();
    }

    @After
    public void tearDown(){
        // if I instantiated a ChromeDriver or FirefoxDriver
        // above then I should close it here
        //driver.close();
    }
}


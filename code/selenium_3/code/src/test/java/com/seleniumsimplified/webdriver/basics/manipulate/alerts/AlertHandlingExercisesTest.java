package com.seleniumsimplified.webdriver.basics.manipulate.alerts;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

public class AlertHandlingExercisesTest {

    private static WebDriver driver;


    @Before
    public void setup(){

        String url= SiteUrls.basicAlertsPageUrl();

        // I could instantiate a driver directly if I wasn't using a Driver manager
        // driver = new ChromeDriver();
        // driver.get(url);
        driver = Driver.get(url);
    }

    @Test
    public void basicAlertHandlingTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("alertexamples"));

        alertButton.click();

        String alertMessage = "I am an alert box!";

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());
        alert.accept();

    }

    @Test
    public void basicAlertHandlingDismissTest(){

        WebElement alertButton;

        alertButton = driver.findElement(By.id("alertexamples"));

        alertButton.click();

        String alertMessage = "I am an alert box!";

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());
        alert.dismiss();
    }

    @Test
    public void basicConfirmHandlingAcceptTest(){

        WebElement confirmButton;
        WebElement confirmResult;

        confirmButton = driver.findElement(By.id("confirmexample"));
        confirmButton.click();

        String alertMessage = "I am a confirm alert";

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());
        alert.accept();

        confirmResult = driver.findElement(By.id("confirmretval"));
        Assert.assertEquals("true", confirmResult.getText());
    }

    @Test
    public void basicConfirmHandlingDismissTest(){

        WebElement confirmButton;
        WebElement confirmResult;

        confirmButton = driver.findElement(By.id("confirmexample"));
        confirmButton.click();

        String alertMessage = "I am a confirm alert";
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());

        alert.dismiss();

        confirmResult = driver.findElement(By.id("confirmretval"));
        Assert.assertEquals("false", confirmResult.getText());
    }

    @Test
    public void basicPromptConfirmHandlingAcceptTest(){

        WebElement promptButton;
        WebElement promptResult;

        promptButton = driver.findElement(By.id("promptexample"));

        promptButton.click();

        String alertMessage = "I prompt you";
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());

        alert.accept();

        promptResult = driver.findElement(By.id("promptretval"));
        Assert.assertEquals("change me", promptResult.getText());
    }

    @Test
    public void basicPromptConfirmHandlingDismissTest(){

        WebElement promptButton;
        WebElement promptResult;

        promptButton = driver.findElement(By.id("promptexample"));
        promptButton.click();

        String alertMessage = "I prompt you";
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());

        alert.dismiss();

        promptResult = driver.findElement(By.id("promptretval"));
        Assert.assertEquals("null", promptResult.getText());
    }

    // send text to prompt
    @Test
    public void basicPromptConfirmHandlingChangeAndAcceptTest(){

        WebElement promptButton;
        WebElement promptResult;

        promptButton = driver.findElement(By.id("promptexample"));

        promptButton.click();

        String alertMessage = "I prompt you";
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());

        alert.sendKeys("Hello");
        alert.accept();

        promptResult = driver.findElement(By.id("promptretval"));
        Assert.assertEquals("Hello", promptResult.getText());
    }

    @Test
    public void basicPromptConfirmHandlingChangeAndDismissTest(){

        WebElement promptButton;
        WebElement promptResult;

        promptButton = driver.findElement(By.id("promptexample"));
        promptButton.click();

        String alertMessage = "I prompt you";
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alertMessage,alert.getText());

        alert.sendKeys("Hello");
        alert.dismiss();

        promptResult = driver.findElement(By.id("promptretval"));
        Assert.assertEquals("null", promptResult.getText());
    }

    @After
    public void tearDown(){
        // if I instantiated a ChromeDriver or FirefoxDriver
        // above then I should close it here
        //driver.close();
    }

}


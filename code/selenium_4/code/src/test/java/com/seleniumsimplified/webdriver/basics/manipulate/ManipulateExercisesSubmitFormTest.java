package com.seleniumsimplified.webdriver.basics.manipulate;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManipulateExercisesSubmitFormTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get("https://testpages.herokuapp.com/" +
                            "basic_html_form.html");
    }

    @Test
    public void submitFormWithButtonClick(){

        WebElement submitButton;
        submitButton = driver.findElement(
                            By.cssSelector(
                              "input[type='submit'][name='submitbutton']"));

        submitButton.click();


        //when moving the test to continuous integration this check showed up as intermittent
        //assertEquals("Expected the form to be processed",
        //                "Processed Form Details",driver.getTitle());
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
         public void submitFormWithButtonSubmit(){

        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.submit();

        //when moving the test to continuous integration this check showed up as intermittent
        //assertEquals("Expected the form to be processed",
        //                "Processed Form Details",driver.getTitle());
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void submitFormWithFormSubmit(){

        WebElement submitForm;
        submitForm = driver.findElement(
                            By.id("HTMLFormElements"));

        submitForm.submit();

        //when moving the test to continuous integration this check showed up as intermittent
        //assertEquals("Expected the form to be processed",
        //                "Processed Form Details",driver.getTitle());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));
    }

    @Test
    public void iCanActuallySubmitOnAnyFormElement(){
        WebElement passwordInput;
        passwordInput = driver.findElement(
                By.cssSelector("input[type='password']"));

        passwordInput.submit();

        //when moving the test to continuous integration this check showed up as intermittent
        //assertEquals("Expected the form to be processed",
        //                "Processed Form Details",driver.getTitle());
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));

    }

    @Test
    public void submitButtonWithKeyPress(){


        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));


        //when moving the test to continuous integration this check showed up as intermittent
        //assertEquals("Expected the form to be processed",
        //                "Processed Form Details",driver.getTitle());

            submitButton.sendKeys(Keys.ENTER);
            // if enter does not work then try SPACE
            //submitButton.sendKeys(Keys.SPACE);

            new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));

    }


}

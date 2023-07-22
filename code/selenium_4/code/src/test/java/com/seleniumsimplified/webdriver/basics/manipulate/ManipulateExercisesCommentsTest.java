package com.seleniumsimplified.webdriver.basics.manipulate;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ManipulateExercisesCommentsTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get("https://testpages.herokuapp.com/" +
                            "basic_html_form.html");
    }

    @Test
    public void submitFormDefaultComments(){

        WebElement submitButton;

        submitButton = driver.findElement(
                        By.cssSelector(
                          "input[type='submit'][name='submitbutton']"));

        submitButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                ExpectedConditions.titleIs("Processed Form Details"));

        WebElement commentsResults;

        commentsResults = driver.findElement(
                            By.id("_valuecomments"));

        assertEquals("Expected default comments",
                    "Comments...", commentsResults.getText());
    }

    @Test
    public void submitFormWithMyComments(){

        WebElement submitButton;
        WebElement commentTextArea;

        String myCommentString = "This is my comment";
        commentTextArea = driver.findElement(By.name("comments"));
        commentTextArea.clear();
        commentTextArea.sendKeys(myCommentString);

        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(
                ExpectedConditions.titleIs("Processed Form Details"));

        WebElement commentsResults;

        commentsResults = driver.findElement(
                By.id("_valuecomments"));

        assertEquals("Expected default comments",
                myCommentString, commentsResults.getText());
    }
}

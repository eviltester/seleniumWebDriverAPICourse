package com.seleniumsimplified.webdriver.basics.manipulate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SendKeysExamplesTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());
    }

    @Test
    public void sendKeysModifierChord(){

        WebElement commentTextArea
                = driver.findElement(By.name("comments"));
        commentTextArea.clear();

        // Chrome will accept the full chord
        // Keys.chord(Keys.SHIFT, "bob", Keys.NULL, " Dobbs")
        // and create BOB Dobbs
        // but on Firefox this results in BOB DOBBS
        // so split into two chords, one for each value
        commentTextArea.sendKeys(Keys.chord(Keys.SHIFT, "bob", Keys.NULL));
        commentTextArea.sendKeys(Keys.chord(" Dobbs"));

        clickSubmitButton();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertExpectedCommentText("BOB Dobbs");
    }

    private void assertExpectedCommentText(String expectedCommentText) {
        WebElement commentsResults;

        commentsResults = driver.findElement(
                            By.id("_valuecomments"));

        assertEquals("Expected to see " + expectedCommentText, expectedCommentText, commentsResults.getText());
    }

    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }

}

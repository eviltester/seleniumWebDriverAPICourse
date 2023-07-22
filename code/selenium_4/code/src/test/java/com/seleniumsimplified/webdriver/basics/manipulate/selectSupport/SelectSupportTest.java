package com.seleniumsimplified.webdriver.basics.manipulate.selectSupport;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SelectSupportTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());
    }

    @Test
    public void selectSupportExplore(){

        // repeat exercise to select 1 2 and 3 on multiple with Select on support.ui

        WebElement multipleSelectElement;

        multipleSelectElement = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        Select multipleSelect = new Select(multipleSelectElement);
        assertTrue(multipleSelect.isMultiple());

        List<WebElement> selectedElements = multipleSelect.getAllSelectedOptions();
        assertEquals("By default expected only 1 selected", 1, selectedElements.size());

        // for Chrome I had to trim the resulting getText otherwise the whitespace padding
        // made it impossible to assert on cross browser
        assertEquals("By default expected different item",
                     "Selection Item 4", selectedElements.get(0).getText().trim());

        multipleSelect.deselectAll();

        selectedElements = multipleSelect.getAllSelectedOptions();
        assertEquals("Expected cleared", 0, selectedElements.size());

        multipleSelect.selectByVisibleText("Selection Item 1");
        multipleSelect.selectByIndex(1);   // select 2nd
        multipleSelect.selectByValue("ms3");

        selectedElements = multipleSelect.getAllSelectedOptions();
        assertEquals("Expected 3 selected", 3, selectedElements.size());

        clickSubmitButton();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertEquals("ms1", driver.findElement(By.id("_valuemultipleselect0")).getText());
        assertEquals("ms2", driver.findElement(By.id("_valuemultipleselect1")).getText());
        assertEquals("ms3", driver.findElement(By.id("_valuemultipleselect2")).getText());
        assertTrue("Expected no 4th element",
                driver.findElements(By.id("_valuemultipleselect3")).isEmpty());
    }

    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }


}

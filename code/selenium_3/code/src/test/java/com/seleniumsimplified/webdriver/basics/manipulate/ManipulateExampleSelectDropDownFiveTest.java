package com.seleniumsimplified.webdriver.basics.manipulate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class ManipulateExampleSelectDropDownFiveTest {

    private static WebDriver driver;


    @Before
    public void setup(){
        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());
    }

    @Test
    public void submitFormWithDropDown5SelectedChainOfFindElements(){

        WebElement dropDownSelect;
        WebElement dropDownOption;

        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownOption = dropDownSelect.findElement(By.cssSelector("option[value='dd5']"));
        dropDownOption.click();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
    }

    @Test
    public void submitFormWithDropDown5SelectedOptionFiveDirect(){

        driver.findElement(By.cssSelector("option[value='dd5']")).click();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
    }


    @Test
    public void submitFormWithDropDownFiveUsingKeyboardFullText(){


        WebElement dropDownSelect;
        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownSelect.sendKeys("Drop Down Item 5");

        // test intermittent without this wait
        waitForOption5DropDownSelected();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();

    }

    /**
     * Helper methods
     */

    private void waitForOption5DropDownSelected() {
        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeSelected(
                        By.cssSelector("option[value='dd5']")));
    }

    private void clickSubmitButton() {
        WebElement submitButton;
        submitButton = driver.findElement(
                By.cssSelector(
                        "input[type='submit'][name='submitbutton']"));

        submitButton.click();
    }

    private void assertDropdownValueIsCorrect() {
        WebElement dropDownResult;

        dropDownResult = driver.findElement(By.id("_valuedropdown"));

        assertEquals("expected drop down 5", dropDownResult.getText(), "dd5");
    }

    /**
     * In early versions of WebDriver we were able to sendkeys to input files to
     * edit them.
     *
     * This approach no longer works.
     *
     * SendKeys is not a suitable approach for complex manipulation but it works for typing into fields
     * and simple search manipulation.
     *
     * I left this code here as a historic marker. It might be possible to use a combination of Actions to do this,
     * we cover Actions later in the course.
     */
    @Ignore("this approach no longer works")
    @Test
    public void submitFormWithDropDownFiveUsingKeyboardSpecialKeys(){
        WebElement dropDownSelect;
        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownSelect.sendKeys(
                Keys.chord(
                    Keys.HOME,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN,
                    Keys.ARROW_DOWN));

        waitForOption5DropDownSelected();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();

    }




}

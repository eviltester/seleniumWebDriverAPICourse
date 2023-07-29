package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.After;
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

    WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
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


    /**
     * Helper methods
     */

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

    @After
    public void close(){
        Driver.close();
    }




}

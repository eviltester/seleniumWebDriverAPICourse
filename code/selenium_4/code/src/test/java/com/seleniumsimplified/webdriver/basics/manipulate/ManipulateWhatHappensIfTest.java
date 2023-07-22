package com.seleniumsimplified.webdriver.basics.manipulate;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.fail;

public class ManipulateWhatHappensIfTest {

    private static WebDriver driver;

    @Before
    public void setup(){
        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());
    }


    @Test
    public void canIClearACheckbox(){

        WebElement checkBox1;

        checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));

        // select it
        if(!checkBox1.isSelected()){
            checkBox1.click();
        }

        try{
            checkBox1.clear();

            // HTMLUnit does not throw an exception when
            // trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){
                if(!checkBox1.isSelected()){
                   fail("can now clear a checkbox");
                }
            }else{
                fail("Expected an exception as you can't clear a checkbox");
            }

        }catch(WebDriverException e){
            // expected an exception, you can't clear a multi select
            // you have to click to remove check
        }
    }

    @Test
    public void canIClearAMultiSelect(){

        WebElement multiSelect;

        multiSelect = driver.findElement(By.cssSelector("select[multiple='multiple']"));

        try{
            multiSelect.clear();

            // HTMLUnit does not throw an exception when
            // trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){

                Select multi = new Select(multiSelect);

                if(multi.getAllSelectedOptions().size() == 0){
                    fail("can now clear a multi select");
                }
            }else{
                fail("Expected an exception as you can't clear a multi select");
            }

        }catch(WebDriverException e){
            // expected an exception, you can't clear a multi select
            // you have to click to remove items
        }
    }

    @Test
    public void canIClearADropDownSelect(){

        WebElement dropDownSelect;

        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));

        try{
            dropDownSelect.clear();

            // neither HTMLUnit throw an exception when
            // trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){

                Select dropDown = new Select(dropDownSelect);

                if(dropDown.getAllSelectedOptions().size() == 0){
                    fail("can now clear a drop down select");
                }
            }else{
                fail("Expected an exception as you can't clear a drop down");
            }

        }catch(WebDriverException e){
            // expected an exception, you can't clear a drop down
            // you have to click to remove items
        }
    }

    @Test
    public void canIClearARadiobutton(){

        WebElement radioButton;

        radioButton = driver.findElement(By.cssSelector("input[value='rd2']"));

        try{
            radioButton.clear();

            // neither HTMLUnit throw an exception when
            // trying to clear this element, but other drivers do
            if(Driver.currentBrowser() == Driver.BrowserName.HTMLUNIT){

                if(!radioButton.isSelected()){
                    fail("can now clear a radio button");
                }
            }else{
                fail("Expected an exception as you can't clear a radio button");
            }

        }catch(WebDriverException e){
            // expected an exception, you can't clear a radio button
            // you have to click on another to remove selected
        }
    }
}

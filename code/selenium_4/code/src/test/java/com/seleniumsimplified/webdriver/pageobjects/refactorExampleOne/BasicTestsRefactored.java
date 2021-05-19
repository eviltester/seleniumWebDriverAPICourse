package com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.pages.BasicAjaxPageObject;
import com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.pages.ProcessedFormPage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.pages.BasicAjaxPageObject.Category;

public class BasicTestsRefactored {

    private WebDriver driver;
    private BasicAjaxPageObject basicAjaxPage;

    @Before
    public void setupTest(){
        driver = Driver.get();
        basicAjaxPage = new BasicAjaxPageObject(driver);
        basicAjaxPage.get();
    }

    @Test
    public void chooseToCodeInJavaOnTheServerFromCombosSyncOnAjaxBusyExample(){

        basicAjaxPage.selectCategory(Category.SERVER);
        basicAjaxPage.selectLanguage(23);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Java code", "23",processedFormPage.getValueFor("language_id"));


    }

    @Ignore("exercise: investigate and fix this broken test")
    @Test
    public void chooseToCodeInJavascriptOnTheWeb(){

        // todo: exercise - investigate and fix this broken test
        // this test has been written as per the specification
        // upon selecting web, the JavaScript entry should be index 0
        // but the page hasn't quite been written that way
        // the page defaults to Web as the default option,
        // so selecting Web doesn't change anything and
        // no XHR request is made to the server
        // there might be something wrong with the way
        // the default HTML is written.
        //
        // Can you add a workaround so that the test passes?
        //
        // And then identify what defect you need to raise

        basicAjaxPage.selectCategory(Category.WEB);
        basicAjaxPage.selectLanguage(0);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Javascript code", "0",processedFormPage.getValueFor("language_id"));

    }

    @Test
    public void chooseToCodeInCppOnDesktop(){

        basicAjaxPage.selectCategory(Category.DESKTOP);
        basicAjaxPage.selectLanguage(10);
        basicAjaxPage.clickCodeInIt();

        ProcessedFormPage processedFormPage = new ProcessedFormPage(driver);
        processedFormPage.waitUntilPageIsLoaded();

        assertEquals("Expected Cpp code", "10",processedFormPage.getValueFor("language_id"));
    }

}

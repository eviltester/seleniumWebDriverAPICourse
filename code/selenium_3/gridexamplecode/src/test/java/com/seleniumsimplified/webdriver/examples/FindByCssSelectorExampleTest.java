package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindByCssSelectorExampleTest {

    WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");
    }

    @Test
    public void findByIdUsingCSSId(){

        WebElement element;
        element = driver.findElement(
                By.cssSelector("#p3"));

        assertEquals("expected a match on id",
                "This is c paragraph text",
                element.getText());
    }


    @After
    public void closeBrowser(){
        Driver.close();
    }
}

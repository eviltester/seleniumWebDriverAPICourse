package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FindByIDOrNameExampleTest {

    WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.eviltester.com/styled/find-by-playground-test.html");
    }

    @Test
    public void findByIdOrNameMatchOnName(){

        WebElement element;
        element = driver.findElement(
                             new ByIdOrName("pName2"));

        assertEquals("expected a match on name",
                "This is b paragraph text",
                element.getText());
    }

    @Test
    public void findByIdOrNameMatchOnId(){

        WebElement element;
        element = driver.findElement(
                new ByIdOrName("p3"));

        assertEquals("expected a match on id",
                "This is c paragraph text",
                element.getText());
    }

    @After
    public void closeBrowser(){
        Driver.close();
    }
}

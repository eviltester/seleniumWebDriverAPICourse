package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class AFirstFindByExampleTest {

    WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");
    }

    @Test
    public void findByID(){
        WebElement cParagraph = driver.findElement(By.id("p3"));
        assertEquals("This is c paragraph text",
                     cParagraph.getText());
    }

    @After
    public void closeBrowser(){
        Driver.close();
    }
}

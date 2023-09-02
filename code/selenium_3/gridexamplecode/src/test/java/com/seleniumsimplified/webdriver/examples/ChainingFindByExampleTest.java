package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ChainingFindByExampleTest {

    WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.eviltester.com/styled/find-by-playground-test.html");
    }

    @Test
    public void chainingWithFindElement(){

        WebElement element = driver.findElement(By.id("div1")).
                                    findElement(By.name("pName3")).
                                    findElement(By.tagName("a"));

        assertEquals("expected a different id",
                     "a3",
                     element.getAttribute("id"));
    }

    @After
    public void close(){
        Driver.close();
    }
}

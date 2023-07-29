package com.seleniumsimplified.webdriver.examples;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ManipulateExampleTest {

    static WebDriver driver;

    @Before
    public void createDriverAndVisitTestPage(){
        driver = Driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");
    }

    @Test
    public void simpleInteraction(){
        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse("Starts not selected",
                checkBox1.isSelected());

        checkBox1.click();

        assertTrue("Click selects",
                checkBox1.isSelected());
    }


    @After
    public void closeBrowser(){
        Driver.close();
    }
}

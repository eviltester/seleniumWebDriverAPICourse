package com.seleniumsimplified.webdriver.userinteractions;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ComparisonFailure;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.*;

public class UserInteractionsExercisesTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setup(){
        driver = Driver.get(SiteUrls.dragAndDropPageUrl());
    }

    @Before
    public void resetPage(){
        driver.navigate().refresh();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("draggable1")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("droppable1")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("draggable2")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("droppable2")));

    }

    @Test
    public void moveDraggable1ToDroppable1(){
        WebElement draggable1 = driver.findElement(By.id("draggable1"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(draggable1).moveToElement(droppable1).release().perform();

        assertEquals("Dropped!", droppable1.getText());

    }

    @Test
    public void dragAndDropDraggable2ToDroppable1(){
        WebElement draggable2 = driver.findElement(By.id("draggable2"));
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);

        // old code had a bug where I released after the drag and drop,
        // that now throws an error so removed spurious release
        actions.dragAndDrop(draggable2,droppable1).perform();

        assertEquals("Get Off Me!", droppable1.getText());

    }


    @Test
    public void controlAndSpace(){
        /*
            when I press control+space the red squares say "Let GO!!"
            we can't check this
         */
        WebElement droppable1 = driver.findElement(By.id("droppable1"));

        Actions actions = new Actions(driver);
        actions.click(droppable1).build().perform();
        // sendkeys does a keydown followed by keyup, so you can't use it for this
        // as keys need to be held down
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SPACE).build().perform();
        String dropText = droppable1.getText();
        actions.keyUp(droppable1,Keys.CONTROL).build().perform();

        try{
            assertEquals("Let GO!!", dropText);
            fail("send keys should not be held down long enough to get the text");
        }catch(ComparisonFailure e){
            assertTrue("How can we hold down the keys?",true);
            assertEquals("Drop Here", dropText);
        }
    }


    @Test
    public void controlAndBwaHaHa(){
        /* when we issue a control+ B draggable 1 says "Bwa! Ha! Ha! */

        WebElement draggable1 = driver.findElement(By.id("draggable1"));

        Actions actions = new Actions(driver);

        draggable1.click();

        new Actions(driver).keyDown(Keys.CONTROL).
                            sendKeys("b").
                            keyUp(Keys.CONTROL).
                            perform();

        assertEquals("Bwa! Ha! Ha!", draggable1.getText());

        // firefox used to fail on this when it did a keyup after every keyDown
    }

}

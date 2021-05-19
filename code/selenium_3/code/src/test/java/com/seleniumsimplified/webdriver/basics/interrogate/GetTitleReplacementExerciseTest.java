package com.seleniumsimplified.webdriver.basics.interrogate;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class GetTitleReplacementExerciseTest {

    static WebDriver driver;
    private final String pageTitle = "Welcome to the Find By Playground";

    /*
    Warning : WebDriver has changed, so getText on title no longer works.
                but getAttribute("text") does
     */

    /*
    There was an exercise in the course to "create an alternative to the driver.getTitle command.

    I removed it from the course because it worked, then didn't then worked on some browsers and not others.

    I have @Ignore (d) the answers in here in case you want to try them out. But this is no longer formally
    part of the course. Treat it as a curiosity if you find this code.

    At the moment (16/6/2013) you can use "text" as an attribute to get the text of an element

    Also, previously IE through an error when you tried to access elements in the head, but now it does not.

     */


    @BeforeClass
    public static void createDriverAndVisitTestPage(){
        //driver = new FirefoxDriver();
        driver = Driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/find_by_playground.php");
    }

    @Test
    public void driverGetTitleSanity(){

        assertThat(pageTitle, is(driver.getTitle()));
    }

    @Test
    public void driverGetTitleWithTagName(){

        WebElement element;

        element = driver.findElement(By.tagName("title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle, is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleFromPageSource(){

        WebElement element;

        int titleStart = driver.getPageSource().toLowerCase().indexOf("<title>");
        int titleEnd = driver.getPageSource().toLowerCase().indexOf("</title>");
        String titleText = driver.getPageSource().substring(titleStart+7, titleEnd);

        assertThat(pageTitle, is(titleText));
    }

    @Test
    public void driverGetTitleWithXPathAbsolute(){

        WebElement element;

        element = driver.findElement(By.xpath("/html/head/title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleWithXPathAny(){

        WebElement element;

        element = driver.findElement(By.xpath("//title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleWithCSSAny(){

        WebElement element;

        element = driver.findElement(By.cssSelector("title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleWithJavaScript(){

        WebElement element;

        element = driver.findElement(By.cssSelector("title"));

        // use JavaScript to get the text of the element
        String titleTextViaJavaScript = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",element);

        assertThat(pageTitle,is(titleTextViaJavaScript));
    }

    @Test
    public void driverGetTitleWithCSSAbsolute(){

        WebElement element;

        element = driver.findElement(By.cssSelector("head > title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleWithCSSAbsoluteFromRoot(){

        WebElement element;

        element = driver.findElement(By.cssSelector("html > head > title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }

    @Test
    public void driverGetTitleWithCSSFromRootSkippingHead(){

        WebElement element;

        element = driver.findElement(By.cssSelector("html title"));

        // getTextNow returns "" for head elements
        //assertThat(pageTitle, is(element.getText()));
        assertThat(pageTitle,is(element.getAttribute("text")));
    }


    @AfterClass
    public static void closeBrowser(){
        //driver.quit();
    }
}

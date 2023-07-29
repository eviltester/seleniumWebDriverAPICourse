package com.seleniumsimplified.webdriver.remote;

import com.seleniumsimplified.environmentmanager.Props;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.Assert.*;

public class CloudRemoteTest {

    public static WebDriver driver=null;

    @BeforeClass
    public static void connectToGrid(){

        String theUrl = "";

        MutableCapabilities capabilities = new FirefoxOptions();
        //MutableCapabilities capabilities = new ChromeOptions();

        // e.g.
        //capabilities.setCapability("platform", Platform.MAC);
        //capabilities.setCapability("platform", Platform.LINUX);
        capabilities.setCapability("platform", Platform.WINDOWS);

        // TODO: configure your chosen cloud platform
        // some example setups are shown for a few vendors
        // Vendors include, but are not limited to:
        // https://saucelabs.com - plans: trial, paid and open source
        // https://browserstack.com - plans: trial, paid and open source
        // https://www.lambdatest.com/ - plans: trial, paid
        // https://www.gridlastic.com - plans: free, paid
        // https://testingbot.com - plans: trial, paid, and open source
        // https://crossbrowsertesting.com/

        // add username and access key via environment variables
        // or property to avoid releasing with source

        // SauceLabs use a username and accesskey added as properties - trial account available, open source account available
        // and a URL
        // https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example
//        capabilities.setCapability("username", Props.getEnvOrProperty("cloud.username"));
//        capabilities.setCapability("accessKey", Props.getEnvOrProperty("cloud.accesskey"));
//        theUrl = "https://ondemand.saucelabs.com/wd/hub";


        // BrowserStack use a username and accesskey - trial account available, open source account available
        // embedded in the remote URL
        //https://www.browserstack.com/automate/java
//        theUrl = "https://" +
//                        Props.getEnvOrProperty("cloud.username") + ":" +
//                        Props.getEnvOrProperty("cloud.accesskey") +
//                        "@hub-cloud.browserstack.com/wd/hub";

        // GridLastic.com have a free account available
        // https://www.gridlastic.com/selenium-grid-demo.html
        // free account
        // - start a grid https://www.gridlastic.com/grid-configuration.php
        // - wait for it to start and page to refresh
        // - change test code to use the configuration options shown
        // - ie. use the "API endpoint with hub username and password" url
        //
        // - run test
        //theUrl = "";
        //capabilities.setCapability("video", "True");

        try {

            driver = new RemoteWebDriver( new URL(theUrl), capabilities);

            System.out.println(
                    String.format("Session ID : %s",
                                    ((RemoteWebDriver)driver).getSessionId()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleInteraction(){
       driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse("Starts not selected",
                checkBox1.isSelected());

        checkBox1.click();

        assertTrue("Click selects",
                checkBox1.isSelected());
    }

    @Test
    public void findByIdOrNameMatchOnId(){

        driver.get("https://testpages.herokuapp.com/styled/find-by-playground-test.html");

        WebElement element;
        element = driver.findElement(
                new ByIdOrName("p3"));

        assertEquals("expected a match on id",
                "This is c paragraph text",
                element.getText());
    }

    @Test
    public void submitFormWithDropDown5SelectedChainOfFindElements(){

        WebElement dropDownSelect;
        WebElement dropDownOption;

        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        dropDownSelect = driver.findElement(By.cssSelector("select[name='dropdown']"));
        dropDownOption = dropDownSelect.findElement(By.cssSelector("option[value='dd5']"));
        dropDownOption.click();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
    }

    @Test
    public void submitFormWithDropDown5SelectedOptionFiveDirect(){

        driver.get("https://testpages.herokuapp.com/styled/basic-html-form-test.html");

        driver.findElement(By.cssSelector("option[value='dd5']")).click();

        clickSubmitButton();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        assertDropdownValueIsCorrect();
    }

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

    @AfterClass
    public static void stopSauce(){
        driver.quit();
    }
}

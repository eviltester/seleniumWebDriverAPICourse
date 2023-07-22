package com.seleniumsimplified.webdriver.mobile;

//import org.openqa.selenium.android.AndroidDriver;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Ignore
public class AndroidTest {

    // appium is now the recommended approach for automating on Android and ios

    public static WebDriver driver=null;

    @BeforeClass
    public static void connectToAppium(){
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // TODO: use the device name can be seen when you do "adb devices"
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", Platform.ANDROID);
        // if you are using an up to date version of android to you can use "Chrome" as the app,
        // otherwise "browser" is the default 'browser' on Android
        capabilities.setCapability("app", "browser");


        try {
            // TODOD add the url andd port of your Appium server
            String appiumURL = "http://127.0.0.1:4723/wd/hub";
            driver = new RemoteWebDriver(
                    new URL(appiumURL),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void simpleInteraction(){
        driver.get("https://testpages.herokuapp.com/basic_html_form.html");

        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse("Starts not selected",
                checkBox1.isSelected());

        checkBox1.click();

        assertTrue("Click selects",
                checkBox1.isSelected());

        driver.quit();
    }
}

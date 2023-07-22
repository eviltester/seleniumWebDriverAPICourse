package com.seleniumsimplified.webdriver.drivers.onpath;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * cover nuances with the Chrome Driver
 */
public class ChromeDriverPathTest {

    // As of Chrome v35, it reports an error regarding --ignore-certificate-errors
    // to remove this error start the chrome driver with
    // args "test-type"


    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // if we don't tell webdriver where to find the chrome driver
        // it will look on the path
        //String currentDir = System.getProperty("user.dir");
        //String chromeDriverLocation = currentDir + "/../tools/chromedriver/chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        // add the folder of the chrome driver to the system path variable
        // (you'll want to restart the IDE)

        // you cn check if it is in the path by running 'chromedriver.exe' from any folder
        // if it runs, then chrome driver is on the path

        // then run the test


    }

    @Ignore("Test requires chromedriver on the path")
    @Test
    public void basicChromeUsage(){

        WebDriver chrome = new ChromeDriver();

        chrome.get("https://testpages.herokuapp.com/basic_html_form.html");

        assertThat(chrome.getTitle(), is("HTML Form Elements"));

        chrome.quit();
    }



}

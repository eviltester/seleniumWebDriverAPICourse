package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * cover nuances with the Chrome Driver
 */
public class ChromeDriverTest {

    // As of Chrome v35, it reports an error regarding --ignore-certificate-errors
    // to remove this error start the chrome driver with
    // args "test-type"


    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // tell webdriver where to find the chrome driver
        String currentDir = System.getProperty("user.dir");
        String chromeDriverLocation = currentDir + "/../tools/chromedriver/chromedriver.exe";

        // if chrome is on your path then you do not need to set the location
        // if this test fails then Chrome might not be on your path and you may need to configure the property above
        //System.setProperty("webdriver.chrome.driver", chromeDriverLocation);


    }


    // You may see an error regarding --ignore-certificate-errors when using Chrome v35 or above
    // as the following test does not use the args "test-type"
    @Test
    public void basicChromeUsage(){

        WebDriver chrome = new ChromeDriver();

        chrome.get(SiteUrls.basicHtmlFormPageUrl());

        assertThat(chrome.getTitle(), is("HTML Form Elements"));

        chrome.quit();
    }

    @Test
    /*
      * Chrome is supported by WebDriver, on linux it may not
      * find the location of your browser
      * the 'which' command for chromium-browser may find it
      */
    public void basicChromeDriverOptions(){

        // http://peter.sh/experiments/chromium-command-line-switches/

        // with Chrome v35 it now reports an error on --ignore-certificate-errors
        // so call with args "test-type"
        // https://code.google.com/p/chromedriver/issues/detail?id=799&q=ignore-certificate-errors&colspec=ID%20Status%20Pri%20Owner%20Summary

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("disable-plugins");
        options.addArguments("disable-extensions");


        WebDriver chrome = new ChromeDriver(options);

        chrome.get(SiteUrls.basicHtmlFormPageUrl());

        assertThat(chrome.getTitle(), is("HTML Form Elements"));

        chrome.quit();
    }


    @Test
    public void basicChromeDriverProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            // http://peter.sh/experiments/chromium-command-line-switches/
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("disable-plugins");
            options.addArguments("disable-extensions");
            options.addArguments("proxy-server=" + Driver.PROXY);
            //options.setProxy(new Proxy().setHttpProxy(Driver.PROXYHOST + ":" + Driver.PROXYPORT));
            // can also configure with setProxy
            options.addArguments("proxy-server=" + Driver.PROXY);

            WebDriver chrome = new ChromeDriver(options);

            chrome.get(SiteUrls.basicHtmlFormPageUrl());

            assertThat(chrome.getTitle(), is("HTML Form Elements"));

            chrome.quit();

        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test basicChromeDriverProxy");
        }
    }



}

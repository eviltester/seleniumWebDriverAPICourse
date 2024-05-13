package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**

We can use ChromeDriver as a headless driver.

This means that it doesn't start up and show the browser
rendering in a window, instead all the rendering is done
in memory.

Unlike HTMLUnit driver, which is also a headless driver.
Running ChromeDriver in headless mode means that all JavaScript
will be executed properly and it is the full driver experience.

But there is simply nothing rendered on screen.

 This can be faster, and when used on a Continuous Integration
 machine means we don't have as many browser windows popping open.

Creating a Headless Browser
===========================

~~~~~~~~
 ChromeOptions options = new ChromeOptions();

 options.setHeadless(true);

 WebDriver driver = new ChromeDriver(options);
~~~~~~~~

We can also set headless mode via an argument at the command line e.g.

~~~~~~~~
 ChromeOptions options = new ChromeOptions();

 options.addArguments("--headless");

 WebDriver driver = new ChromeDriver(options);
~~~~~~~~

The setHeadless method on options is probably the safest way to do this as it
would be updated if the name of the command line argument changed.

Everything else that you do with the browser is the same in headless mode
as it is in visual mode.

Chrome in headless mode is usually a good replacement for HTMLUnit
and the JavaScript processing is better.

User Agent Header Changes
================

One other advantage that Chrome offers, which HTMLUnit does not, is that we
can change the user-agent header and pretend to be other browsers.

We might do this if we we testing if the server rendered different pages for
mobile devices compared to desktop devices.

We can change the user-agent header using options and adding arguments:

~~~~~~~~
 ChromeOptions options = new ChromeOptions();
 options.setHeadless(true);

 // pretending to be a Galaxy Note II mobile
 options.addArguments("user-agent=Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 " +
 "Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

 WebDriver driver = new ChromeDriver(options);
~~~~~~~~

Using A Proxy
=========

We can still use a proxy in headless mode, and this is quite useful because we may
be changing the user-agent and simulating mobile devices.

NOTE: setting the setSslProxy is important otherwise it
will not send https traffic through the proxy.

~~~~~~~~
 ChromeOptions options = new ChromeOptions();
 options.setHeadless(true);

 options.setProxy(new Proxy().
                        setHttpProxy(Driver.PROXY).
                        setSslProxy(Driver.PROXY));
~~~~~~~~

We can also set proxy from arguments, in which case we only need one setting to cover both
http and https.

~~~~~~~~
 options.addArguments("proxy-server=" + Driver.PROXY);
~~~~~~~~


 Closing Driver
========

We still need to close the driver even though we can't see the browser.

~~~~~~~~
 driver.quit();
~~~~~~~~

 */
public class ChromeDriverHeadlessTest {

    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // if chrome is on your path then you do not need to set the location

        // tell webdriver where to find the chrome driver
        // String currentDir = System.getProperty("user.dir");
        // String chromeDriverLocation = currentDir + "/../tools/chromedriver/chromedriver.exe";

        // if this test fails then Chrome might not be on your path and you may need to configure the property above
        //System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
    }

    @Test
    public void headlessChromeUsageViaArguments(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        driver.get(SiteUrls.basicWebPageUrl());

        Assert.assertEquals(driver.getTitle(), "Basic Web Page Title");

        driver.quit();
    }

    @Test
    public void headlessChromeUsingUserAgentArguments(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // pretending to be a Galaxy Note II mobile
        // Note, we don't have to use headless mode to change the useragent
        // but most of the time if we are pretending to be other browsers we
        // will probably do that headless
        options.addArguments("user-agent=Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        WebDriver driver = new ChromeDriver(options);

        driver.get(SiteUrls.useragentRedirectPageUrl());

        Assert.assertTrue(driver.getTitle().contains("Mobile"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/mobile/"));

        driver.close();

        driver.quit();
    }


    @Test
    public void headlessChromeDriverProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            // http://peter.sh/experiments/chromium-command-line-switches/
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");

            // NOTE: setting the setSslProxy is important otherwise it
            // will not send https traffic through the proxy
            options.setProxy(new Proxy().setHttpProxy(Driver.PROXY).
                                        setSslProxy(Driver.PROXY));

            // setting proxy via arguments will cover http and https
            //options.addArguments("proxy-server=" + Driver.PROXY);

            WebDriver driver = new ChromeDriver(options);

            driver.get(SiteUrls.basicWebPageUrl());

            Assert.assertEquals(driver.getTitle(), "Basic Web Page Title");

            driver.quit();

        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test basicChromeDriverProxy");
        }
    }

}

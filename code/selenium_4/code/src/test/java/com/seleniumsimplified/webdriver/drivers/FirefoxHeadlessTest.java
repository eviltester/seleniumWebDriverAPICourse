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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**

We can use FirefoxDriver as a headless driver.

This means that it doesn't start up and show the browser
rendering in a window, instead all the rendering is done
in memory.

Unlike HTMLUnit driver, which is also a headless driver.
Running FirefoxDriver in headless mode means that all JavaScript
will be executed properly and it is the full driver experience.

But there is simply nothing rendered on screen.

 This can be faster, and when used on a Continuous Integration
 machine means we don't have as many browser windows popping open.

Creating a Headless Browser
===========================

~~~~~~~~
 FirefoxOptions options = new FirefoxOptions();

 options.setHeadless(true);

 WebDriver driver = new FirefoxDriver(options);
~~~~~~~~

We can also set headless mode via an argument at the command line e.g.

~~~~~~~~
 FirefoxOptions options = new FirefoxOptions();

 options.addArguments("-headless");

 WebDriver driver = new FirefoxDriver(options);
~~~~~~~~

The setHeadless method on options is probably the safest way to do this as it
would be updated if the name of the command line argument changed.

Everything else that you do with the browser is the same in headless mode
as it is in visual mode.

Firefox in headless mode is usually a good replacement for HTMLUnit
and the JavaScript processing is better.

User Agent Header Changes
================

One other advantage that Firefox offers, which HTMLUnit does not, is that we
can change the user-agent header and pretend to be other browsers.

We might do this if we we testing if the server rendered different pages for
mobile devices compared to desktop devices.

We can change the user-agent header using options and adding a profile:

~~~~~~~~
 FirefoxOptions options = new FirefoxOptions();

 FirefoxProfile profile = new FirefoxProfile();

 profile.setPreference("general.useragent.override",
 "Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) " +
 "AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

 options.setProfile(profile);

 WebDriver driver = new FirefoxDriver(options);
~~~~~~~~

Using A Proxy
=========

We can still use a proxy in headless mode, and this is quite useful because we may
be changing the user-agent and simulating mobile devices.

NOTE: setting the setSslProxy is important otherwise it
 will not send https traffic through the proxy.

~~~~~~~~
 FirefoxOptions options = new FirefoxOptions();
 options.setHeadless(true);

 options.setProxy(
        new Proxy().setHttpProxy(Driver.PROXY).
                    setSslProxy(Driver.PROXY));

 WebDriver driver = new FirefoxDriver(options);
~~~~~~~~


Closing Driver
========

We still need to close the driver even though we can't see the browser.

~~~~~~~~
 driver.close();
~~~~~~~~

 */
public class FirefoxHeadlessTest {

    @BeforeClass
    public static void setupTheFirefoxDriverSystemProperty(){

        // if Firefox is on your path then you do not need to set the location

        // tell webdriver where to find the chrome driver
        // String currentDir = System.getProperty("user.dir");
        // String firefoxDriverLocation = currentDir + "/../tools/firefoxdriver/firefoxdriver.exe";

        // if this test fails then Firefox might not be on your path and you may need to configure the property above
        //System.setProperty("webdriver.firefox.driver", firefoxDriverLocation);
    }

    @Test
    public void basicHeadlessFirefoxUsage(){

        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);

        WebDriver driver = new FirefoxDriver(options);

        driver.get(SiteUrls.basicWebPageUrl());

        Assert.assertEquals(driver.getTitle(), "Basic Web Page Title");

        driver.close();
    }

    @Test
    public void headlessFirefoxUsageViaArguments(){

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");

        WebDriver driver = new FirefoxDriver(options);

        driver.get(SiteUrls.basicWebPageUrl());

        Assert.assertEquals(driver.getTitle(), "Basic Web Page Title");

        driver.close();
    }

    @Test
    public void headlessFirefoxUsingUserAgent(){

        FirefoxOptions options = new FirefoxOptions();

        // Note, we don't have to use headless mode to change the useragent
        // but most of the time if we are pretending to be other browsers we
        // will probably do that headless
        options.setHeadless(true);

        // pretending to be a Galaxy Note II mobile by setting useragent
        // via a Firefox profile
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("general.useragent.override",
                "Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) " +
                        "AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        options.setProfile(profile);


        WebDriver driver = new FirefoxDriver(options);

        driver.get(SiteUrls.useragentRedirectPageUrl());

        Assert.assertTrue(driver.getTitle().contains("Mobile"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/mobile/"));

        driver.close();
    }

    @Test
    public void headlessFirefoxUsingProxy(){

        if(!ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {
            System.out.println(
                    "No Proxy seemed to be running on " +
                    Driver.PROXY +
                    " so didn't run test headlessFirefoxUsingUserAgentProfileAndProxy");
            return;
        }

        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);

        // NOTE: setting the setSslProxy is important otherwise it
        // will not send https traffic through the proxy
        options.setProxy(new Proxy().setHttpProxy(Driver.PROXY).
                                    setSslProxy(Driver.PROXY));

        WebDriver driver = new FirefoxDriver(options);

        driver.get(SiteUrls.basicWebPageUrl());

        Assert.assertEquals(driver.getTitle(), "Basic Web Page Title");

        driver.close();
    }


}

package com.seleniumsimplified.webdriver.drivers;


import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FirefoxDriverTest {

    // Code for section "Different Browsers"
    // video FirefoxDriver Introduction

    /*
        Firefox requires installation of the GeckoDriver

        - https://github.com/mozilla/geckodriver
        - https://github.com/mozilla/geckodriver/releases

        - Install on Mac:

        ~~~~~~~~
        brew install geckodriver
        ~~~~~~~~

        - Install on Windows with Chocolatey

        - https://chocolatey.org/packages/selenium-gecko-driver

        ~~~~~~~~
        choco install selenium-gecko-driver
        ~~~~~~~~

     */
    @Test
    public void basicFirefoxDriver(){

        WebDriver firefox = new FirefoxDriver();

        firefox.get(SiteUrls.rootUrl());

        Assert.assertTrue(firefox.getTitle().contains("Test Pages"));

        firefox.quit();
    }

    @Test
    public void firefoxDriverWithProfile(){

        FirefoxProfile profile = new FirefoxProfile();

        // WebDriver firefox = new FirefoxDriver(profile);
        // can add extensions to profile if you need to
        // profile.addExtension(...);


        // e.g. set a different user agent to pretend to be Galaxy II phone
        profile.setPreference("general.useragent.override",
                "Mozilla/5.0 (Linux; U; Android 4.1; en-us; GT-N7100 Build/JRO03C) " +
                "AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);
        WebDriver firefox = new FirefoxDriver(options);

        firefox.get(SiteUrls.rootUrl());

        Assert.assertTrue(firefox.getTitle().contains("Test Pages"));

        firefox.quit();
    }


    @Test
    public void firefoxDriverWithCapabilitiesForProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            // Proxies need to be configured correctly, otherwise SSL errors can occur
            // by default Firefox doesn't let you browse with misconfigured SSL sites
            org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
            proxy.setHttpProxy(Driver.PROXY)
                 .setFtpProxy(Driver.PROXY)
                 .setSslProxy(Driver.PROXY);

            // using the following did not seem to setup Firefox to use mis-configured proxy
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.PROXY, proxy);
//            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//            FirefoxOptions options = new FirefoxOptions(capabilities);

            // cannot assume that proxy has been configured correctly
            // so try to ignore errors by creating a profile
            // and using options to go through proxy
            FirefoxOptions options = new FirefoxOptions();
            final FirefoxProfile profile = new FirefoxProfile();
            profile.setAcceptUntrustedCertificates(true);
            profile.setAssumeUntrustedCertificateIssuer(true);
            options.setProfile(profile);
            options.setProxy(proxy);

            WebDriver firefox = new FirefoxDriver(options);

            firefox.get(SiteUrls.rootUrl());

            Assert.assertTrue(firefox.getTitle().contains("Test Pages"));

            firefox.quit();
        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                    Driver.PROXY +
                    " so didn't run test firefoxDriverWithCapabilitiesForProxy");
        }
    }

    // have deprecated a Plugin example because we no longer use Firebug
    // sample code for using an extension is online in this gist
    // https://gist.github.com/eviltester/02a655be5b22b73fce2f9316e1eab6d4

}

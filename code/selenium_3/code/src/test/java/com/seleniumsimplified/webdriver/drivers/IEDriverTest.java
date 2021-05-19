package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IEDriverTest {

    @BeforeClass
    public static void setupTheIEDriverSystemProperty(){

        // tell webdriver where to find the IE driver
        String currentUserDir = System.getProperty("user.dir");
        String IEDriverLocation = currentUserDir + "/../tools/iedriver_32/IEDriverServer.exe";
        System.setProperty("webdriver.ie.driver", IEDriverLocation);
    }

    @Test
    public void basicIEDriverTest(){

        WebDriver iedriver = new InternetExplorerDriver();

        iedriver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

        assertThat(iedriver.getTitle(), is("HTML Form Elements"));

        iedriver.quit();
    }

    @Test
    public void ieDriverProxyTest(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            Proxy proxy = new Proxy();
            proxy.setHttpProxy(Driver.PROXY)
                    .setFtpProxy(Driver.PROXY)
                    .setSslProxy(Driver.PROXY);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(CapabilityType.PROXY, proxy);

            WebDriver iedriver = new InternetExplorerDriver(capabilities);

            iedriver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

            assertThat(iedriver.getTitle(), is("HTML Form Elements"));

            iedriver.quit();

        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test ieDriverProxyTest");
        }
    }

}

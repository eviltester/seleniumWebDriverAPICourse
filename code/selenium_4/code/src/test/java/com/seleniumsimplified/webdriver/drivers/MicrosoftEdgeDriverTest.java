package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Alan on 28/07/2016.
 */
public class MicrosoftEdgeDriverTest {

    /*

    Microsoft Edge Driver:
    Where to get it: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
    What it currently supports: https://developer.microsoft.com/en-us/microsoft-edge/platform/documentation/webdriver-commands/


    */

    @BeforeClass
    public static void setupTheChromeDriverSystemProperty(){

        // tell webdriver where to find the MicrosoftEdgeDriver driver
        // for this test it is in a tools directory which is a peer to the code/ directory of this project
        String currentDir = System.getProperty("user.dir");
        String driverLocation = currentDir + "/../tools/edgedriver/MicrosoftWebDriver.exe";
        System.setProperty("webdriver.edge.driver", driverLocation);

        // or have the folder it lives in, listed in your Path

    }

    @Test
    public void useWebDriverDefaultWrapper(){

        // webdriver comes with a default wrapper for MS Edge
        // this will either use property "webdriver.edge.driver" to find the .exe of the service or
        // will look for MicrosoftWebDriver.exe on the System Path
        // Any custom options for EdgeDriver can be found in the EdgeOptions class
        // Or use capabilities
        WebDriver driver = new EdgeDriver();

        driver.get("http://compendiumdev.co.uk/selenium/testpages/");

        // found that Edge didn't always synchronise on page load properly so I added a wait for title
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains("Selenium"));

        Assert.assertEquals("Selenium Test Pages", driver.getTitle());

        driver.close();
        driver.quit();

    }


    @Ignore("Edge does not seem to support proxy yet https://developer.microsoft.com/en-us/microsoft-edge/platform/issues/5468949/")
    @Test
    public void basicEdgeProxy(){

        //run this only if proxy is running on port 8888 e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {


            // from 3.14 we no longer use capabilities, instead we use options
            Proxy proxy = new Proxy().setHttpProxy(Driver.PROXY); // e.g. "localhost:8888"
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability(CapabilityType.PROXY, proxy);
//            WebDriver driver = new EdgeDriver(capabilities);

            WebDriver driver = new EdgeDriver(new EdgeOptions().setProxy(proxy));

            driver.get("http://www.compendiumdev.co.uk/selenium/basic_html_form.html");

            assertThat(driver.getTitle(), is("HTML Form Elements"));

            driver.close();
            driver.quit();

        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test proxy on Edge");
        }
    }
}

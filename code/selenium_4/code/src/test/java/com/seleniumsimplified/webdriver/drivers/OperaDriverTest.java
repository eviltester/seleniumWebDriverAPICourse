package com.seleniumsimplified.webdriver.drivers;

import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.manager.ProxyPort;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**

## Opera Driver

Install a current version of Opera from the official site.

Opera https://www.opera.com/

I last tried Opera on 6th November 2019, and using version 77.0.3865.120 of the Opera Driver.

CHROMIUM VERSION OF OPERA
====

Opera is now built on Chromium so the driver used is based on the chromium driver,
but maintained separately at

https://github.com/operasoftware/operachromiumdriver

https://github.com/operasoftware/operachromiumdriver/releases

CODE USAGE
==========

The code is basically the same as Chrome Driver but instead of:

~~~~~~~~
 WebDriver driver = new ChromeDriver();
~~~~~~~~

You would write:

~~~~~~~~
 WebDriver driver = new OperaDriver();
~~~~~~~~

And if you wanted to configure options you would use OperaOptions instead of ChromeOptions.

Can configure a proxy the same way as Chrome using options:

~~~~~~~~
 OperaOptions options = new OperaOptions();
 options.addArguments("proxy-server=localhost:8080");
~~~~~~~~

or

~~~~~~~~
 OperaOptions options = new OperaOptions();
 options.setProxy(new Proxy().setHttpProxy("localhost:8080"));
~~~~~~~~


INSTALL
=======

Install as per the ChromeDriver i.e.

- use Chocolatey or Brew

or

- download the executable
- add to the path
- exit all command lines and IDE before trying to use
- check that it runs by typing 'operadriver' at the command line

MAC
===

Install Opera Driver on mac using brew, or download and run yourself.

https://formulae.brew.sh/cask/operadriver

$ brew cask install operadriver

WINDOWS
=======

https://chocolatey.org/packages/selenium-opera-driver

"choco install selenium-opera-driver"

OLD VERSION OF OPERA
==========

The original Opera driver was deprecated back around version 2.34.0 of WebDriver.

It supported a maximum of Opera 12.17. The sourcecode for the old has been moved to github.

https://github.com/eviltester/operaWebDriverExample

More information can be read on my blog http://seleniumsimplified.com/2015/07/operadriver-is-now-officially-deprecated-in-my-code/

*/

public class OperaDriverTest {

    @BeforeClass
    public static void setupTheOperaDriverSystemProperty(){

        // if Opera is on your path then you do not need to set the location

        // tell webdriver where to find the chrome driver
        // String currentDir = System.getProperty("user.dir");
        // String operaDriverLocation = currentDir + "/../tools/operadriver/operadriver.exe";


        // if this test fails then Opera might not be on your path and you may need to configure the property above
        //System.setProperty("webdriver.opera.driver", operaDriverLocation);

    }

    @Test
    public void basicOperaUsage(){

        WebDriver driver = new OperaDriver();

        driver.get("https://testpages.herokuapp.com/styled/basic-web-page-test.html");

        assertThat(driver.getTitle(), is("Basic Web Page Title"));

        driver.quit();
    }

    @Test
    public void basicOperaDriverOptions(){

        OperaOptions options = new OperaOptions();
        options.addArguments("disable-extensions");

        WebDriver driver = new OperaDriver(options);

        driver.get("https://testpages.herokuapp.com/styled/basic-web-page-test.html");

        assertThat(driver.getTitle(), is("Basic Web Page Title"));

        driver.quit();
    }


    @Test
    public void basicOperaDriverProxy(){

        //run this only if proxy is running e.g. Fiddler or BrowserMobProxy or BurpSuite etc.
        if(ProxyPort.inUse(Driver.PROXYHOST, Driver.PROXYPORT)) {

            OperaOptions options = new OperaOptions();
//            options.setProxy(new Proxy().setHttpProxy(Driver.PROXYHOST + ":" + Driver.PROXYPORT));
            options.addArguments("proxy-server=" + Driver.PROXY);
            WebDriver driver = new OperaDriver(options);

            driver.get("https://testpages.herokuapp.com/styled/basic-web-page-test.html");

            assertThat(driver.getTitle(), is("Basic Web Page Title"));

            driver.quit();

        }else{
            System.out.println(
                    "No Proxy seemed to be running on " +
                            Driver.PROXY +
                            " so didn't run test basicOperaDriverProxy");
        }
    }



}

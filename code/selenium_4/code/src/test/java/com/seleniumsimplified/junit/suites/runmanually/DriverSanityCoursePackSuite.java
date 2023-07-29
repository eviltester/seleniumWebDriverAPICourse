package com.seleniumsimplified.junit.suites.runmanually;

import com.seleniumsimplified.webdriver.basics.driver.FirstFirefoxTest;
import com.seleniumsimplified.webdriver.basics.driver.FirstTest;
import com.seleniumsimplified.webdriver.basics.driver.FundamentalWhatHappensIfTest;
import com.seleniumsimplified.webdriver.drivermanager.DriverManagerTest;
import com.seleniumsimplified.webdriver.drivers.*;
import com.seleniumsimplified.webdriver.screenshots.Ex1_Answer_CanWeTakeScreenshotsTest;
import com.seleniumsimplified.webdriver.screenshots.Ex2_Answer_PersistScreenshotsTest;
import com.seleniumsimplified.webdriver.screenshots.ScreenshotsExampleTest;
import com.seleniumsimplified.webdriver.seleniumapi.WebDriverBackedSeleniumTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * This is for tests that are browser specific and control their browser setup to run
 * i.e. they don't use the Driver class so aren't cross browser
 */
@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses({
        // basics.driver
        FirstFirefoxTest.class,
        FirstTest.class,
        FundamentalWhatHappensIfTest.class,

        // drivermanager
        DriverManagerTest.class,

        // because these have hardcoded paths for drivers etc. I only run this suite in the IDE, not CI
        // drivers
        ChromeDriverTest.class,
        FirefoxDriverTest.class,
        HtmlUnitDriverTest.class,
        MicrosoftEdgeDriverTest.class,

        // screenshots
        Ex1_Answer_CanWeTakeScreenshotsTest.class,
        Ex2_Answer_PersistScreenshotsTest.class,
        ScreenshotsExampleTest.class,

        // seleniumapi
        WebDriverBackedSeleniumTest.class,

})
public class DriverSanityCoursePackSuite {

}

package com.seleniumsimplified.junit.suites;

import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExampleSelectDropDownFiveTest;
import com.seleniumsimplified.webdriver.basics.manipulate.SendKeysExamplesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingFAQsTest;
import com.seleniumsimplified.webdriver.screenshots.Ex2_Answer_PersistScreenshotsTest;
import com.seleniumsimplified.webdriver.screenshots.ScreenshotCaptureNotWrittenTest;
import com.seleniumsimplified.webdriver.screenshots.ScreenshotsExampleTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsCanvasExercisesTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests which fail to run, on HTMLUnit

 mvn test -Dtest=FailOnHTMLUnitSuite -Dselenium2basics.webdriver=HTMLUNIT

 * 20191115 Status - WebDriver 3.141.59 && HtmlUnit v 2.36
 * 20160728 Status - WebDriver 2.53.1 && HtmlUnit v 2.21
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy

        // Interrogate

        // manipulate alerts
        // HTMLUnit does not do confirm dialog handling fully
        AlertHandlingExercisesTest.class,
        AlertHandlingFAQsTest.class,

        // Manipulate Frames

        // Manipulate select Support

        // manipulate windows

        // Manipulate
        // HTML Unit does not do keyboard fully
         ManipulateExampleSelectDropDownFiveTest.class,
         SendKeysExamplesTest.class,

        // Navigation

        // cookies

        // data driven tests

        // drivermanger in DriverSanityCoursePack

        // Javascript


        // mobile in its own suite

        // Page Objects

        // remote in its own suite

        // screenshots in DriverSanityCoursePack

        // HTML Unit does support screenshots
        ScreenshotCaptureNotWrittenTest.class,

        //Synchronisation  conditions

        //Synchronisation  fluently

        //Synchronisation  fluentWiat

        //Synchronisation  implicitWait

        //Synchronisation  refactored

        //Synchronisation  webDriverWaitBasics

        //userinteractions
        // HTML Unit does not do all mouse and user interaction
        UserInteractionsExercisesTest.class,
        UserInteractionsCanvasExercisesTest.class
})
public class FailOnHTMLUnitSuite {
}

package com.seleniumsimplified.junit.suites;

import com.seleniumsimplified.webdriver.basics.interrogate.findby.AFirstFindByExampleTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.FindByCssSelectorExampleTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.FindByIDOrNameExampleTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.FirstFindByExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExercisesSubmitFileTest;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulatetExampleMultiSelectTest;
import com.seleniumsimplified.webdriver.basics.manipulate.selectSupport.SelectSupportTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.*;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
Tests which currently fail on edge

Edge is still a work in progress

https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/

Here is what is supposed to work:

https://developer.microsoft.com/en-us/microsoft-edge/platform/documentation/webdriver-commands/

mvn test -Dtest=FailOnEdgeSuite -Dselenium2basics.webdriver=EDGE

last update:

* 20160728 checked this suite against : WebDriver 2.53.1 && EdgeDriver v_10586 && Edge [Edge 25.10586 EdgeHtml 13.10586]
   * Alerts do not work and halt the entire suite so alert tests have been removed
   * removed a bunch of other tests pending investigation - will add to a FailOnEdgeSuite to allow monitoring
*/
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {


        // Fails because of getText() issue -
        AFirstFindByExampleTest.class,
        FindByCssSelectorExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FirstFindByExercisesTest.class,


        // manipulate alerts
        // Fails because alerts not supported yet
        // if you enable these the suite halt so I have removed them from even the failing suite
        //AlertHandlingExampleTest.class,
        //AlertHandlingExercisesTest.class,

        // Manipulate Frames

        // Manipulate select Support
        // Fails haven't investigate why
        SelectSupportTest.class,

        // manipulate windows



        // Manipulate
        ManipulateExercisesSubmitFileTest.class,
        // Fails on Edge - haven't investigated why
        ManipulatetExampleMultiSelectTest.class,


        // cookies
        // Fails on Edge - haven't investigated why
        CookiesExercisesTest.class,


        // drivermanger in DriverSanityCoursePack

        // Javascript

        // Fails on Edge - haven't investigated why
        //JavascriptAsyncExecutorTest.class,

        // mobile in its own suite

        // Page Objects


        // remote in its own suite

        // screenshots in DriverSanityCoursePack

        //Synchronisation  conditions



        //Synchronisation  implicitWait

        //Synchronisation  refactored

        //Synchronisation  webDriverWaitBasics


        //userinteractions
})
public class FailOnEdgeSuite {
}

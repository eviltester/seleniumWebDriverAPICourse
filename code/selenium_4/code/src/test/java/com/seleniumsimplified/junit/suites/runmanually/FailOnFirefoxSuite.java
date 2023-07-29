package com.seleniumsimplified.junit.suites.runmanually;

import com.seleniumsimplified.webdriver.basics.manipulate.SendKeysExamplesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsExerciseTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.WindowsFAQTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTestWorkWithExtraSync;
import com.seleniumsimplified.webdriver.javascript.JavascriptAsyncExecutorTest;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Last checked on Mac 20230725,
 * Firefox 115.0.2 (64-bit)
 * > geckodriver --version
 * geckodriver 0.33.0
 */
@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses({
        /*
        failing:
         switchToNewWindowHandle
         switchToNewWindowName

         This was intermittent probably a synchronisation issue.
         20230725 Added some sync on window numbers and issue did not repeat.
         */
        //WindowsExampleTest.class,

        /*
        WindowsFAQTest.class
          whatHappensIfSwitchToAwindowHaveAlreadyClosed

          above is intermittent, passes when run on its own,
          fails when run in suite

          20230725 - added some synchronisation and it worked

          WindowsFAQTest.class,
         */

        /*
         SendKeysExamplesTest.class
          - sendKeysModifierChord

          20230725 - amended the chord code to work on both firefox and chrome

          SendKeysExamplesTest.class,
         */


        /*
          JavascriptAsyncExecutorTest.class
          syncOnAjaxGifRemovalViaAsync

          Firefox doesn't seem to like injecting code that overrides
          functions meaning the test page approach here doesn't work for Firefox.
         */
        JavascriptAsyncExecutorTest.class,

        /*

        These work fine in 'prod' against testpages.herokuapp.com
        But fail in localhost.

        CookiesExercisesTestWorkWithExtraSync.class,
        - changeCookieVisitsCountUsingCookieBuilder
        - changeCookieVisitsCount

        CookiesExercisesTest.class,
        - changeCookieVisitsUsingCookieBuilder
        - changeCookieVisitsCount

        Known issue - https://github.com/mozilla/geckodriver/issues/1579

        Workaround added into the code to not set domain when on localhost

        CookiesExercisesTestWorkWithExtraSync.class,
        CookiesExercisesTest.class,
         */


        /* WindowsExampleTest.class

            switchToNewWindowName

            Firefox doesn't always seem to like switching to windows
            when the name is set by JavaScript.
         */
        WindowsExampleTest.class,

})
public class FailOnFirefoxSuite {
}

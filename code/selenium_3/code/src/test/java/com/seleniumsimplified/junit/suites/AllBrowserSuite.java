package com.seleniumsimplified.junit.suites;

import com.seleniumsimplified.junit.JUnitBeforeAndAfterTest;
import com.seleniumsimplified.junit.JUnitExampleTest;
import com.seleniumsimplified.junit.JUnitExercisesTest;
import com.seleniumsimplified.webdriver.basics.interrogate.DriverInterrogateRefactoredTest;
import com.seleniumsimplified.webdriver.basics.interrogate.DriverInterrogateTest;
import com.seleniumsimplified.webdriver.basics.interrogate.GetTitleReplacementExerciseTest;
import com.seleniumsimplified.webdriver.basics.interrogate.WebElementInterrogationTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.*;
import com.seleniumsimplified.webdriver.basics.manipulate.*;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExampleTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingExercisesTest;
import com.seleniumsimplified.webdriver.basics.manipulate.alerts.AlertHandlingFAQsTest;
import com.seleniumsimplified.webdriver.basics.manipulate.frames.*;
import com.seleniumsimplified.webdriver.basics.manipulate.iframes.*;
import com.seleniumsimplified.webdriver.basics.manipulate.selectSupport.SelectSupportTest;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.*;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationBasicsTest;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTestWorkWithExtraSync;
import com.seleniumsimplified.webdriver.datadriven.BasicDataDrivenTest;
import com.seleniumsimplified.webdriver.datadriven.CsvDataDrivenTest;
import com.seleniumsimplified.webdriver.drivermanager.DriverManager;
import com.seleniumsimplified.webdriver.javascript.JavaScriptExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptAsyncExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecuteAsyncExerciseTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecutorExampleTest;
import com.seleniumsimplified.webdriver.screenshots.ScreenshotCaptureNotWrittenTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.*;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExercisesTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitForWebElementExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluently.UseWebDriverWaitFluentlyTest;
import com.seleniumsimplified.webdriver.synchronisation.implicitWait.ImplicitWaitTest;
import com.seleniumsimplified.webdriver.synchronisation.refactored.MakeYourWaitsReadableTest;
import com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics.*;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsCanvasExercisesTest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExamplesClickDONOTUSETest;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests which should run, and pass, across any of the browsers (including headless) without amendment
 * ]
 * (this statement does not always happen, this is an aspirational suite)
 *
 * mvn test -Dtest=AllBrowserSuite -Dselenium2basics.webdriver=<insertbrowserhere>
 *
 * 20160728 Status - WebDriver 2.53.1
 * - FirefoxDriver && Firefox 47.0.1 - all tests passed
 * - ChromeDriver v2.22.397933 && Chrome Version 52.0.2743.82 m - all tests passed
 * - IEDriverServer (32 bit) v2.53.1 - 1 test failed (frames related)
 * - HTMLUnitDriver v 2.21 - 27 tests failed
 *    - see HTMLUnitSuite and FailOnHTMLUnitSuite for more details
 * Marionette and Edge are still works in progress so not all of the tests work on those yet
 * - Marionette v 0.9.0 (64 bit) && Firefox 47.0.1 - 66 tests failed using AllBrowserSuite
 *    - see MarionetteSuite and FailOnMarionetteSuite to see Marionette/Gecko status
 * - Edge - Alert tests halt the execution of suite so not run against Edge
 *    - see EdgeSuite and FailOnEdgeSuite to see edge status
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit
        JUnitBeforeAndAfterTest.class,
        JUnitExampleTest.class,
        JUnitExercisesTest.class,

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite
        // FirstFirefoxTest.class
        // FirstTest.class
        // FundamentalWhatHappensIfTest.class

        // Interrogate FindBy
        AFirstFindByExampleTest.class,
        ChainingFindByExampleTest.class,
        FindByCSSSelectorBasicExercisesFullAnswersTest.class,
        FindByCSSSelectorBasicExercisesTest.class,
        FindByCssSelectorExampleTest.class,
        FindByCSSSelectorPathsExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FindByXpathExampleTest.class,
        FindByXPathSelectorBasicExercisesFullAnswersTest.class,
        FindElementsExampleTest.class,
        FindElementsExercisesTest.class,
        FirstFindByExercisesTest.class,


        // Interrogate
        DriverInterrogateRefactoredTest.class,
        DriverInterrogateTest.class,
        GetTitleReplacementExerciseTest.class,
        WebElementInterrogationTest.class,

        // manipulate alerts
        AlertHandlingExampleTest.class,
        AlertHandlingExercisesTest.class,
        AlertHandlingFAQsTest.class,

        // Manipulate Frames
        FrameExercisesAnswersTest.class,
        FrameExercisesTest.class,
        FrameFaqTest.class,
        FramesExampleTest.class,

        IFrameExercisesAnswersTest.class,
        IFrameExercisesTest.class,
        IFrameFaqTest.class,
        IFramesExampleTest.class,

        // Manipulate select Support
        SelectSupportTest.class,

        // manipulate windows
        ManageWindowsExampleTest.class,
        ManageWindowsExerciseAnswerTest.class,
        ManageWindowsExerciseTest.class,
        WindowsExampleTest.class,
        WindowsExerciseAnswersTest.class,
        WindowsExerciseTest.class,
        WindowsFAQTest.class,

        // Manipulate
        ManipulateExampleSelectDropDownFiveTest.class,
        ManipulateExampleTest.class,
        ManipulateExercisesCheckboxTest.class,
        ManipulateExercisesCommentsTest.class,
        ManipulateExercisesSelectRadio2Test.class,
        ManipulateExercisesSubmitFileTest.class,
        ManipulateExercisesSubmitFormTest.class,
        ManipulatetExampleMultiSelectTest.class,
        ManipulateWhatHappensIfTest.class,
        ManipulationFirstTryExampleTest.class,
        SendKeysExamplesTest.class,

        // Navigation
        NavigationBasicsTest.class,
        NavigationExampleTest.class,

        // cookies
        CookiesExampleTest.class,
        CookiesExercisesTest.class,
        CookiesExercisesTestWorkWithExtraSync.class,

        // data driven tests
        BasicDataDrivenTest.class,
        CsvDataDrivenTest.class,

        // drivermanger in DriverSanityCoursePack
        // These are driver specific tests so require different setup
        // DriverManagerTest.class,

        // also the drivers package have unique driver tests
        // /onpath/ChromeDriverPathTest.class,
        // ChromeDriverHeadlessTest.class.
        // ChromeDriverTest.class,
        // FirefoxDriverTest.class,
        // HtmlUnitDriverTest.class,
        // MicrosoftEdgeDriverTest.class,

        // Javascript

        JavascriptAsyncExecutorTest.class,
        JavascriptExecuteAsyncExerciseTest.class,
        JavascriptExecutorExampleTest.class,
        JavaScriptExecutorTest.class,

        // mobile in its own suite

        // Page Objects
        com.seleniumsimplified.webdriver.pageobjects.loadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOneExercise.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.slowloadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.usingpagefactory.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.WithoutPageObjectsTest.class,

        // remote in its own suite

        // screenshots in DriverSanityCoursePack
        ScreenshotCaptureNotWrittenTest.class,
        // all the other screenshot tests write files hence not for CI

        //Synchronisation  conditions
        CustomExpectedConditionsExampleTest.class,
        InlineExpectedConditionExampleTest.class,
        WaitingExercisesTest.class,
        WaitingExercisesUsingPredicateTest.class,
        WebDriverWaitFunctionAndExpectedConditionsExampleTest.class,

        //Synchronisation  fluently
        UseWebDriverWaitFluentlyTest.class,

        //Synchronisation  fluentWiat
        FluentWaitExampleTest.class,
        FluentWaitExercisesTest.class,
        FluentWaitForWebElementExampleTest.class,

        //Synchronisation  implicitWait
        ImplicitWaitTest.class,

        //Synchronisation  refactored
        MakeYourWaitsReadableTest.class,

        //Synchronisation  webDriverWaitBasics
        MyFailingWebDriverWaitTest.class,
        SynchronisationWithWebDriverWaitTest.class,
        WebDriverWaitExampleTest.class,
        WebDriverWaitExampleTestRefactored.class,
        WebDriverWaitFaqTest.class,

        //userinteractions
        UserInteractionsExercisesTest.class,
        UserInteractionsCanvasExercisesTest.class,
        // this test is filled with experiments
        //UserInteractionsExamplesClickDONOTUSETest.class,
})
public class AllBrowserSuite {
}

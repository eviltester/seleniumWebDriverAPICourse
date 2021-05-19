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
import com.seleniumsimplified.webdriver.basics.manipulate.frames.*;
import com.seleniumsimplified.webdriver.basics.manipulate.iframes.*;
import com.seleniumsimplified.webdriver.basics.manipulate.windows.*;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationBasicsTest;
import com.seleniumsimplified.webdriver.basics.navigation.NavigationExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExampleTest;
import com.seleniumsimplified.webdriver.cookies.CookiesExercisesTestWorkWithExtraSync;
import com.seleniumsimplified.webdriver.datadriven.BasicDataDrivenTest;
import com.seleniumsimplified.webdriver.datadriven.CsvDataDrivenTest;
import com.seleniumsimplified.webdriver.javascript.JavaScriptExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecuteAsyncExerciseTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptExecutorExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.conditions.*;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitExercisesTest;
import com.seleniumsimplified.webdriver.synchronisation.fluentWait.FluentWaitForWebElementExampleTest;
import com.seleniumsimplified.webdriver.synchronisation.fluently.UseWebDriverWaitFluentlyTest;
import com.seleniumsimplified.webdriver.synchronisation.implicitWait.ImplicitWaitTest;
import com.seleniumsimplified.webdriver.synchronisation.refactored.MakeYourWaitsReadableTest;
import com.seleniumsimplified.webdriver.synchronisation.webDriverWaitBasics.*;
import com.seleniumsimplified.webdriver.userinteractions.UserInteractionsExercisesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
Tests which should run, and pass on edge

Edge is still a work in progress

mvn test -Dtest=EdgeSuite -Dselenium2basics.webdriver=EDGE

last update:

* 20160728 checked this suite against : WebDriver 2.53.1 && EdgeDriver v_10586 && Edge [Edge 25.10586 EdgeHtml 13.10586]
   * Alerts do not work and halt the entire suite so alert tests have been removed
   * removed a bunch of other tests pending investigation - will add to a FailOnEdgeSuite to allow monitoring
*/
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit
        JUnitBeforeAndAfterTest.class,
        JUnitExampleTest.class,
        JUnitExercisesTest.class,

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy

        // Fails because of getText() issue -
        // AFirstFindByExampleTest.class,
        ChainingFindByExampleTest.class,
        FindByCSSSelectorBasicExercisesFullAnswersTest.class,
        FindByCSSSelectorBasicExercisesTest.class,
        // Fails because of getText() issue -
        // FindByCssSelectorExampleTest.class,
        FindByCSSSelectorPathsExampleTest.class,
        // Fails because of getText() issue -
        // FindByIDOrNameExampleTest.class,
        FindByXpathExampleTest.class,
        FindByXPathSelectorBasicExercisesFullAnswersTest.class,
        FindElementsExampleTest.class,
        FindElementsExercisesTest.class,
        // Fails because of getText() issue, and other reasons -
        //FirstFindByExercisesTest.class,


        // Interrogate
        DriverInterrogateRefactoredTest.class,
        DriverInterrogateTest.class,
        GetTitleReplacementExerciseTest.class,
        WebElementInterrogationTest.class,

        // manipulate alerts
        // Fails because alerts not supported yet
        //AlertHandlingExampleTest.class,
        //AlertHandlingExercisesTest.class,

        // Manipulate Frames
        FrameFaqTest.class,
        FramesExampleTest.class,
        FrameExercisesTest.class,
        FrameExercisesAnswersTest.class,

        IFrameFaqTest.class,
        IFramesExampleTest.class,
        IFrameExercisesTest.class,
        IFrameExercisesAnswersTest.class,

        // Manipulate select Support
        // Fails haven't investigate why
        //SelectSupportTest.class,

        // manipulate windows
        ManageWindowsExerciseTest.class,
        ManageWindowsExampleTest.class,

        // Manipulate
        ManipulateExampleSelectDropDownFiveTest.class,
        ManipulateExampleTest.class,
        ManipulateExercisesCheckboxTest.class,
        ManipulateExercisesCommentsTest.class,
        ManipulateExercisesSelectRadio2Test.class,
        // Fails on Edge - haven't investigated why
        // ManipulateExercisesSubmitFileTest.class,
        ManipulateExercisesSubmitFormTest.class,
        // Fails on Edge - haven't investigated why
        // ManipulatetExampleMultiSelectTest.class,
        ManipulateWhatHappensIfTest.class,
        ManipulationFirstTryExampleTest.class,
        SendKeysExamplesTest.class,

        // Navigation
        NavigationBasicsTest.class,
        NavigationExampleTest.class,

        // cookies
        CookiesExampleTest.class,
        // Fails on Edge - haven't investigated why
        // CookiesExercisesTest.class,
        CookiesExercisesTestWorkWithExtraSync.class,

        // data driven tests
        BasicDataDrivenTest.class,
        CsvDataDrivenTest.class,

        // drivermanger in DriverSanityCoursePack

        // Javascript

        // Fails on Edge - haven't investigated why
        //JavascriptAsyncExecutorTest.class,
        JavascriptExecuteAsyncExerciseTest.class,
        JavascriptExecutorExampleTest.class,
        JavaScriptExecutorTest.class,

        // mobile in its own suite

        // Page Objects
        // Fails on Edge - haven't investigated why
        com.seleniumsimplified.webdriver.pageobjects.loadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOne.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.refactorExampleOneExercise.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.slowloadablecomponent.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.usingpagefactory.BasicTestsRefactored.class,
        com.seleniumsimplified.webdriver.pageobjects.WithoutPageObjectsTest.class,

        // remote in its own suite

        // screenshots in DriverSanityCoursePack

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
})
public class EdgeSuite {
}

package com.seleniumsimplified.junit.suites.runmanually;

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
import com.seleniumsimplified.webdriver.javascript.JavaScriptExecutorTest;
import com.seleniumsimplified.webdriver.javascript.JavascriptAsyncExecutorTest;
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
import org.htmlunit.corejs.javascript.tools.shell.Environment;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Tests which run, on HTMLUnit

 mvn test -Dtest=HTMLUnitSuite -Dselenium2basics.webdriver=HTMLUNIT

 * 20160728 Status - WebDriver 2.53.1 && HtmlUnit v 2.21
 */
@Ignore
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {

        //junit
        JUnitBeforeAndAfterTest.class,
        JUnitExampleTest.class,
        JUnitExercisesTest.class,

        // Basics Driver all use Firefox - see DriverSanityCoursePackSuite

        // Interrogate FindBy
        // Failed in v2.21 for getText
        //AFirstFindByExampleTest.class,
        ChainingFindByExampleTest.class,
        FindByCSSSelectorBasicExercisesFullAnswersTest.class,
        FindByCSSSelectorBasicExercisesTest.class,
        // Failed in v2.21 for getText
        //FindByCssSelectorExampleTest.class,
        FindByCSSSelectorPathsExampleTest.class,
        // Failed in v2.21 for getText
        //FindByIDOrNameExampleTest.class,
        //FindByXpathExampleTest.class,
        FindByXPathSelectorBasicExercisesFullAnswersTest.class,
        FindElementsExampleTest.class,
        // Failed in v2.21 for getText
        //FindElementsExercisesTest.class,
        //FirstFindByExercisesTest.class,


        // Interrogate
        DriverInterrogateRefactoredTest.class,
        DriverInterrogateTest.class,
        GetTitleReplacementExerciseTest.class,
        WebElementInterrogationTest.class,

        // manipulate alerts
        AlertHandlingExampleTest.class,
        // Failed in v2.21
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
        SelectSupportTest.class,

        // manipulate windows
        // Failed in v2.21
        // WindowManageExerciseTest.class,
        ManageWindowsExampleTest.class,
        ManageWindowsExerciseTest.class,
        WindowsExampleTest.class,
        WindowsExerciseAnswersTest.class,

        // Manipulate
        // Failed in v2.21
        // ManipulateExampleSelectDropDownFiveTest.class,
        ManipulateExampleTest.class,
        ManipulateExercisesCheckboxTest.class,
        ManipulateExercisesCommentsTest.class,
        ManipulateExercisesSelectRadio2Test.class,

        // Failed in v 2.23
        //ManipulateExercisesSubmitFileTest.class,

        ManipulateExercisesSubmitFormTest.class,
        ManipulatetExampleMultiSelectTest.class,
        ManipulateWhatHappensIfTest.class,
        ManipulationFirstTryExampleTest.class,
        // Failed in v2.21
        // SendKeysExamplesTest.class,

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

        //Synchronisation  conditions
        CustomExpectedConditionsExampleTest.class,
        InlineExpectedConditionExampleTest.class,
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
        // Failed in v2.21
        // UserInteractionsExercisesTest.class,
})
public class HTMLUnitSuite {
}

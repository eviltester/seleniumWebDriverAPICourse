package com.seleniumsimplified.junit.suites;

import com.seleniumsimplified.webdriver.basics.interrogate.WebElementInterrogationTest;
import com.seleniumsimplified.webdriver.basics.interrogate.findby.*;
import com.seleniumsimplified.webdriver.basics.manipulate.ManipulateExampleTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A simple suite that contains a cut down version of test pack
 * as an example of how to collate specific Test Classes into
 * a Suite. But also these are tests that should run headless
 * and cross browser to use as CI examples.
 *
 * Can run the suite with
 *
 * This replaces the repos:
 *     - https://github.com/eviltester/wdci
 *     - https://github.com/eviltester/alsdemowdci
 *     - https://github.com/eviltester/wdcibasic
 *
 * mvn clean -Dtest=SimpleCi test
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AFirstFindByExampleTest.class,
        ChainingFindByExampleTest.class,
        FindByCssSelectorExampleTest.class,
        FindByIDOrNameExampleTest.class,
        FindByXpathExampleTest.class,
        FindElementsExampleTest.class,
        WebElementInterrogationTest.class,
        ManipulateExampleTest.class
})
public class SimpleCi {
}


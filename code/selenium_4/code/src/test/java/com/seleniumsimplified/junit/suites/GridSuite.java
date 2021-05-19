package com.seleniumsimplified.junit.suites;

import com.seleniumsimplified.webdriver.remote.GridTest;
import com.seleniumsimplified.webdriver.remote.SaucelabsTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Used to copy and paste to create a new suite!
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        GridTest.class,
        SaucelabsTest.class
})
public class GridSuite {
}

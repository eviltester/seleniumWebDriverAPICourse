package com.seleniumsimplified.webdriver.basics.manipulate.windows;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsExerciseTest {

    String pageUrl = "https://testpages.herokuapp.com/styled/windows-test.html";
    WebDriver driver;

    @BeforeClass
    public static void quitToRestart(){
        // I need to make sure that only one window open so, if using Driver Manager...
        Driver.quit();
    }

    @Before
    public void startDriver(){
        //driver = new ChromeDriver();
        //driver.get(pageUrl);
        driver = Driver.get(pageUrl);
    }


    /*
        Exercise:

        - visit the windows index page 'styled/windows-test.html'
        - click on each of the links to open all 4 of the pages
        - you can switch to three of the windows by name do this, and check that the titles match

        |  name | title |
        |----|-----|
        | windowsindex | Windows Example Test |
        | basicajax | Test Page For Basic Ajax Example |
        | alerts | Test Page For JavaScript Alerts |

        - the Attribute page does not have a name, iterate over the window handles until you find it

     */

    @Test
    public void windowSwitching(){

        // need to keep switching back to main index page
        // in order to click


        // check titles listed in table


        // look at the attributes page to find its title
        // Test Page For Element Attributes



        // assert on that title

    }

    @After
    public void closeDriver(){

        //driver.quit();
        Driver.quit();
    }
}

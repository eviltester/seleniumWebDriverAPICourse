package com.seleniumsimplified.webdriver.screenshots;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**

 This is a simple example of how to test if a browser can take screenshots.

 Early versions of WebDriver supported use of Has capabilities

 ~~~~~~~~
   if(((HasCapabilities)driver).getCapabilities().is(CapabilityType.TAKES_SCREENSHOT)){
       System.out.println("yes it can take a screenshot");
   }
 ~~~~~~~~

 But the capabilities no longer returns the TAKES_SCREENSHOT entry so we can't use this.

 We can take advantage of the ClassCastException to check if we can take screenshots.

 */
public class CanBrowser {

    public static boolean takeScreenshots(final WebDriver driver) {
        try {
            TakesScreenshot snapper = (TakesScreenshot) driver;
        }catch(ClassCastException e){
            // no it cannot
            return false;
        }
        return true;
    }
}

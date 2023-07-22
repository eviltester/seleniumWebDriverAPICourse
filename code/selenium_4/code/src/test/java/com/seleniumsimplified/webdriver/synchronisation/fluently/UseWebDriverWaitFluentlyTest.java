package com.seleniumsimplified.webdriver.synchronisation.fluently;

import com.seleniumsimplified.webdriver.manager.Driver;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UseWebDriverWaitFluentlyTest {

    @Test
    public void wait5Seconds(){

        WebDriver driver = Driver.get("https://testpages.herokuapp.com/");

        long currTime = System.currentTimeMillis();

        try{

            // prior to version 3.12 we use TimeUnit
            // after 3.12 we use Duration
            // so this was...
//            new WebDriverWait(driver, 1).
//                    pollingEvery(100, TimeUnit.MILLISECONDS).
//                    withTimeout(5, TimeUnit.SECONDS).

            /* this will ignore the thrown exception in the apply */
            new WebDriverWait(driver, Duration.ofSeconds(1)).
                    pollingEvery(Duration.ofMillis(100)).
                    withTimeout(Duration.ofSeconds(5)).
                    ignoring(IllegalStateException.class).

                    withMessage("See I told you a Timeout Happened").until(
                        new ExpectedCondition<Boolean>() {
                            @Override
                            public Boolean apply(WebDriver webDriver) {
                                throw new IllegalStateException();
                            }
                        }
                    );

            fail("A time out exception should have been thrown");

        }catch(TimeoutException e){
            assertTrue(e.getMessage().contains("See I told you a Timeout Happened"));
        }

        long nowTime = System.currentTimeMillis();
        System.out.println("Timeout calculated as " + (nowTime - currTime));

        assertTrue((nowTime - currTime) >= 5000);

    }

}

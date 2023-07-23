package com.seleniumsimplified.webdriver.synchronisation.fluentWait;

import com.google.common.base.Function;
import com.seleniumsimplified.webdriver.manager.Driver;
import com.seleniumsimplified.webdriver.siteabstractions.SiteUrls;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class FluentWaitExampleTest {

    /**
     * FluentWait gives more granular control and can adjust params on the fly
     * so reuse the wait but next time with a different timeout or a different
     * polling interval, or add additional exceptions to ignore
     */
    @Test
    public void exampleUsingExpectedConditions(){

        WebDriver driver;

        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());

        // Fluent wait constructor has changed for version 3.12
        // we used to use
        //        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
        //                                withTimeout(10, TimeUnit.SECONDS).
        //                                pollingEvery(100, TimeUnit.MILLISECONDS).
        //                                ignoring(NotFoundException.class);
        // now we use Duration.ofSeconds or Duration.ofMillis

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofMillis(100)).
                ignoring(NotFoundException.class);

        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    // this is equivalent to the above
    // example only
    @Test
    public void exampleUsingExpectedConditionsMix(){

        WebDriver driver;

        driver = Driver.get(SiteUrls.basicHtmlFormPageUrl());

        // Fluent wait constructor has changed for version 3.12
        // we used to use
//        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,10).
//                                 pollingEvery(100, TimeUnit.MILLISECONDS);

        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver,Duration.ofSeconds(10)).
                pollingEvery(Duration.ofMillis(100));


        // the following line is not needed because a WebDriverWait
        // add the NotFoundException to the ignored list
        // wait.ignoring(NotFoundException.class);

        wait.until(ExpectedConditions.titleIs("HTML Form Elements"));

        assertEquals("HTML Form Elements", driver.getTitle());
    }

    /**
     * Fluent wait does not need webdriver, this test waits
     * for a minimum of 4 seconds using FluentWait
     */
    @Test
    public void fluentWaitDoesNotNeedWebDriver(){

        Long startTime = System.currentTimeMillis();

        // Fluent wait constructor has changed for version 3.12
        // we used to use
//        FluentWait<Long> wait = new FluentWait<Long>(startTime).
//                                    withTimeout(7, TimeUnit.SECONDS).
//                                    pollingEvery(50, TimeUnit.MILLISECONDS);
        // now we use
        FluentWait<Long> wait = new FluentWait<Long>(startTime).
                                      withTimeout(Duration.ofSeconds(7)).
                                      pollingEvery(Duration.ofMillis(50));

        Long endTime = wait.until(new Function<Long,Long>() {
                            @Override
                            public Long apply(Long startTime) {
                                Long currTime = System.currentTimeMillis();

                                if(currTime > (startTime + 4000))
                                    return currTime;

                                return null;
                            }
                        });

        System.out.println("Actual Time difference = " + (endTime- startTime) + " milliseconds");
    }
}

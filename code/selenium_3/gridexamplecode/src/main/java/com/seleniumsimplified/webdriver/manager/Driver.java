package com.seleniumsimplified.webdriver.manager;

import com.seleniumsimplified.environmentmanager.Props;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    public static final long DEFAULT_TIMEOUT_SECONDS = 10;
    static WebDriver driver;

    static String driverName = "CHROME";

    public static WebDriver get() {

        final String overrideBrowser = Props.getEnvOrProperty("selenium2basics.webdriver");
        if(overrideBrowser.length()>0){
            driverName = overrideBrowser;
        }

        if(driver==null){
            switch (driverName){
                case "CHROME":
                    driver = new ChromeDriver();
                    break;
                case "FIREFOX":
                    driver = new FirefoxDriver();
                    break;
                case "GRID":
                    driver = connectToGrid();
                    break;
                default:
                    driver = new ChromeDriver();
                    break;
            }
        }
        return driver;
    }

    public static WebDriver connectToGrid(){

        // configure the default grid here
        MutableCapabilities capabilities = DesiredCapabilities.chrome();
        String gridUrl = "http://localhost:4444/wd/hub";

        try {
            // add url to environment variables to avoid releasing with source
            driver = new RemoteWebDriver(
                    new URL(gridUrl),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    public static WebDriver get(String url){
        WebDriver mydriver = get();
        mydriver.get(url);
        return mydriver;
    }

    public static void close(){
        try{
            driver.close();
            driver.quit();
        }catch(Exception e){

        }
        driver=null;
    }
}

package com.seleniumsimplified.webdriver.manager;

/**
 * Created by Alan on 25/07/2016.
 */
public class EnvironmentPropertyReader {

    /**
     * Allow setting the controls via property or environment variable
     * property takes precedence, then environment variable, then default
     */
    public static String getPropertyOrEnv(String name, String theDefault){

        String theValue = System.getProperty(name);
        if(theValue == null){

            // this message just confuses people
            //System.out.println("Could not find Property " + name);
            theValue = System.getenv(name);

            if(theValue==null){

                System.out.println("No Environment Variable or Property named [" + name + "] using default value [" + theDefault + "]");
                theValue = theDefault;

            }else{
                System.out.println("Using Environment Variable " + name + " with value " + theValue);
            }
        }else{
            System.out.println("Using Property " + name + " with value " + theValue);
        }

        return theValue;
    }
}

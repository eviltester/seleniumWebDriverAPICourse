package com.seleniumsimplified.environmentmanager;

public class Props {
    public static String getEnvOrProperty(final String name) {
        String env = System.getenv(name);
        String property = System.getProperty(name);

        if(env==null){
            env="";
        }

        if(property==null){
            property="";
        }

        // property e.g. -Dproperty.name=VALUE takes precendence
        if(property.length()>0){
            return property;
        }

        return env;
    }
}

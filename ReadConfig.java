package com.example;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    private static final String myEmailAccount = getPropertyParam("myEmailAccount"), myEmailPassword = getPropertyParam("myEmailPassword"), myEmailSMTPHost = getPropertyParam("myEmailSMTPHost");

    public static Properties getConfig() {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

    private static String getPropertyParam(String key) {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream("src/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }

}

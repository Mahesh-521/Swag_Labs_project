package com.swaglabs.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/TestData/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration file not found.");
        }
    }

    public String getBaseURL() {
        return prop.getProperty("baseURL");
    }

   
}

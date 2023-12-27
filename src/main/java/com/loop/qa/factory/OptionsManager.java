package com.loop.qa.factory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;
public class OptionsManager {

    private final Properties prop;

    private ChromeOptions co;

    private FirefoxOptions fo;
    public OptionsManager(Properties prop) {
        this.prop=prop;
    }
    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }
        co.addArguments("--log-level=3");
        return co;
    }

    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if(Boolean.parseBoolean(prop.getProperty("headless"))) {
            fo.addArguments("--headless");
        }
        if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }
        if(Boolean.parseBoolean(prop.getProperty("remote"))) {
            fo.setCapability("browserName", "firefox");
            fo.setBrowserVersion(prop.getProperty("browserversion").trim());

            //fo.setCapability("enableVNC", true);
        }
        return fo;
    }


}

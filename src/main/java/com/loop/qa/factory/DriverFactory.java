package com.loop.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    Properties prop;

    public static String highlightElement;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    WebDriver driver;

    OptionsManager optionsManager;

    public synchronized static WebDriver getDriver(){ return tlDriver.get(); }

/**
 * take screenshot
 */

public static String getScreenshot() {
    if (getDriver() != null) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtil.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    } else {
        System.out.println("No driver found");
        return "No driver found";
    }
}
public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");

        // String browserName = System.getProperty("browser");
        System.out.println("browser name is : " + browserName);

        optionsManager = new OptionsManager(prop);

        switch (browserName.toLowerCase()) {

            case "chrome":
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                break;

            case "firefox":
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;

            default:
                System.out.println("Please pass the right browser...." + browserName);
                break;
        }

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public Properties initProp() {

        // mvn clean install -Denv="qa"
        FileInputStream ip = null;
        prop = new Properties();

        String envName = System.getProperty("env");
        System.out.println("env name is : " + envName);

        try {
            if (envName == null) {
                System.out.println("no env is given...hence running it on config.properties env..by default");
                ip = new FileInputStream("./src/main/resources/config/config.properties");
            } else {

                switch (envName.toLowerCase().trim()) {
                    case "dev":
                        ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
                        break;
                    case "beta":
                        ip = new FileInputStream("./src/main/resources/config/beta.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/main/resources/config/config.properties");
                        break;

                    default:
                        System.out.println("please pass the right env name...." + envName);
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}



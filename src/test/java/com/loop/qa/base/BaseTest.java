package com.loop.qa.base;

import com.loop.qa.factory.DriverFactory;
import com.loop.qa.pages.HrdLogin;
import com.loop.qa.pages.LoginPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

    protected Properties prop;
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected LoginPage loginPage;
    protected HrdLogin hrdLogin;
    protected DriverFactory df;
    protected SoftAssert softAssert;
    WebDriver driver;
    String browserName = "chrome";


    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browserName) {
        df = new DriverFactory();
        prop = df.initProp();
        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }
        driver = df.initDriver(prop);

        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    public BaseTest() {
        loadConfigProperties();
    }

    private void loadConfigProperties() {
        try {
            prop = new Properties();
            String env = System.getProperty("env");
            if (env == null) {
                env = System.getenv("env");
            }
            if (env == null) {
                throw new RuntimeException("Please specify the environment using the 'env' system property or environment variable");
            }
            FileInputStream ip = new FileInputStream("./src/main/resources/config/" + env + ".config.properties");
            prop.load(ip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

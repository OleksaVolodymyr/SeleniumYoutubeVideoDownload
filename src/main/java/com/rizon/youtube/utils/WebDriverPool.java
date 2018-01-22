package com.rizon.youtube.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebDriverPool {
    private static Logger LOG = Logger.getLogger(WebDriverPool.class);
    private static ThreadLocal<WebDriver> pool = new ThreadLocal<>();
    private static List<WebDriver> driversInThread = Collections.synchronizedList(new ArrayList<>());

    public static synchronized WebDriver getInstance() {
        if (pool.get() == null) {
            LOG.info("Create web driver, thread id : " + Thread.currentThread().getId());
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            System.setProperty("webdriver.chrome.args", "--disable-logging");
           // System.setProperty("webdriver.chrome.silentOutput", "true");
            WebDriver driver;
            LoggingPreferences loggingPreferences = new LoggingPreferences();
            loggingPreferences.enable(LogType.DRIVER, Level.OFF);
            loggingPreferences.enable(LogType.CLIENT, Level.OFF);
            loggingPreferences.enable(LogType.BROWSER, Level.OFF);
            loggingPreferences.enable(LogType.PERFORMANCE, Level.OFF);
            loggingPreferences.enable(LogType.SERVER, Level.OFF);
            DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
            desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(desiredCapabilities);
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            driver.navigate().to("https://www.youtube.com");
            driversInThread.add(driver);
            pool.set(driver);
        }
        return pool.get();
    }

    private static WebDriver getDriver() {
        return getInstance();
    }

    public static void quit() {
//        if (getDriver() != null) {
//            getDriver().quit();
//        }
        for (WebDriver driver : driversInThread) {
            driver.quit();
        }

    }

}

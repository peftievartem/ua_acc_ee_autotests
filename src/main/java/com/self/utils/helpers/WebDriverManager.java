package com.self.utils.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;

public class WebDriverManager {
    private static WebDriver driver;
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverManager.class);
    public WebDriver setupDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + FileReaderManager.getInstance().getConfigReader().getDriverPath() + File.separator + "chromedriver");
        // Comment next 4 lines to have visible test run
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
//        driver = new ChromeDriver(options);
        driver = new ChromeDriver();
        if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) {
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebDriver getDriver() {
        LOG.info("Getting WebDriver instance.");
        return driver;
    }

}

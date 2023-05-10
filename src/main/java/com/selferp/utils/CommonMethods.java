package com.selferp.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.selferp.utils.Constants.*;

public class CommonMethods {

    static final int EXPLICIT_WAIT_SEC = EXPLICIT_WAIT_TIMEOUT;

    private void waitAndClickElement(long timeOutInSeconds, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(World.driver, Duration.ofSeconds(timeOutInSeconds));
            this.waitUntilElementIsDisappeared(RETRY_TIMEOUT, element);
            wait.until(ExpectedConditions.visibilityOf(element));
            element.click();
        } catch (Exception ignored) {
        }
    }

    public void waitAndClickElement(WebElement element) {
        this.waitAndClickElement(EXPLICIT_WAIT_SEC, element);
    }

    private void waitAndSendText(long timeOutInSeconds, WebElement element, String text) {
        this.waitAndClickElement(timeOutInSeconds, element);
        try {
            this.waitAndClickElement(timeOutInSeconds, element);
            element.clear();
            this.waitAndClickElement(timeOutInSeconds, element);
            element.sendKeys(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void waitAndSendText(WebElement element, String text) {
        this.waitAndSendText(EXPLICIT_WAIT_SEC, element, text);
    }

    private void waitAndSelectTextFromList(long timeOutInSeconds, WebElement inputField, WebElement squareButton, String text) {
        this.waitAndClickElement(timeOutInSeconds, inputField);
        try {
            inputField.clear();
            inputField.sendKeys(text);
            if (squareButton != null) {
                this.waitUntilElementIsVisible(RETRY_TIMEOUT, squareButton);
                inputField.sendKeys(Keys.chord(Keys.ENTER));
                this.waitUntilElementIsVisible(squareButton);
            } else {
                this.waitUntilElementIsDisappeared(RETRY_TIMEOUT, inputField);
                inputField.sendKeys(Keys.chord(Keys.ENTER));
                this.waitUntilElementIsDisappeared(RETRY_TIMEOUT, inputField);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void waitAndSelectTextFromList(WebElement inputField, WebElement squareButton, String text) {
        this.waitAndSelectTextFromList(EXPLICIT_WAIT_SEC, inputField, squareButton, text);
    }

    private void waitUntilElementIsVisible(long timeOutInSeconds, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(World.driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception ignored) {
        }
    }

    public void waitUntilElementIsVisible(WebElement element) {
        this.waitUntilElementIsVisible(EXPLICIT_WAIT_SEC, element);
    }

    private void waitUntilElementIsDisappeared(long timeOutInSeconds, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(World.driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception ignored) {
        }
    }

    public void waitUntilElementIsDisappeared(WebElement element) {
        this.waitUntilElementIsDisappeared(EXPLICIT_WAIT_SEC, element);
    }

    public boolean isPresent(WebElement element) {
        boolean value;
        WebDriverWait wait = new WebDriverWait(World.driver, Duration.ofSeconds(EXPLICIT_WAIT_SEC));
        wait.ignoring(TimeoutException.class, NoSuchElementException.class);
        value = wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        return value;
    }

    private void waitForDocumentReady() {
        JavascriptExecutor executor = (JavascriptExecutor) World.driver;
        FluentWait<WebDriver> wait = new WebDriverWait(World.driver, Duration.ofSeconds(AJAX_TIMEOUT));
        wait.until(driver -> executor.executeScript("return document.readyState == 'complete'").equals(true));
    }

    private void waitForAjaxRequestsComplete() {
        JavascriptExecutor executor = (JavascriptExecutor) World.driver;
        FluentWait<WebDriver> wait = new WebDriverWait(World.driver, Duration.ofSeconds(AJAX_TIMEOUT));
        wait.until(driver -> executor.executeScript("return typeof(jQuery) == 'function' ? (jQuery.active == 0) : true").equals(true));
    }

    public void waitForPageToLoad() {
        this.waitForDocumentReady();
        this.waitForAjaxRequestsComplete();
    }
}

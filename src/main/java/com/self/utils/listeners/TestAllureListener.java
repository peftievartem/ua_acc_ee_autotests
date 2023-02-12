package com.self.utils.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import com.self.utils.helpers.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static com.self.utils.World.driver;

public class TestAllureListener implements ITestListener, TestLifecycleListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }


    // Text attachments for Allure
    @Attachment (value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver). getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment (value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Step("Screenshot")
    private static void takeScreenshot(WebDriver driver) {
        Allure.addAttachment("Screenshot of failed step", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus().name().equalsIgnoreCase("failed")) {
            takeScreenshot(WebDriverManager.getDriver());
        }
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("I am in onFinish method " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");
    }

//    @Override
//    public void onTestFailure(ITestResult iTestResult) {
//        System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
//        if (driver != null) {
//            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
//            saveFailureScreenShot(driver);
//            System.out.println("Screenshot saved");
//            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
//            Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) iTestResult.getTestContext().getAttribute("WebDriver")).getScreenshotAs(OutputType.BYTES)));
//            System.out.println("Screenshot saved");
//            Object webDriverAttribute = iTestResult.getTestContext().getAttribute("WebDriver");
//            saveFailureScreenShot((WebDriver) webDriverAttribute);
//            System.out.println("Screenshot saved #2");
//        }
//        saveTextLog(iTestResult.getMethod().getConstructorOrMethod().getName());
//        takeScreenshot(WebDriverManager.getDriver());
//    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System. out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
        Object testClass = iTestResult.getInstance();
        WebDriver driver = WebDriverManager.getDriver();
        // Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }
        // Save a log on allure.
        saveTextLog(getTestMethodName(iTestResult) + " failed and screen$hot taken!");
        Allure.addAttachment("Screenshot of failed step", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }
}

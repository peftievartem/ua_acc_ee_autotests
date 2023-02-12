package com.self.utils.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import com.self.utils.helpers.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.self.utils.World.driver;

public class TestResultListener extends TestListenerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(TestResultListener.class);

    /**
     * Prints the test results to report.
     *
     * @param result the result
     */
    private void printTestResults(ITestResult result) {
        if (result.getParameters().length != 0) {
            String params = null;
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            LOG.info("Output params: " + params);
        }

        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "Pass";
                break;
            case ITestResult.FAILURE:
                status = "Failed";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
                break;
        }

        LOG.info("Test status is: " + status);
    }

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", driver);
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("I am in onTestSkipped method " + getTestMethodName(arg0) + " skipped");
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("I am in onTestSuccess method " + getTestMethodName(arg0) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        System. out.println("I am in onTestFailure method " + getTestMethodName(arg0) + " failed");
        LOG.info("Taking screenshot for failed test");
        testScreenshotOnFinish();
    }

    @Step("Screenshot on failed test")
    public void testScreenshotOnFinish() {
        Allure.addAttachment("Screenshot after test", "image/png", getScreenshotByteArray(), "png");
    }

    public ByteArrayInputStream getScreenshotByteArray() {
        AShot ashot = new AShot().coordsProvider(new WebDriverCoordsProvider());
        Screenshot screenImage = ashot.takeScreenshot(WebDriverManager.getDriver());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(screenImage.getImage(), "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] screeshotByteArray = baos.toByteArray();

        return new ByteArrayInputStream(screeshotByteArray);
    }
}
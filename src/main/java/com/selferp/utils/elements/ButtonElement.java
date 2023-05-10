package com.selferp.utils.elements;

import io.qameta.allure.Step;
import com.selferp.utils.CommonMethods;

public class ButtonElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseButtonPath = "//button[contains(text(),'%s') and not (contains(@class,'o_invisible'))]";

    private static final String baseButtonClassPath = "//button[(contains(@class,'%s')) and not (contains(@class,'o_invisible'))]";
    private static final String buttonWithTypeAttributePath = "//button[@type='%s']";

    @Step("Click on button")
    public static void clickOnButton(String buttonName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseButtonPath, buttonName)));
        commonMethods.waitForPageToLoad();
    }

    @Step("Click on button")
    public static void clickOnButtonByClass(String buttonName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseButtonClassPath, buttonName)));
        commonMethods.waitForPageToLoad();
    }

    @Step("Click on button")
    public static void clickOnButtonXpath(String attribute) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", attribute));
        commonMethods.waitForPageToLoad();
    }

    @Step("Click on button with attribute")
    public static void clickOnButtonWithTypeAttribute(String attributeName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(buttonWithTypeAttributePath, attributeName)));
    }
}

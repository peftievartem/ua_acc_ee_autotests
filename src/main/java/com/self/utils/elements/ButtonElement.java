package com.self.utils.elements;

import io.qameta.allure.Step;
import com.self.utils.CommonMethods;
import org.openqa.selenium.WebElement;

public class ButtonElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseButtonPath = "//button[contains(text(),'%s') and not (contains(@class,'o_invisible'))]";

    private static final String baseButtonClassPath = "//button[(contains(@class,'%s')) and not (contains(@class,'o_invisible'))]";
    private static final String buttonWithTypeAttributePath = "//button[@type='%s']";
    private static final String modalButton = "//*[@class='modal-content']/descendant::span[text()='%s']";
    private static final String baseButtonBoxPath = "//button/descendant::span[contains(text(),'%s')]";
    private static final String baseAdvancedSearchButtonPath = ".o_searchview_more";
    private static final String baseSearchOptionsButtonPath = ".btn-group:nth-child(1) > .o_dropdown_toggler_btn";
    private static final String baseButtonListPath = "//button[contains(@class,'o_cp_switch_list')]";
    private static final String baseStatusBarButtonPath = "//*[@name='state']/button[@data-value='%s']";
    private static final String baseInputTagDeleteButtonPath = "//*[@name='%s']/descendant::span[contains(@class,'o_delete')]";

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

    public static WebElement getButtonElement(String buttonName) {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", String.format(baseButtonPath, buttonName));
    }

    public static WebElement getButtonBoxElement(String buttonName) {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", String.format(baseButtonBoxPath, buttonName));
    }

    @Step("Click on button with attribute")
    public static void clickOnButtonWithTypeAttribute(String attributeName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(buttonWithTypeAttributePath, attributeName)));
    }

    @Step("Click on modal button")
    public static void clickOnModalButton(String buttonName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(modalButton, buttonName)));
    }

    @Step("Click on button box")
    public static void clickOnButtonBox(String buttonName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseButtonBoxPath, buttonName)));
    }

    @Step("Click advance search button")
    public static void clickAdvancedSearchButton() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("CSS", baseAdvancedSearchButtonPath));
    }

    @Step("Click on search options button")
    public static void clickOnSearchOptionsButton() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("CSS", baseSearchOptionsButtonPath));
    }

    @Step("Click on button list")
    public static void clickOnButtonList() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", baseButtonListPath));
    }

    @Step("Click on delete button in tag of input field")
    public static void clickOnInputTagDeleteButton(String attributeName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseInputTagDeleteButtonPath, attributeName)));
    }

    @Step("Click on status bar button")
    public static void clickOnStatusBarButton(String buttonName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseStatusBarButtonPath, buttonName)));
    }
}

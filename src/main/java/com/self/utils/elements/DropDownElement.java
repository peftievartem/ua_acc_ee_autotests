package com.self.utils.elements;

import com.self.utils.CommonMethods;
import org.openqa.selenium.By;

public class DropDownElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseDropDownPath = "//*[@class='dropdown-toggle' and contains(text(),'%s')]";
    private static final String baseDropDownPathForApp = "//li[@class='app-name']/*[contains(text(),'%1$s')]/ancestor::ul/descendant::*[@class='dropdown-toggle' and contains(text(),'%2$s')]";
    private static final String baseDropDownValuePath = "/following-sibling::*/descendant::*[contains(text(),'%s')]";
    private static final String simpleDropDownValuePath = "//*[contains(@class,'dropdown-menu')]/descendant::*[contains(text(),'%s')]";
    private static final String selectOptionDropdownPath = "//select[@name='%s']";
    private static final String dropDownOptionPath = "/descendant::option[contains(text(),'%s')]";
    private static final String firstDropDownSelectOptionPath = "/descendant::option[@value!='false']['%s']";
    public static void selectBaseDropDownValue(String dropDownName, String dropDownValueName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseDropDownPath, dropDownName)));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseDropDownPath, dropDownName).
                        concat(String.format(baseDropDownValuePath, dropDownValueName))));
    }

    public static void selectBaseDropDownValueForApp(String appName, String dropDownName, String dropDownValueName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseDropDownPathForApp, appName, dropDownName)));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseDropDownPathForApp, appName, dropDownName).
                        concat(String.format(baseDropDownValuePath, dropDownValueName))));
    }

    public static void selectSimpleDropDownValue(String dropDownValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(simpleDropDownValuePath, dropDownValue)));
    }

    public static void dropDownSelectOption(String selectDropDownAttributeNameValue, String optionValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(selectOptionDropdownPath, selectDropDownAttributeNameValue)));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                        String.format(selectOptionDropdownPath, selectDropDownAttributeNameValue))
                .findElement(By.xpath(String.format(dropDownOptionPath, optionValue))));
    }

    public static void selectFirstDropDownSelectOption(String selectDropDownAttributeNameValue, String optionValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(selectOptionDropdownPath, selectDropDownAttributeNameValue)));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(selectOptionDropdownPath, selectDropDownAttributeNameValue)).findElement(By.xpath(String.format(firstDropDownSelectOptionPath, optionValue))));
    }
}

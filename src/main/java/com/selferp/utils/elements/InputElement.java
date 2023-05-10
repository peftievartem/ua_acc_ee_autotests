package com.selferp.utils.elements;

import com.selferp.utils.CommonMethods;

public class InputElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    public static void setInput(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath",
                attributeValue), inputValue);
    }

    public static void setInputWithNameAttribute(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Name",
                attributeValue), inputValue);
    }

    public static void setInputWithClassAttribute(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("ClassName",
                attributeValue), inputValue);
    }

    public static void setInputForDateTimePickerXpath(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath", attributeValue), inputValue);
    }

    public static void setInputDropdownWithoutButtonByXpath(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSelectTextFromList(baseElementLocator.getWebElement("Xpath", attributeValue),
                null, inputValue);
    }
}

package com.selferp.utils.elements;

import com.selferp.utils.CommonMethods;

public class RadioButtonElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();
    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseRadioButtonPath = "//div[@name='%1$s']/descendant::label[text()='%2$s']";
    private static final String baseRadioButtonDataValue = "//input[@data-value='%s']";

    public static void selectRadioButton(String radioButtonSectionName, String radioButtonName) {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(baseRadioButtonPath, radioButtonSectionName, radioButtonName)).click();
    }

    public static void selectRadioButtonByDataValue(String radioButtonDataValue) {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(baseRadioButtonDataValue, radioButtonDataValue)).click();
    }


}

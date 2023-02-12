package com.self.utils.elements;

import com.self.utils.CommonMethods;

public class RadioButtonElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();
    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseRadioButtonPath = "//div[@name='%1$s']/descendant::label[text()='%2$s']";

    public static void selectRadioButton(String radioButtonSectionName, String radioButtonName) {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(baseRadioButtonPath, radioButtonSectionName, radioButtonName)).click();
    }
}

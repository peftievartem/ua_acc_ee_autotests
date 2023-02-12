package com.self.utils.elements;

import com.self.utils.CommonMethods;

public class CheckBoxElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseCheckBoxPath = "//*[@name='%s']/child::input[@type='checkbox']";
    private static final String tableHeadCheckboxPath = "//thead/descendant::input[@type='checkbox']";

    public static void selectCheckbox(String checkboxNameAttribute) {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(baseCheckBoxPath, checkboxNameAttribute)).click();
    }

    public static void selectTableHeadCheckbox() {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", tableHeadCheckboxPath).click();
    }
}

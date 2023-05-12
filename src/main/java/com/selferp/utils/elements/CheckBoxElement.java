package com.selferp.utils.elements;

import com.selferp.utils.CommonMethods;

public class CheckBoxElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    public static boolean isCheckedById(String checkboxId) {
        commonMethods.waitForPageToLoad();
        return (baseElementLocator.getWebElement("CSS", "input#" + checkboxId + ":checked[type='checkbox']") != null);
    }

}

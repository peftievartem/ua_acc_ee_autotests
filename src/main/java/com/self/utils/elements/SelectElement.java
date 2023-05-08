package com.self.utils.elements;

import com.self.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    public static void setSimpleSelectValue(String attributeValue, String value) {
        commonMethods.waitForPageToLoad();
        Select selectElement = new Select(baseElementLocator.getWebElement("Xpath", attributeValue));
        if (!value.isEmpty()) {
            selectElement.selectByValue('"' + value + '"');
        }
    }

    public WebElement getSimpleSelectValue(String attributeValue) {
        commonMethods.waitForPageToLoad();
        Select selectElement = new Select(baseElementLocator.getWebElement("Xpath", attributeValue));
        return selectElement.getFirstSelectedOption();
    }

}

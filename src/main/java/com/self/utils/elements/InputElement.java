package com.self.utils.elements;

import com.self.utils.CommonMethods;
import org.openqa.selenium.WebElement;

public class InputElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseDateTimePickerInputPath = "//*[@name='%s']/input[@name='%s']";
    private static final String baseDropdownInputPath = "//*[@name='%s']/div/input | //*[@name='%s']/descendant::input";
    private static final String baseDropdownButtonPath = "//*[@name='%s']/button";
    private static final String baseSelectInputPath = "//select/option[contains(text(),'%s')]";
    private static final String baseInputMonetaryPath = "//div[@name='%s' and (contains(@class,'o_field_monetary'))]/input";
    private static final String dropdownActiveOptionPath = "//ul[contains(@style,'block')]/child::li[contains(@class,'dropdown_option')]/a[text()='%s']";
    private static final String modalInputWithNameAttributePath = "//*[@class='modal-content']/descendant::input[@name='%s']";
    public static void setInputWithNameAttribute(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Name",
                attributeValue), inputValue);
    }

    public static void setModalInputWithNameAttribute(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath",
                String.format(modalInputWithNameAttributePath, attributeValue)), inputValue);
    }

    public static WebElement getInputWithClassAttribute(String attributeValue) {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("ClassName", attributeValue);
    }

    public static void setInputWithClassAttribute(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("ClassName",
                attributeValue), inputValue);
    }

    public static void setInputForDateTimePicker(String attributeValue1, String attributeValue2, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath",
                String.format(baseDateTimePickerInputPath, attributeValue1, attributeValue2)), inputValue);
    }

    public static void setInputDropdown(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSelectTextFromList(baseElementLocator.getWebElement("Xpath",
                        String.format(baseDropdownInputPath,
                                attributeValue, attributeValue)),
                baseElementLocator.getWebElement("Xpath",
                        String.format(baseDropdownButtonPath,
                                attributeValue)), inputValue);
    }

    public static void selectActiveOptionInDropdown(String attributeValue, String activeOptionName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseDropdownInputPath, attributeValue, attributeValue)));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(dropdownActiveOptionPath, activeOptionName)));
    }

    public static void setInputDropdownWithoutButton(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSelectTextFromList(baseElementLocator.getWebElement("Xpath",
                        String.format(baseDropdownInputPath,
                                attributeValue, attributeValue)),
                null, inputValue);
    }

    public static void setInputSelect(String selectValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseSelectInputPath, selectValue)));
    }

    public static void setInputMonetary(String attributeValue, String inputValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(baseInputMonetaryPath, attributeValue)));
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath",
                String.format(baseInputMonetaryPath, attributeValue)), inputValue);
    }
}

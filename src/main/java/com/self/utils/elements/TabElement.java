package com.self.utils.elements;

import com.self.utils.CommonMethods;

public class TabElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseTabPath = "//a[contains(text(),'%s')]";
    private static final String mainContentTabPath = "//*[@class='o_main_content']/descendant::a[contains(text(),'%s')]";

    public static void selectTab(String tabName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(baseTabPath, tabName)));
    }

    public static void mainContentTab(String tabName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(mainContentTabPath, tabName)));
    }
}

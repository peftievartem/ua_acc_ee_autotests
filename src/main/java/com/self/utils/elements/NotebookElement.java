package com.self.utils.elements;

import com.self.utils.CommonMethods;
import io.qameta.allure.Step;

public class NotebookElement {
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String notebookPath = "//div[hasclass('o_notebook')]";
    private static final String notebookTab = "//*[@class='o_notebook_headers']/ul/li/a[contains(text(),'%s')]";

    @Step("Click on notebook tab")
    public static void selectNotebookTab(String tabName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(notebookTab, tabName)));
    }

}

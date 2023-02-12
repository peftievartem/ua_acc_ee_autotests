package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.World;
import com.self.utils.elements.InputElement;
import org.openqa.selenium.Keys;

public class EmployeePage extends CommonPage {

    @Inject
    public EmployeePage(World world) {
        super(world);
    }

    private final String baseContentTextPath = "//*[contains(@name,'full_passport_line')]";
    private final String baseActiveStateNamePath = "div.o_form_statusbar > div.o_statusbar_status > button.btn-primary";

    public String getContentText() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", baseContentTextPath).getText();
    }

    public String getActiveStateName() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("CSS", baseActiveStateNamePath).getText();
    }

    public void selectEmployeeTableCell(String cellName) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithClassAttribute(getSearchInputPath(), cellName);
        baseElementLocator.getWebElement("ClassName", getSearchInputPath()).sendKeys(Keys.chord(Keys.ENTER));
        commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath", getTableElementPath()));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", getTableElementPath()));
    }

    public void searchEmployee(String employeeName) {
        commonMethods.waitForPageToLoad();
        if (baseElementLocator.getWebElement("Xpath", getSearchViewFacetPath()).isDisplayed()) {
            commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", getSearchViewFacetCloseIconPath()));
        }
        InputElement.setInputWithClassAttribute(getSearchInputPath(), employeeName);
        baseElementLocator.getWebElement("ClassName", getSearchInputPath()).sendKeys(Keys.chord(Keys.ENTER));
    }
}

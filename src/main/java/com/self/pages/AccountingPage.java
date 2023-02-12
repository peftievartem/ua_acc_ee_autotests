package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.CommonMethods;
import com.self.utils.World;
import com.self.utils.elements.BaseElementLocator;

public class AccountingPage extends CommonPage {

    @Inject
    public AccountingPage(World world) {
        super(world);
    }

    static CommonMethods commonMethods = new CommonMethods();
    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    private static final String costAllocationTypeField = "//select[@name='cost_allocation_type']";
    private static final String costAllocationTypeValue = "/child::option[text()='%s']";

    public void selectDropDownInputValue(String dropDownValue) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", costAllocationTypeField));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(costAllocationTypeField.concat(costAllocationTypeValue), dropDownValue)));
    }
}

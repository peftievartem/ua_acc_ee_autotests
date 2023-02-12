package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.World;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PayrollPage extends CommonPage {
    @Inject
    public PayrollPage(World world) {
        super(world);
    }

    private final String modalSearchInput = "//div[@class='modal-content']//input[@class='o_searchview_input']";
    private final String paidInfoTextElement = "//i[contains(@class,'o_payment_label')]";

    public WebElement getPaidInfoTextElement() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", paidInfoTextElement);
    }

    public void searchModalTableItem(String itemName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndSendText(baseElementLocator.getWebElement("Xpath", modalSearchInput), itemName);
        baseElementLocator.getWebElement("Xpath", modalSearchInput).sendKeys(Keys.chord(Keys.ENTER));
        try {
            commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath",
                    modalSearchInput));
        } catch (Exception ignore) {
        }
    }
}

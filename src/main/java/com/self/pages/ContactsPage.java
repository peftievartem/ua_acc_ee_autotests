package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.World;

public class ContactsPage extends CommonPage {
    @Inject
    public ContactsPage(World world) {
        super(world);
    }

    private final String partnerPath = "//*[@class='oe_kanban_details']";

    public void selectPartner() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getListWebElements("Xpath", partnerPath).get(0));
    }

}

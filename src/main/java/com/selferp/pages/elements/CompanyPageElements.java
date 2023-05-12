package com.selferp.pages.elements;

import com.selferp.inject.Inject;
import com.selferp.pages.CommonPage;
import com.selferp.utils.World;
import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;
import com.selferp.utils.elements.SelectElement;

import static com.selferp.utils.Constants.RANDOM_NUM;
import static com.selferp.utils.Constants.constantNewCompany;

public class CompanyPageElements extends CommonPage {

    @Inject
    public CompanyPageElements(World world) {
        super(world);
    }
    protected CommonPage commonPage = new CommonPage(world);

    public String createNewCompany(String firstEvent) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("contacts.menu_contacts").click();
        if (commonPage.checkElementByXpath("//i[@aria-label='Remove']"))
            commonPage.clickOnLinkByXpath("//i[@aria-label='Remove']");
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        if (!firstEvent.isEmpty()) {
            if (commonPage.getElementByXpath("//*[contains(@class, 'o_kanban_record_title')]/span[contains(text(), '" + constantNewCompany + firstEvent + "_" + RANDOM_NUM + ")]") == null) {
                commonPage.clickOnLinkByXpath("//input[@data-value='company']");
                InputElement.setInput("//div[contains(@name,'name')]/div/input", constantNewCompany + firstEvent + "_" + RANDOM_NUM);
                commonPage.clickOnLinkByXpath("//a[@name='accounting']");
                SelectElement.setSimpleSelectValue("//select[@id='tracking_first_event']", firstEvent);
                return constantNewCompany + firstEvent + "_" + RANDOM_NUM;
            }
        } else {
            if (commonPage.getElementByXpath("//*[contains(@class, 'o_kanban_record_title')]/span[contains(text(), '" + constantNewCompany + RANDOM_NUM + ")]") == null) {
                commonPage.clickOnLinkByXpath("//input[@data-value='company']");
                InputElement.setInput("//div[contains(@name,'name')]/div/input", constantNewCompany + RANDOM_NUM);
                commonPage.clickOnLinkByXpath("//a[@name='accounting']");
                SelectElement.setSimpleSelectValue("//select[@id='tracking_first_event']", firstEvent);
            }
        }
        return constantNewCompany + RANDOM_NUM;
    }

    public String createNewCompany() throws InterruptedException{
        return createNewCompany("");
    }

}

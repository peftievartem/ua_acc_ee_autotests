package com.selferp.pages.elements;

import com.selferp.inject.Inject;
import com.selferp.pages.CommonPage;
import com.selferp.utils.World;
import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;

import static com.selferp.utils.Constants.*;

public class SalesPageElements extends CommonPage {

    @Inject
    public SalesPageElements(World world) {
        super(world);
    }

    protected CommonPage commonPage = new CommonPage(world);

    public String createProposition(String companyName, String productName, String productQuantity, String productPrice) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", companyName);
        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addAProduct + "']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", productName);
        InputElement.setInput("//div[contains(@name,'product_uom_qty')]/input", productQuantity);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", productPrice);
        ButtonElement.clickOnButtonByClass("o_form_button_save");

        return commonPage.getElementByXpath("//h1/div[@name='name']/span").getText();
    }

    public String createProposition(String companyName, String productName, String productPrice) throws InterruptedException {
        return createProposition(companyName, productName, "1", productPrice);
    }



    public void confirmProposition(String propositionName) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.waitForPageToLoad();
        commonPage.filterBy(propositionName);
        commonPage.clickOnLinkByXpath("//td[@data-tooltip='" + propositionName + "']");
        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");
    }

    public void deliveryProducts(String propositionName, String productsQuantity) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.waitForPageToLoad();
        commonPage.filterBy(propositionName);
        commonPage.clickOnLinkByXpath("//td[@data-tooltip='" + propositionName + "']");
        commonPage.clickOnLinkByXpath("//div[@name='delivery_count']");
        commonPage.clickOnLinkByXpath("//div[@name='qty_done']");
        InputElement.setInput("//div[contains(@name,'qty_done')]/input", productsQuantity);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();
    }

    public void deliveryProducts(String propositionName) throws InterruptedException {
        deliveryProducts(propositionName, "1");
    }

    public void turnBackProducts(String propositionName, String productQuantity) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.waitForPageToLoad();
        commonPage.filterBy(propositionName);
        commonPage.clickOnLinkByXpath("//td[@data-tooltip='" + propositionName + "']");
        commonPage.clickOnLinkByXpath("//div[@name='delivery_count']");
        ButtonElement.clickOnButtonXpath("//button[@data-hotkey='k']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//td[contains(@name,'quantity')]");
        InputElement.setInput("//div[contains(@name,'quantity')]/input", productQuantity);
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonXpath("//button[@name='create_returns']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//td[contains(@name,'quantity_done')]");
        InputElement.setInput("//div[contains(@name,'quantity_done')]/input", productQuantity);
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");


    }

    public void createInvoice(String propositionName, String invoiceDate) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.waitForPageToLoad();
        commonPage.filterBy(propositionName);
        commonPage.clickOnLinkByXpath("//td[@data-tooltip='" + propositionName + "']");

        ButtonElement.clickOnButtonXpath("//button[@name='create_invoice_delivered']");
        if (!invoiceDate.isEmpty())
            InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", invoiceDate);
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");
        commonPage.waitForPageToLoad();
    }

    public void createInvoice(String propositionName) throws InterruptedException {
        createInvoice(propositionName, "");
    }

    public void createStorno(String propositionName, String productQuantity, String stornoDate) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.waitForPageToLoad();
        commonPage.filterBy(propositionName);
        commonPage.clickOnLinkByXpath("//td[@data-tooltip='" + propositionName + "']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//div[@name='invoice_count']");
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonXpath("//button[@name='action_reverse']");
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonXpath("//button[@name='reverse_moves']");
        if (!stornoDate.isEmpty())
            InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", stornoDate);
        commonPage.clickOnLinkByXpath("//td[contains(@name,'quantity')]");
        InputElement.setInput("//div[contains(@name,'quantity')]/input", productQuantity);
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");
        commonPage.waitForPageToLoad();
    }


}

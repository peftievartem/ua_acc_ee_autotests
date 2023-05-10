package com.selferp.pages.elements;

import com.selferp.inject.Inject;
import com.selferp.pages.CommonPage;
import com.selferp.utils.World;
import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;

import static com.selferp.utils.Constants.*;

public class AccountingPageElements extends CommonPage {

    @Inject
    public AccountingPageElements(World world) {
        super(world);
    }
    protected CommonPage commonPage = new CommonPage(world);

    public void createPayment(String companyName, String paymentLabel, String paymentPrice,  String paymentDate) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", paymentLabel);
        InputElement.setInput("//input[@id='amount']", paymentPrice);
        if (!paymentDate.isEmpty()) {
            InputElement.setInputForDateTimePickerXpath("//input[@id='date']", paymentDate);
        }
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", companyName);
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");
    }

    public void createPayment(String companyName, String paymentLabel, String paymentPrice) throws InterruptedException {
        createPayment(companyName, paymentLabel, paymentPrice, "");
    }


    public void selectPayment(String paymentName) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        commonPage.waitForPageToLoad();
        commonPage.filterBy(paymentName);
    }

    public void setPaymentProposition(String propositionName) throws InterruptedException {
        commonPage.clickOnLinkByXpath("//div[@name='linked_sale_order_id']");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='linked_sale_order_id']", propositionName);
        commonPage.waitForPageToLoad();
    }

    public String createContract(String companyName, String contractStartDate) throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_receivables", "selferp_contract_settlement.account_contract_menu_customer");
        commonPage.waitForPageToLoad();

        // create contract
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", companyName);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", contractStartDate);
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        return commonPage.getElementByXpath("//h1/div/span").getText();
    }

    public String createContract(String companyName) throws InterruptedException {
        return createContract(companyName, commonPage.getDate(0));
    }

    public void setContract(String contractName) throws InterruptedException {
        commonPage.clickOnLinkByXpath("//div[@name='linked_sale_order_id']");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", contractName);
        commonPage.waitForPageToLoad();
    }
}
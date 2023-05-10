package test.Accounting;

import io.qameta.allure.Description;
import com.selferp.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class ContractTest extends BaseTest {
    String customerContractName;
    String customerContractNameFull;
    String vendorContractName;
    String vendorContractNameFull;



//    @Test(priority = 1)
//    @Description("Create contract with customer")
//    public void testAccountingInTermsContractsReceive() throws InterruptedException {
//        commonPage.gotoAppsPage();
//        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
//        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_receivables", "selferp_contract_settlement.account_contract_menu_customer");
//        commonPage.waitForPageToLoad();
//
//        // create contract
//        ButtonElement.clickOnButtonByClass("o_list_button_add");
//        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
//        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
//        ButtonElement.clickOnButtonByClass("o_form_button_save");
//        commonPage.waitForPageToLoad();
//
//        customerContractName = commonPage.getElementByXpath("//h1/div/span").getText();
//        customerContractNameFull = commonPage.getElementByXpath("//span[contains(@class,'text-truncate')]").getText();
//
//        Assert.assertTrue(customerContractName.length() > 0);
//        Assert.assertTrue(customerContractNameFull.length() > 0);
//    }
//
//    @Test(priority = 2)
//    @Description("Create Bank extraction reconciliation")
//    public void testBankExtractionReconciliations() throws InterruptedException {
//        // go to reconciliations
//        commonPage.gotoAppsPage();
//        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
//        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
//        ButtonElement.clickOnButtonByClass("oi-view-kanban");
//        commonPage.waitForPageToLoad();
//
//        // create new bank reconciliation
//        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
//        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-1");
//        InputElement.setInput("//input[@id='amount']", String.valueOf(price1));
//        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
//        String price = commonPage.getElementByXpath("//input[@id='amount']").getAttribute("value");
//        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");
//
//        // back to reconciliation in list
//        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
//        // added contract
//        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", customerContractName);
//        commonPage.waitForPageToLoad();
//        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
//        commonPage.waitForPageToLoad();
//
//        //select reconciliation again and press on bank in account line
//        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
//        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");
//
//        // open contract column in table rows
//        ButtonElement.clickOnButtonXpath("//div[contains(@class,'o_optional_columns_dropdown')]/button");
//        if (!commonPage.getElementByXpath("//input[@name='contract_id']").isSelected())
//            commonPage.clickOnLinkByXpath("//input[@name='contract_id']");
//
//        Assert.assertTrue(commonPage.getElementByXpath("(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText().length() > 0);
//        String priceInTable = commonPage.getElementByXpath("(//div[@name='line_ids']/descendant::td[@name='credit'])[last()]").getText();
//        Assert.assertTrue(priceInTable.replace("\u00a0"," ").contains(price.replace("\u00a0"," ")));
//
//    }
//
//
//
//    // -------------------------------
//

    @Test(priority = 3)
    @Description("Create contract with vendor")
    public void testAccountingInTermsContractsPay() throws InterruptedException {
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "selferp_contract_settlement.account_contract_menu_vendor");

        // create contract
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        vendorContractName = commonPage.getElementByXpath("//h1/div/span").getText();
        vendorContractNameFull = commonPage.getElementByXpath("//span[contains(@class,'text-truncate')]").getText();

        Assert.assertTrue(vendorContractName.length() > 0);
        Assert.assertTrue(vendorContractNameFull.length() > 0);
    }

    @Test(priority = 4)
    @Description("Contract - receipt of payment from the buyer - create payment")
    public void testContractReceiptPaymentFromBuyer1() throws InterruptedException {
        // go to reconciliations
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();

        //create payment
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addAProduct + "']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", constantProduct1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", String.valueOf(price1));
        if (commonPage.getElementByXpath("//div[@name='tax_id']/descendant::*[@aria-label='Delete']") != null)
            commonPage.clickOnLinkByXpath("//div[@name='tax_id']/descendant::*[@aria-label='Delete']");
        InputElement.setInputForDateTimePickerXpath("//input[@id='validity_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");

        commonPage.waitForPageToLoad();
        String orderName = baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        //delivery
        commonPage.clickOnLinkByXpath("//div[@name='delivery_count']");
        commonPage.clickOnLinkByXpath("//td[@name='qty_done']");
        InputElement.setInput("//td[@name='qty_done']/div/input", "1");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.clickOnLinkByXpath("//a[text()='" + orderName + "']");

        ButtonElement.clickOnButtonXpath("//button[@name='create_invoice_delivered']");
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");

        Assert.assertTrue(vendorContractNameFull.contains(commonPage.getElementByXpath("//div[@name='contract_id']/a").getText()));
    }


    @Test(priority = 5)
    @Description("Contract - receipt of payment from the buyer")
    public void testContractReceiptPaymentFromBuyer2() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");
        commonPage.waitForPageToLoad();

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-2");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInput("//input[@id='amount']", String.valueOf(price1));
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-2']");
        // validate wo adding contract
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-2']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows
        ButtonElement.clickOnButtonXpath("//div[contains(@class,'o_optional_columns_dropdown')]/button");
        if (!commonPage.getElementByXpath("//input[@name='contract_id']").isSelected())
            commonPage.clickOnLinkByXpath("//input[@name='contract_id']");

        Assert.assertTrue(commonPage.getElementByXpath("(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText().length() > 0);
    }


//  ----------------------------


    @Test(priority = 11)
    @Description("Create contract with vendor")
    public void testAccountingInTermsContractsVendor() throws InterruptedException {
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "selferp_contract_settlement.account_contract_menu_vendor");

        // created contract
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        vendorContractName = commonPage.getElementByXpath("//h1/div/span").getText();
        vendorContractNameFull = commonPage.getElementByXpath("//span[contains(@class,'text-truncate')]").getText();

        Assert.assertTrue(vendorContractName.length() > 0);
        Assert.assertTrue(vendorContractNameFull.length() > 0);
    }


    @Test(priority = 12)
    @Description("Create Bank extraction reconciliation for vendor")
    public void testBankExtractionReconciliationsVendor() throws InterruptedException {
        // go to reconciliations
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");
        commonPage.waitForPageToLoad();

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-3");
        InputElement.setInput("//input[@id='amount']", String.valueOf(price1 * -1));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        String price = commonPage.getElementByXpath("//input[@id='amount']").getAttribute("value");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-3']");
        // added contract
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-3']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows
        ButtonElement.clickOnButtonXpath("//div[contains(@class,'o_optional_columns_dropdown')]/button");
        if (!commonPage.getElementByXpath("//input[@name='contract_id']").isSelected())
            commonPage.clickOnLinkByXpath("//input[@name='contract_id']");

        Assert.assertTrue(commonPage.getElementByXpath("(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText().length() > 0);
    }


    @Test(priority = 13)
    @Description("Create contract purchase from user 2")
    public void testCreateContractBuyer() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("purchase.menu_purchase_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("purchase.menu_procurement_management", "purchase.menu_purchase_form_action");

        // create purchase order
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_order']", commonPage.getDate(0));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);
        commonPage.waitForPageToLoad();

        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addAProduct + "']");
        InputElement.setInputDropdownWithoutButtonByXpath("//td[@name='product_id']/descendant::input", constantService1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", String.valueOf(price1));
        if (commonPage.getElementByXpath("//div[@name='taxes_id']/descendant::*[@aria-label='Delete']") != null)
            commonPage.clickOnLinkByXpath("//div[@name='taxes_id']/descendant::*[@aria-label='Delete']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_confirm']");
        ButtonElement.clickOnButtonXpath("//button[@name='action_create_invoice']");
        InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");
        commonPage.waitForPageToLoad();

        Assert.assertNotNull(commonPage.getElementByXpath("//button[@name='action_register_payment']"));
    }
}

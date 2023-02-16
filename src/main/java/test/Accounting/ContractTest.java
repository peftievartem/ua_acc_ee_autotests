package test.Accounting;

import com.sun.source.tree.AssertTree;
import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.*;
import static com.self.utils.Hooks.LOG;

public class ContractTest extends BaseTest {
    String customerContractName;
    String customerContractNameFull;
    String vendorContractName;
    String vendorContractNameFull;


    @Test(priority = 1)
    @Description("Create contract prepay from user")
    public void testtestAccountingInTermsContractsReceive() throws InterruptedException {
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // created contract
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_receivables", "l10n_ua_contract.account_contract_menu_customer");
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='payment_term_id']", days15);
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        customerContractName =  commonPage.getElementByXpath("//h1/div/span").getText();
        customerContractNameFull =  commonPage.getElementByXpath("//span[contains(@class,'text-truncate')]").getText();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");

        commonPage.getElementByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']").click();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");

        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-1");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInput("//input[@id='amount']", "1000");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
        commonPage.waitForPageToLoad();
        // added contract
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", customerContractName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows
        ButtonElement.clickOnButtonXpath("//div[contains(@class,'o_optional_columns_dropdown')]/button");

        commonPage.clickOnLinkByXpath("//input[@name='contract_id']");
        commonPage.waitForPageToLoad();

        Assert.assertEquals(customerContractNameFull, baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText());
        String creditLastString = baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='credit'])[last()]").getText();
        Assert.assertTrue(creditLastString.contains("1,000"));

        LOG.info("Customer contract name2 - " + customerContractNameFull);
    }

    @Test(priority = 2)
    @Description("Create contract prepay from user 2")
    public void testAccountingInTermsContractsReceive2() throws InterruptedException {

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();

        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();

        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", customerContractName);

        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='Add a product']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", constantProduct1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", "1000");
        InputElement.setInputForDateTimePickerXpath("//input[@id='validity_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");

        commonPage.waitForPageToLoad();
        String orderName =  baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        //delivery
        commonPage.clickOnLinkByXpath("//div[@name='delivery_count']");
        commonPage.clickOnLinkByXpath("//td[@name='quantity_done']");
        InputElement.setInput("//td[@name='quantity_done']/div/input", "1");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.clickOnLinkByXpath("//a[text()='" + orderName + "']");

        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice']");
        RadioButtonElement.selectRadioButtonByDataValue("delivered");
        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice_open']");



        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-2");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInput("//input[@id='amount']", "1000");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-2']");
        commonPage.waitForPageToLoad();
        // validate wo adding contract
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-2']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows

        // already opened



//        Assert.assertEquals(customerContractNameFull, baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText());
    }


    @Test(priority = 3)
    @Description("Create contract purchase from user")
    public void testCreateContractPayable() throws InterruptedException {

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // created contract in vendor contracts
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "l10n_ua_contract.account_contract_menu_vendor");
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='payment_term_id']", days15);
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        vendorContractName =  baseElementLocator.getWebElement("Xpath", "//h1/div/span").getText();
        vendorContractNameFull =  baseElementLocator.getWebElement("Xpath", "//span[contains(@class,'text-truncate')]").getText();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-3");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInput("//input[@id='amount']", "-1000");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-3']");
        commonPage.waitForPageToLoad();
        // added contract
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-3']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows

        // already opened

        Assert.assertEquals(vendorContractNameFull, baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText());
        String debitLastString = baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='debit'])[last()]").getText();
        Assert.assertTrue(debitLastString.contains("1,000"));
    }


    @Test(priority = 4)
    @Description("Create contract purchase from user 2")
    public void testCreateContractPayable2() throws InterruptedException {

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // created contract in vendor contracts
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "l10n_ua_contract.account_contract_menu_vendor");
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInputForDateTimePickerXpath("//input[@id='date_start']", commonPage.getDate(0));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='payment_term_id']", days15);
        ButtonElement.clickOnButtonByClass("o_form_button_save");
        commonPage.waitForPageToLoad();

        vendorContractName =  baseElementLocator.getWebElement("Xpath", "//h1/div/span").getText();
        vendorContractNameFull =  baseElementLocator.getWebElement("Xpath", "//span[contains(@class,'text-truncate')]").getText();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-4");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInput("//input[@id='amount']", "-1000");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-4']");
        // added contract
        commonPage.waitForPageToLoad();
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-4']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        // open contract column in table rows

        // already opened

        Assert.assertEquals(vendorContractNameFull, baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText());
        String debitLastString = baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='debit'])[last()]").getText();
        Assert.assertTrue(debitLastString.contains("1,000"));




        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();

        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();

        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='contract_id']", vendorContractName);

        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='Add a product']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", constantProduct1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", "1000");
        InputElement.setInputForDateTimePickerXpath("//input[@id='validity_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");

        commonPage.waitForPageToLoad();
        String orderName =  baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        //delivery
        commonPage.clickOnLinkByXpath("//div[@name='delivery_count']");
        commonPage.clickOnLinkByXpath("//td[@name='quantity_done']");
        InputElement.setInput("//td[@name='quantity_done']/div/input", "1");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.clickOnLinkByXpath("//a[text()='" + orderName + "']");
        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice']");
        RadioButtonElement.selectRadioButtonByDataValue("delivered");
        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice_open']");

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();

        // go to bank reconciliations
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

//        // create new bank reconciliation
//        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
//        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-4");
//        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyDecoAddict);
//        InputElement.setInput("//input[@id='amount']", "1000");
//        commonPage.waitForPageToLoad();
//        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");
//
//        // back to reconciliation in list
//        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-4']");
//        commonPage.waitForPageToLoad();
//
//        // validate wo adding contract
//        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
//        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-4']");
//        //select reconciliation again and press on bank in account line
//        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");
//        commonPage.waitForPageToLoad();
//
//        // open contract column in table rows
//
//        // already opened
//
//        Assert.assertEquals(customerContractNameFull, baseElementLocator.getWebElement("Xpath", "(//div[@name='line_ids']/descendant::td[@name='contract_id'])[last()]").getText());
    }
}

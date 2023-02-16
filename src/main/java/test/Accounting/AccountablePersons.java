package test.Accounting;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import test.BaseTest;
import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.testng.Assert;

import static com.self.utils.Constants.*;
import static com.self.utils.Hooks.LOG;


public class AccountablePersons  extends BaseTest {

    @Test(priority = 1)
    @Description("Accountable persons through payables - reports ")
    public void testAccountingPayableReport() throws InterruptedException {
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "l10n_ua_accountable_person.account_move_menu_advance_reports");

        //create report
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id_1']", constantVendorMitchellAdmin);
        baseElementLocator.getWebElement("Xpath", "//table/descendant::*[text()='" + addALine + "']").click();
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_id']/descendant::input", constantService1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", "10");
        InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");

        // go to bank reconciliations
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "accountable_persons_" + RANDOM_NUM + "-1");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantVendorMitchellAdmin);
        InputElement.setInput("//input[@id='amount']", "-12");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + "-1']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + "-1']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        baseElementLocator.getWebElement("Xpath", "//a[@name='aml_tab']").click();
        commonPage.waitForPageToLoad();

        Boolean doc372 = false;

        for (WebElement el : baseElementLocator.getListWebElements("Xpath", "//td[@name='account_id']")) {
            if (el.getText().startsWith("372")) {
                doc372 = true;
            }
        }
        Assert.assertTrue(doc372);
   }

    @Test(priority = 2)
    @Description("Accountable persons through payables - reports ")
    public void tesAccountingPayableReport2() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("purchase.menu_purchase_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("purchase.menu_procurement_management", "l10n_ua_accountable_person.purchase_order_menu_purchase_from_accountable_persons");

        //create report
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id_1']", constantVendorMitchellAdmin);
        baseElementLocator.getWebElement("Xpath", "//table/descendant::*[text()='" + addAProduct + "']").click();
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_id']/descendant::input", constantService1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", "20");
        ButtonElement.clickOnButtonXpath("//button[@name='button_confirm']");

        // go to bank reconciliations
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "accountable_persons_" + RANDOM_NUM + "-1");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantVendorMitchellAdmin);
        InputElement.setInput("//input[@id='amount']", "-24");
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + "-1']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + "-1']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        baseElementLocator.getWebElement("Xpath", "//a[@name='aml_tab']").click();
        commonPage.waitForPageToLoad();

        Boolean doc372 = false;

        for (WebElement el : baseElementLocator.getListWebElements("Xpath", "//td[@name='account_id']")) {
            if (el.getText().startsWith("372")) {
                doc372 = true;
            }
        }
        Assert.assertTrue(doc372);
    }
}

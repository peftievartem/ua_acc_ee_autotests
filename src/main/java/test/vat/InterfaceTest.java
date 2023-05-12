package test.vat;

import com.selferp.utils.elements.CheckBoxElement;
import io.qameta.allure.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class InterfaceTest extends BaseTest {

//    @Test(priority = 1)
//    @Description("VAT general settings")
//    public void testVatInGeneralSettings() throws InterruptedException {
//        commonPage.gotoAppsPage();
//        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='base.menu_administration']");
//        commonPage.clickOnLinkByXpath("//div[@data-key='account']");
//        commonPage.filterBy(vatPayer);
//        Assert.assertNotNull(commonPage.getElementByXpath("//b[text()='" + vatPayer + "']"));
//
//        if (!CheckBoxElement.isCheckedById("vat_payer"))
//            commonPage.clickOnLinkByXpath("//input[@id='vat_payer']");
//        commonPage.clickOnLinkByXpath("//a[@class='o_menu_toggle']");
//    }
//
//    @Test(priority = 2)
//    @Description("VAT new columns in first event table")
//    public void testTableVatColumn() throws InterruptedException {
//        commonPage.gotoAppsPage();
//        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
//        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");
//        Assert.assertNotNull(commonPage.getElementByXpath("//span[(contains(@class, 'o_account_report_column_value')) and text()='" + vatFirstEvent + "']"));
//        Assert.assertNotNull(commonPage.getElementByXpath("//span[(contains(@class, 'o_account_report_column_value')) and text()='" + vatSum + "']"));
//    }
//
//    @Test(priority = 3)
//    @Description("Link to VAT in proposition")
//    public void testVatLinkInProposition() throws InterruptedException {
//        commonPage.gotoAppsPage();
//        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
//        commonPage.clickOnLinkByXpath("(//td[@name='name'])[1]");
//        Assert.assertNotNull(commonPage.getElementByXpath("//button[@name='action_view_vat_invoices']"));
//    }

    @Test(priority = 4)
    @Description("VAT order/contract columns in vat-list table")
    public void testTableVatOrderContractColumns() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_move_menu_vat_invoice");
        Assert.assertNotNull(commonPage.getElementByXpath("//th[@data-name='vat_contract_ids']"));
        Assert.assertNotNull(commonPage.getElementByXpath("//th[@data-name='vat_sale_order_ids']"));
    }

        // інший колір якщо податкові сформовані

}
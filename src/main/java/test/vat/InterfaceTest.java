package test.vat;

import io.qameta.allure.Description;
import org.testng.annotations.Test;
import test.BaseTest;

public class InterfaceTest extends BaseTest {

    @Test(priority = 1)
    @Description("VAT by ContrAgent")
    public void testInterfaceElements() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");


// колонка податок

        // у накладної внизу блок 'група податків'
        // правий верхній кут - замовлення.

        // те саме у повернень


        // з пропозиції переходи на всі накладні чи по договорам чи по замовленням
        // у списку подактових по якому договору по якому замовленню

//        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'o_account_report_column_value')) and (contains(text(), '" + pershaPodiya + "'))]").isDisplayed());

    }
}
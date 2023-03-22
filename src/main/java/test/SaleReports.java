package test;

import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.self.utils.Constants.*;

public class SaleReports extends BaseTest {

    @Test(priority = 1)
    @Description("Create report sale buttons from invoices page")
    public void testSaleReport1() throws InterruptedException {
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_receivables", "account.menu_action_move_out_invoice_type");

        commonPage.clickOnLinkByXpath("//table/tbody/tr[1]/td[2]");
        ButtonElement.clickOnButtonXpath("//button[@data-hotkey='shift+u']");
        Assert.assertNotNull(commonPage.getElementByXpath("//span[(contains(@class, 'dropdown-item')) and (contains(text(), '" + vydatkovaNakladna + "'))]"));
        Assert.assertNotNull(commonPage.getElementByXpath("//span[(contains(@class, 'dropdown-item')) and (contains(text(), '" + aktVykonanykhRobit + "'))]"));
    }

    @Test(priority = 2)
    @Description("Create report sale buttons from order page")
    public void testSaleReport2() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("sale.menu_sale_invoicing", "sale.menu_sale_order_invoice");

        commonPage.clickOnLinkByXpath("//table/tbody/tr[1]");
        ButtonElement.clickOnButtonXpath("//button[@data-hotkey='shift+u']");
        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'dropdown-item')) and (contains(text(), '" + rakhunokNaOplatu + "'))]").isDisplayed());
    }
}

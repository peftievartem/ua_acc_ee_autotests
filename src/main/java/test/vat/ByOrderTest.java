package test.vat;

import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;
import static com.selferp.utils.Constants.price1;

public class ByOrderTest extends BaseTest {

    @Test(priority = 1)
    @Description("VAT first position by order")
    public void testVatByOrder() throws InterruptedException {

        String companyName = companyPageElements.createNewCompany("by_order");

        String propositionName1 = salesPageElements.createProposition(companyName, constantProduct1, "10", String.valueOf(price1));

        accountingPageElements.createPayment(companyName, "byOrder_payment_1_" + RANDOM_NUM, String.valueOf(price1*5), commonPage.getDateDay(10, 0));
        accountingPageElements.selectPayment("byOrder_payment_1_" + RANDOM_NUM);
        accountingPageElements.setPaymentProposition(propositionName1);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");

        salesPageElements.confirmProposition(propositionName1);
        salesPageElements.deliveryProducts(propositionName1, "10");
        salesPageElements.createInvoice(propositionName1,  commonPage.getDateDay(20, 0));
        //check new interface button orders
        commonPage.checkElementByXpath("//button[@name='action_view_source_sale_orders']");
        commonPage.waitForPageToLoad();

        // turn back products
        salesPageElements.turnBackProducts(propositionName1, "5");
        salesPageElements.createStorno(propositionName1, "5", commonPage.getDateDay(25, 0));
        String totalAmount = commonPage.getElementByXpath("//span[@name='amount_total']").getText();

        // -- test info in report
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");

        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_tracking_first_event'))]");
        commonPage.clickOnLinkByXpath("//a[@data-id='by_order']");
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_date'))]");
        commonPage.clickOnLinkByXpath("//a[@data-filter='this_year']");
        commonPage.clickOnLinkByXpath("//div[@id='extra_options_dropdown']");
        commonPage.clickOnLinkByXpath("//a[@data-filter='groupby_sale_order']");
        commonPage.waitForPageToLoad();
        InputElement.setInput("//input[@class='o_searchview_input']", companyName);
        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + companyName + "')]");
        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'account_report_line_name')) and (contains(text(), '" + propositionName1 + "'))]").isDisplayed());
        commonPage.waitForPageToLoad();
        // + check new interface button
        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + companyName + "')]/a[@action='action_create_vat_invoices_by_partner']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//div[contains(text(), '" + companyName + "')]");
        String companyReportsTitle = commonPage.getElementByXpath("//th/descendant::*[contains(text(), '" + companyName + "')]").getText();
        Assert.assertTrue(companyReportsTitle.contains("(3)"));

        //amount_total
        String firstPayTotal = commonPage.getElementByXpath("(//td[@name='vat_line_total'])[1]").getText();
        String turnBackTotal = commonPage.getElementByXpath("(//td[@name='vat_line_total'])[3]").getText();

        Assert.assertEquals(firstPayTotal, totalAmount.substring(0, totalAmount.length()-2));
        Assert.assertEquals(turnBackTotal, "-" + totalAmount.substring(0, totalAmount.length()-2));

        //test green light of confirmed vat in first-event table
        commonPage.clickOnLinkByXpath("//td[@name='name']");
        commonPage.clickOnLinkByXpath("//button[@name='action_post']");
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");

//        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_tracking_first_event'))]");
//        commonPage.clickOnLinkByXpath("//a[@data-id='by_order']");
//        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_date'))]");
//        commonPage.clickOnLinkByXpath("//a[@data-filter='this_year']");
//        commonPage.clickOnLinkByXpath("//div[@id='extra_options_dropdown']");
//        commonPage.clickOnLinkByXpath("//a[@data-filter='groupby_sale_order']");
//        commonPage.waitForPageToLoad();
        InputElement.setInput("//input[@class='o_searchview_input']", propositionName1);
        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + propositionName1 + "')]");
        commonPage.waitForPageToLoad();
        Assert.assertNotNull(commonPage.getElementByXpath("//td[@class='o_account_report_line number text-success']"));



    }
}

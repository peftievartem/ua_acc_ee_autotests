package test.vat;

import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class ByContrAgentTest extends BaseTest {

    @Test(priority = 1)
    @Description("VAT first position by contrAgent")
    public void testAccountingInTermsOrders() throws InterruptedException {

        String companyName = companyPageElements.createNewCompany("in_general");

        String propositionName = salesPageElements.createProposition(companyName, constantProduct1, String.valueOf(price2));
        accountingPageElements.createPayment(companyName, "25_payment_" + RANDOM_NUM, String.valueOf(price2/4));

        accountingPageElements.selectPayment("25_payment_" + RANDOM_NUM);
        accountingPageElements.setPaymentProposition(propositionName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");

        // -- test info in report
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");

        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_tracking_first_event'))]");
        commonPage.clickOnLinkByXpath("//a[@data-id='in_general']");
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_date'))]");
        commonPage.clickOnLinkByXpath("//a[@data-filter='this_year']");

        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'o_account_report_column_value')) and (contains(text(), '" + pershaPodiya + "'))]").isDisplayed());
        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'account_report_line_name')) and (contains(text(), '" + companyName + "'))]").isDisplayed());

        // Invoice
        salesPageElements.confirmProposition(propositionName);
        salesPageElements.createInvoice(propositionName, commonPage.getDate(1));

        // VAT Report genegation
        commonPage.gotoAppsPage();


        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");

        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_tracking_first_event'))]");
        commonPage.clickOnLinkByXpath("//a[@data-id='in_general']");
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_date'))]");
        commonPage.clickOnLinkByXpath("//a[@data-filter='this_year']");
        commonPage.waitForPageToLoad();
        InputElement.setInput("//input[@class='o_searchview_input']", companyName);
        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + companyName + "')]");
        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + companyName + "')]/a[@action='action_create_vat_invoices_by_partner']");
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//div[contains(text(), '" + companyName + "')]");
        String companyReportsTitle = commonPage.getElementByXpath("//th/descendant::*[contains(text(), '" + companyName + "')]").getText();
        Assert.assertTrue(companyReportsTitle.contains("(2)"));
    }
}
package test.vat;

import com.selferp.utils.elements.ButtonElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;
import static com.selferp.utils.Constants.price1;

public class ByContractTest extends BaseTest {

    // We need configure VAT in configurations and Plan of accounts

    @Test(priority = 1)
    @Description("VAT by Contract")
    public void testVatByContract() throws InterruptedException {


        String companyName = companyPageElements.createNewCompany("by_contract");

        String contractName = accountingPageElements.createContract(companyName);

        String propositionName1 = salesPageElements.createProposition(companyName, constantProduct1, String.valueOf(price1));

        accountingPageElements.createPayment(companyName, "byContract_payment_1_" + RANDOM_NUM, String.valueOf(price1*4), commonPage.getDateDay(10, 0));
        accountingPageElements.selectPayment("byContract_payment_1_" + RANDOM_NUM);
        accountingPageElements.setContract(contractName);
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");

        salesPageElements.confirmProposition(propositionName1);
        salesPageElements.deliveryProducts(propositionName1, "10");
        salesPageElements.createInvoice(propositionName1,  commonPage.getDateDay(20, 0));
        commonPage.waitForPageToLoad();

        // -- test info in report
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("selferp_l10n_ua_vat.menu_accounting_vat", "selferp_l10n_ua_vat.account_report_vat_first_event_menu");

        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_tracking_first_event'))]");
        commonPage.clickOnLinkByXpath("//a[@data-id='by_contract']");
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'o_account_reports_filter_date'))]");
        commonPage.clickOnLinkByXpath("//a[@data-filter='this_year']");
        commonPage.clickOnLinkByXpath("//div[@id='extra_options_dropdown']");
        commonPage.clickOnLinkByXpath("//a[@data-filter='groupby_contract']");

        commonPage.clickOnLinkByXpath("//span[contains(text(), '" + companyName + "')]");

        Assert.assertTrue(commonPage.getElementByXpath("//span[(contains(@class, 'account_report_line_name')) and (contains(text(), '" + contractName + "'))]").isDisplayed());




    }
}

package test;

import io.qameta.allure.Description;
import com.selferp.utils.elements.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.selferp.utils.Constants.constantCompanyAzure;

public class CompareReport extends BaseTest {

    @Test(priority = 1)
    @Description("Create report order from customer page")
    public void testPrintFromCustomer() throws InterruptedException {
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_receivables", "account.menu_account_customer");
        commonPage.clickOnLinkByXpath("//span[text()='" + constantCompanyAzure + "']");

        if (!commonPage.checkElementByXpath("//button[@id='partner_ledger_button']")) {
            commonPage.clickOnLinkByXpath("//button[contains(@class, 'o_button_more')]");
        }
        ButtonElement.clickOnButtonXpath("//button[@id='partner_ledger_button']");
        commonPage.clickOnLinkByXpath("//span[(@class='account_report_line_name') and (contains(text(), '" + constantCompanyAzure + "'))]");
        Assert.assertNotNull(commonPage.getElementByXpath("//span[(@class='account_report_line_name') and (contains(text(), '" + constantCompanyAzure + "'))]/a[@action='action_export_file_reconciliation_report']"));
    }

    @Test(priority = 2)
    @Description("Create report order from customer page")
    public void testPrintFromReports() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_reports", "account_reports.menu_action_account_report_partner_ledger");

        WebElement report_partner_ledger =  baseElementLocator.getWebElement("Xpath","//span[(@class='account_report_line_name') and (contains(text(), '" + constantCompanyAzure + "'))]");
        if (!report_partner_ledger.isDisplayed()) {
            commonPage.clickOnLinkByXpath("//button[contains(@class, 'o_button_more')]");
        }
        commonPage.clickOnLinkByXpath("//span[(@class='account_report_line_name') and (contains(text(), '" + constantCompanyAzure + "'))]");
        Assert.assertNotNull(commonPage.getElementByXpath("//span[(@class='account_report_line_name') and (contains(text(), '" + constantCompanyAzure + "'))]/a[@action='action_export_file_reconciliation_report']"));
    }
}

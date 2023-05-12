package test.Accounting;

import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import io.qameta.allure.Description;
import com.selferp.utils.elements.*;
import org.testng.Assert;

import static com.selferp.utils.Constants.*;

// Розрахунки з підзвітними особами

public class AccountablePersons  extends BaseTest {

    @DataProvider(name = "service elem")
    public Object[][] serviceElem() {
        return new Object[][]{
                {constantService1, price1},

        };
    }

    @Test(priority = 1, dataProvider = "service elem")
    @Description("Accountable persons - Creating Advance Report")
    public void testCreateAdvReport(String serviceElem, int price) throws InterruptedException {
        //commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.waitForPageToLoad();
        commonPage.selectPageInTopBarMenuByDataXmlId("account.menu_finance_payables", "selferp_accountable_person.account_move_menu_advance_reports");

        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id_1']", constantVendorMitchellAdmin);
        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addALine + "']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_id']/descendant::input", serviceElem);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", String.valueOf(price));
        if (commonPage.checkElementByXpath("//div[@name='taxes_id']/descendant::*[@aria-label='Delete']"))
            commonPage.clickOnLinkByXpath("//div[@name='taxes_id']/descendant::*[@aria-label='Delete']");
        InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", commonPage.getDate(0));
        commonPage.waitForPageToLoad();

        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");
        commonPage.waitForPageToLoad();

        commonPage.clickOnLinkByXpath("//a[@name='aml_tab']");
        boolean doc372 = false;
        for (WebElement el : baseElementLocator.getListWebElements("Xpath", "//td[@name='account_id']")) {
            if (el.getText().startsWith("372")) {
                doc372 = true;
            }
        }
        Assert.assertTrue(doc372);
    }


    @Test(priority = 2, dataProvider = "service elem")
    @Description("Accountable persons - advanced reports ")
    public void tesAccountingPayableReport2(String serviceElem, int price) throws InterruptedException {

        // go to bank reconciliations
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("account_accountant.menu_accounting").click();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");
        commonPage.waitForPageToLoad();

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "accountable_persons_" + RANDOM_NUM + price);
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantVendorMitchellAdmin);
        InputElement.setInput("//input[@id='amount']", String.valueOf(price*-1));
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list and validate
        commonPage.waitForPageToLoad();
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + price + "']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        //select reconciliation again and press on bank in account line
        commonPage.clickOnLinkByXpath("//span[text()='accountable_persons_" + RANDOM_NUM + price + "']");
        commonPage.clickOnLinkByXpath("//tr[contains(@class,'o_bank_rec_liquidity_line')]/descendant::*[contains(@class, 'o_form_uri')]");

        commonPage.clickOnLinkByXpath("//a[@name='aml_tab']");
        commonPage.waitForPageToLoad();
        boolean doc372 = false;
        for (WebElement el : baseElementLocator.getListWebElements("Xpath", "//td[@name='account_id']")) {
            if (el.getText().startsWith("372")) {
                doc372 = true;
            }
        }
        Assert.assertTrue(doc372);
    }
}

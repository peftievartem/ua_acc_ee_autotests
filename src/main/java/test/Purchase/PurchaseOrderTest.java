package test.Purchase;

import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.InputElement;
import com.self.utils.elements.RadioButtonElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.*;

public class PurchaseOrderTest extends BaseTest {

    String orderName;

    @Test(priority = 1)
    @Description("Create order and smart button visible")
    public void testAccountingInTermsPurchaseOrders() throws InterruptedException {

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("purchase.menu_purchase_root").click();

        commonPage.selectPageInTopBarMenuByDataXmlId("purchase.menu_procurement_management", "purchase.menu_purchase_form_action");
        commonPage.waitForPageToLoad();

        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);

        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addAProduct + "']");
        commonPage.waitForPageToLoad();
        InputElement.setInputDropdownWithoutButtonByXpath("//td[@name='product_id']/descendant::input", constantService1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", String.valueOf(price1));
        commonPage.clickOnLinkByXpath("//div[@name='taxes_id']/descendant::*[@aria-label='Delete']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_confirm']");

        commonPage.waitForPageToLoad();
        orderName = baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        ButtonElement.clickOnButtonXpath("//button[@name='action_create_invoice']");
        InputElement.setInputForDateTimePickerXpath("//input[@id='invoice_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");

        //Try to find journal smart button
        commonPage.clickOnLinkByXpath("//a[text()='" + orderName + "']");
        Assert.assertEquals(Integer.valueOf(commonPage.getElementByXpath("//div[@name='move_line_count']/span[@class='o_stat_info o_stat_value']").getText()), 1);
    }

//    @Test(priority = 2)
//    @Description("Create Bank extraction reconciliation")
//    public void testAccountingInTermsPurchaseOrdersBank() throws InterruptedException {
//        // go to reconciliations
//        commonPage.gotoAppsPage();
//        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
//        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
//        ButtonElement.clickOnButtonByClass("oi-view-kanban");
//
//        // create new bank reconciliation
//        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
//        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-1");
//        InputElement.setInput("//input[@id='amount']", String.valueOf(price1 * -1));
//        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
//        String price = commonPage.getElementByXpath("//input[@id='amount']").getAttribute("value");
//        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");
//
//        // back to reconciliation in list
//        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
//        commonPage.waitForPageToLoad();
//        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
//        commonPage.waitForPageToLoad();
//
//        commonPage.gotoAppsPage();
//        commonPage.appItemByDataXmlId("purchase.menu_purchase_root").click();
//
//        commonPage.selectPageInTopBarMenuByDataXmlId("purchase.menu_procurement_management", "purchase.menu_purchase_form_action");
//        commonPage.waitForPageToLoad();
//
//        commonPage.clickOnLinkByXpath("//td[text()='" + orderName + "']");
//        commonPage.waitForPageToLoad();
//
//        Assert.assertTrue(Integer.valueOf(commonPage.getElementByXpath("//div[@name='move_line_count']/span[@class='o_stat_info o_stat_value']").getText()) > 1);
//    }
}

package test.Sale;

import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class SaleOrderTest extends BaseTest {

    String orderName;

    @Test(priority = 1)
    @Description("Create order and smart button visible")
    public void testAccountingInTermsOrders() throws InterruptedException {

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();

        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();

        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);

        commonPage.clickOnLinkByXpath("//table/descendant::*[text()='" + addAProduct + "']");
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", constantProduct1);
        InputElement.setInput("//div[contains(@name,'price_unit')]/input", String.valueOf(price1));
        if (commonPage.checkElementByXpath("//div[@name='tax_id']/descendant::*[@aria-label='Delete']"))
            commonPage.clickOnLinkByXpath("//div[@name='tax_id']/descendant::*[@aria-label='Delete']");
        InputElement.setInputForDateTimePickerXpath("//input[@id='validity_date']", commonPage.getDate(0));
        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");

        commonPage.waitForPageToLoad();
        orderName = baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        //delivery
        commonPage.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", "//div[@name='delivery_count']").click();
        commonPage.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", "//td[@name='qty_done']").click();
        InputElement.setInput("//td[@name='qty_done']/div/input", "1");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", "//a[text()='" + orderName + "']").click();

        ButtonElement.clickOnButtonXpath("//button[@name='create_invoice_delivered']");
        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");

        //Try to find journal smart button
        commonPage.clickOnLinkByXpath("//a[text()='" + orderName + "']");
        Assert.assertEquals(Integer.valueOf(commonPage.getElementByXpath("//div[@name='move_line_count']/span[@class='o_stat_info o_stat_value']").getText()), 1);
    }


    @Test(priority = 2)
    @Description("Create Bank extraction reconciliation")
    public void testAccountingInTermsOrdersBank() throws InterruptedException {
        // go to reconciliations
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='account_accountant.menu_accounting']");
        commonPage.clickOnLinkByXpath("//a/span[text()='" + bank + "']");
        ButtonElement.clickOnButtonByClass("oi-view-kanban");
        commonPage.waitForPageToLoad();

        // create new bank reconciliation
        ButtonElement.clickOnButtonByClass("o-kanban-button-new");
        InputElement.setInput("//input[@id='payment_ref']", "label_" + RANDOM_NUM + "-1");
        InputElement.setInput("//input[@id='amount']", String.valueOf(price1));
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", constantCompanyAzure);
        ButtonElement.clickOnButtonXpath("//button[@name='action_save_close']");

        // back to reconciliation in list
        commonPage.clickOnLinkByXpath("//span[text()='label_" + RANDOM_NUM + "-1']");
        ButtonElement.clickOnButtonXpath("//button[@name='button_validate']");
        commonPage.waitForPageToLoad();

        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();

        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();

        commonPage.clickOnLinkByXpath("//div[contains(@class, 'o_searchview')]/descendant::*[contains(@class, 'o_facet_remove')]");
        commonPage.clickOnLinkByXpath("//td[text()='" + orderName + "']");

        Assert.assertTrue(Integer.parseInt(commonPage.getElementByXpath("//div[@name='move_line_count']/span[@class='o_stat_info o_stat_value']").getText()) > 1);
    }
}

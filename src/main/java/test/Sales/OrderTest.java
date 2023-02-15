package test.Sales;

import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.DropDownElement;
import com.self.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import org.openqa.selenium.Keys;

import static com.self.utils.Constants.*;
import static test.Sales.SalesTestData.salesPricelistName;
import static test.Sales.SalesTestData.salesPricelistOtherCurrencyName;

public class OrderTest extends BaseTest {

    @Test()
    @Description("Create pricelist")
    public void createPricelistTest() throws InterruptedException {
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonByClass("o_list_button_add");
        InputElement.setInputDropdownWithoutButtonByXpath("//input[@id='partner_id']", "Deco Addict");

        baseElementLocator.getWebElement("Xpath", "//table/descendant::*[text()='Add a product']").click();
        InputElement.setInputDropdownWithoutButtonByXpath("//div[@name='product_template_id']/descendant::input", "[FURN_6666] Acoustic Bloc Screens");

        InputElement.setInput("//div[contains(@name,'price_unit')]/input", "1000");

        InputElement.setInputForDateTimePickerXpath("//input[@id='validity_date']", commonPage.getDate(0));

        ButtonElement.clickOnButtonXpath("//button[@name='action_confirm']");

        commonPage.waitForPageToLoad();
        String orderName =  baseElementLocator.getWebElement("Xpath", "//h1/div[@name='name']").getText();

        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice_percentage']");

        InputElement.setInput("//input[@id='amount']", "50");
        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice_open']");

        ButtonElement.clickOnButtonXpath("//button[@id='create_invoice_percentage']");

        ButtonElement.clickOnButtonXpath("//button[@name='action_post']");

        commonPage.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", "//a[@name='aml_tab']").click();

        ButtonElement.clickOnButtonXpath("//div[contains(@class,'o_optional_columns_dropdown')]/button");
        commonPage.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", "//input[@name='linked_sale_order_id']").click();

        Assert.assertTrue(baseElementLocator.getWebElement("Xpath", "//td[@data-tooltip='" + orderName + "']").isDisplayed());
    }
}

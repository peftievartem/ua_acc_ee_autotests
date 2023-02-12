package test.Sales;

import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.DropDownElement;
import com.self.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.constantsCompanyCurrency;
import static com.self.utils.Constants.constantsOtherCurrency;
import static test.Sales.SalesTestData.salesPricelistName;
import static test.Sales.SalesTestData.salesPricelistOtherCurrencyName;

public class OrderTest extends BaseTest {

    @DataProvider(name = "order combination")
    public Object[][] pricelist() {
        return new Object[][]
                {
                        {salesPricelistName, constantsCompanyCurrency},
                        {salesPricelistOtherCurrencyName, constantsOtherCurrency}
                };
    }

    @Test(dataProvider = "pricelist combination")
    @Description("Create pricelist")
    public void createPricelistTest(String customerName, String currency) {
        commonPage.appItemByDataXmlId("sale.sale_menu_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("sale.sale_order_menu", "sale.menu_sale_order");
        ButtonElement.clickOnButton("Create");
        DropDownElement.selectSimpleDropDownValue(customerName);
        commonPage.clickOnLinkByClass("o_field_x2many_list_row_add");
        DropDownElement.selectSimpleDropDownValue();

        InputElement.setInputWithNameAttribute("name", orderName);

        DropDownElement.selectBaseDropDownValueForApp("Sales", "Catalog", "Pricelists");
        ButtonElement.clickOnButton("Create");
        InputElement.setInputWithNameAttribute("name", pricelistName);
        InputElement.setInputDropdown("currency_id", currency);
        ButtonElement.clickOnButton("Save");
        commonPage.clickBreadcrumbElement("Pricelists");
        Assert.assertTrue(commonPage.searchIfElementIsInTable(pricelistName));
    }
}

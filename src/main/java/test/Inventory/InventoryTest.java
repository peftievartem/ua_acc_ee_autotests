package test.Inventory;

import com.sun.source.tree.AssertTree;
import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.*;
import static test.Inventory.InventoryTestData.*;

public class InventoryTest extends BaseTest {

//    @DataProvider(name = "product combination")
//    public Object[][] productCombination() {
//        return new Object[][]{
//                {inventoryProductNameService, inventoryProductTypeService, inventoryProductCategoryService, null},
//                {inventoryProductNameAssets, inventoryProductTypeStockable, inventoryProductCategoryAssets, constantsAssetType},
//                {inventoryProductNameExpenses, inventoryProductTypeConsumable, inventoryProductCategoryExpenses, null},
//                {inventoryProductNameSaleable, inventoryProductTypeStockable, inventoryProductCategorySaleable, null}
//        };
//    }
// @Test(dataProvider = "product combination")

    @Test()
    @Description("Create TTN Report")
    public void createTtnReport() {
        commonPage.appItemByDataXmlId("stock.menu_stock_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("stock.menu_stock_warehouse_mgmt", "stock.all_picking");

        baseElementLocator.getWebElement("Xpath", "//a/span[contains(text(),'Delivery Orders')]").click();
        baseElementLocator.getWebElement("Xpath", "//*[contains(@class,'o_data_cell') and text()='outgoing shipment']").click();
        NotebookElement.selectNotebookTab("Additional Info");
        Assert.assertTrue(baseElementLocator.getWebElement("Xpath", "//*[contains(@name,'action_print_report')]").isDisplayed());
    }

//    @Test()
//    @Description("Verify new product creation")
//    public void createProductTest(String product, String type, String category, String assetCategory) {
//        commonPage.homePage().click();
//        commonPage.appItem("Inventory").click();
//        DropDownElement.selectBaseDropDownValue("Master Data", "Products");
//        ButtonElement.clickOnButton("Create");
//        InputElement.setInputWithNameAttribute("name", product);
//        CheckBoxElement.selectCheckbox("can_be_expensed");
//        DropDownElement.dropDownSelectOption("type", type);
//        InputElement.setInputDropdown("categ_id", category);
//        InputElement.setInputDropdown("sort_id", inventoryProductSort);
//        InputElement.setInputWithNameAttribute("default_code", constantsReferenceName);
//        InputElement.setInputMonetary("list_price", "5000");
//        TabElement.mainContentTab("Invoicing");
//        ButtonElement.clickOnInputTagDeleteButton("taxes_id");
//        ButtonElement.clickOnInputTagDeleteButton("supplier_taxes_id");
//
//
//        ButtonElement.clickOnButton("Save");
//        Assert.assertTrue(employeePage.getBreadcrumbName().contains(product));
//    }
}

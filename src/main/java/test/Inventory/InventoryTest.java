package test.Inventory;

import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.*;

public class InventoryTest extends BaseTest {

    @Test()
    @Description("Create TTN Report")
    public void createTtnReport() {
        commonPage.appItemByDataXmlId("stock.menu_stock_root").click();
        commonPage.selectPageInTopBarMenuByDataXmlId("stock.menu_stock_warehouse_mgmt", "stock.all_picking");

        baseElementLocator.getWebElement("Xpath", "//a/span[contains(text(),'Delivery Orders')]").click();
        baseElementLocator.getWebElement("Xpath", "//*[contains(@class,'o_data_cell') and text()='outgoing shipment']").click();
        NotebookElement.selectNotebookTab(additionalInfo);
        Assert.assertTrue(baseElementLocator.getWebElement("Xpath", "//*[contains(@name,'action_print_report')]").isDisplayed());
    }
}

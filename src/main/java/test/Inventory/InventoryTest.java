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
    public void createTtnReport() throws InterruptedException {
        commonPage.appItemByDataXmlId("stock.menu_stock_root").click();
        commonPage.waitForPageToLoad();

        baseElementLocator.getWebElement("Xpath", "//a/span[contains(text(),'" + deliveryOrders + "')]").click();
        baseElementLocator.getWebElement("Xpath", "//*[contains(@class,'o_data_cell') and text()='outgoing shipment']").click();
        NotebookElement.selectNotebookTab(additionalInfo);
        Assert.assertNotNull(baseElementLocator.getWebElement("Xpath", "//*[contains(@name,'action_print_report')]"));
    }
}

package test.Inventory;

import io.qameta.allure.Description;
import com.selferp.utils.elements.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class InventoryTest extends BaseTest {

    @Test()
    @Description("Create TTN Report")
    public void createTtnReport() throws InterruptedException {
        commonPage.appItemByDataXmlId("stock.menu_stock_root").click();
        commonPage.waitForPageToLoad();

        baseElementLocator.getWebElement("Xpath", "//a/span[contains(text(),'" + deliveryOrders + "')]").click();
        baseElementLocator.getWebElement("Xpath", "//*[contains(@class,'o_data_cell') and contains(@name,'partner_id')][1]").click();
        NotebookElement.selectNotebookTab(additionalInfo);
        Assert.assertNotNull(baseElementLocator.getWebElement("Xpath", "//*[contains(@name,'action_print_report')]"));
    }
}

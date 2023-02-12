package test.Inventory;

import io.qameta.allure.Description;
import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.DropDownElement;
import com.self.utils.elements.InputElement;
import com.self.utils.elements.RadioButtonElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static test.Inventory.InventoryTestData.inventoryAdjustmentName;
import static test.Inventory.InventoryTestData.inventoryProductNameSaleable;

public class InventoryAdjustmentTest extends BaseTest {
    @Test
    @Description("Create new inventory adjustment")
    public void createInventoryAdjustment() {
        commonPage.appItem("Inventory").click();
        DropDownElement.selectBaseDropDownValueForApp("Inventory", "Operations", "Inventory Adjustments");
        ButtonElement.clickOnButton("Create");
        InputElement.setInputWithNameAttribute("name", inventoryAdjustmentName);
        RadioButtonElement.selectRadioButton("filter", "Select products manually");
        InputElement.setInputForDateTimePicker("accounting_date", "accounting_date", commonPage.getDate(0));
        ButtonElement.clickOnButton("Start Inventory");
        commonPage.clickAddTableItem();
        InputElement.setInputDropdown("product_id", inventoryProductNameSaleable);
        InputElement.setInputWithNameAttribute("product_qty", "1");
        ButtonElement.clickOnButton("Validate Inventory");
        ButtonElement.clickOnButton("Save");
        commonPage.clickBreadcrumbElement("Inventory Adjustments");
        Assert.assertTrue(commonPage.isElementInTable(inventoryAdjustmentName));
    }
}

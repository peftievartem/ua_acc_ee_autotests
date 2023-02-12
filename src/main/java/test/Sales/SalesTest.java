package test.Sales;

import io.qameta.allure.Description;
import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.DropDownElement;
import com.self.utils.elements.InputElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.self.utils.Constants.*;
import static test.Employees.EmployeesTestData.employeesEmployeeName;
import static test.Inventory.InventoryTestData.inventoryProductNameSaleable;
import static test.Sales.SalesTestData.salesPricelistName;
import static test.Sales.SalesTestData.salesPricelistOtherCurrencyName;

public class SalesTest extends BaseTest {
    @DataProvider(name = "pricelist")
    public Object[][] pricelist() {return new Object[][]{{salesPricelistName, constantsCompanyCurrency}, {salesPricelistOtherCurrencyName, constantsOtherCurrency}};}

    @Test(dataProvider = "pricelist")
    @Description("Verify sales order creation")
    public void createSalesOrderTest(String pricelist, String currency) {
        commonPage.sidebarItem("Sales").click();
        DropDownElement.selectBaseDropDownValueForApp("Sales", "Orders", "Orders");
        ButtonElement.clickOnButton("Create");
        InputElement.setInputDropdown("partner_id", employeesEmployeeName);
        InputElement.setInputDropdown("pricelist_id", pricelist);
        commonPage.clickAddTableItem();
        InputElement.setInputDropdown("product_id", inventoryProductNameSaleable);
        InputElement.setInputWithNameAttribute("note", "Test Note");
        ButtonElement.clickOnButton("Save");
        ButtonElement.clickOnButton("Confirm Sale");
        ButtonElement.clickOnButtonBox("Delivery");
        ButtonElement.clickOnButton("Validate");
        ButtonElement.clickOnModalButton("Apply");
        commonPage.clickBreadcrumbElementByIndex(2);
        ButtonElement.clickOnButton("Create Invoice");
        ButtonElement.clickOnModalButton("Create and View Invoices");
        ButtonElement.clickOnButton("Validate");
        ButtonElement.clickOnButton("Register Payment");
        InputElement.setInputDropdownWithoutButton("currency_id", currency);
        InputElement.setInputSelect(constantsJournalPaymentName);
        ButtonElement.clickOnModalButton("Validate");
        Assert.assertTrue(ButtonElement.getButtonElement("Print Invoice").isDisplayed(), "Verifying that invoice has been created and can be printed");
    }
}

package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.CommonMethods;
import com.self.utils.World;
import com.self.utils.elements.BaseElementLocator;
import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.InputElement;
import com.self.utils.elements.NotebookElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.self.utils.Constants.APPS_URL;
import static com.self.utils.Constants.BASE_URL;
import static java.lang.Thread.sleep;

public class CommonPage extends BasePage {

    CommonMethods commonMethods = new CommonMethods();
    BaseElementLocator baseElementLocator = new BaseElementLocator();
    private static Logger LOG = LoggerFactory.getLogger(CommonPage.class);

    @Inject
    public CommonPage(World world) {
        super(world);
    }

    private final String sidebarPath = "sidebar";
    private final String appElementPath = "//a[contains(@class,'%s')]";
    private final String elementPathByDataXmlId = "//*[contains(@data-menu-xmlid,'%s')]";

    private final String navBarPath = "//nav[contains(@class,'navbar-default')]";
    private final String topbarNamePath = "//span[@class='oe_topbar_name']";
    private final String tableElementPath = "//table/descendant::*[contains(@class,'o_data_cell')]";
    private final String tableElementNamePath = "//*[contains(@class,'o_data_cell') and text()='%s']";
    private final String modalWarningPath = "//*[@class='modal-content']/descendant::*[contains(@class,'dialog_warning')]";
    private final String baseBreadcrumbNamePath = "//*[contains(@class,'breadcrumb')]/li[contains(@class,'active')]";
    private final String breadcrumbElementPath = "//*[@class='breadcrumb']/descendant::*[text()='%s']";
    private final String breadcrumbElementByIndex = "//*[@class='breadcrumb']/li[%s]";
    private final String searchInputPath = "o_searchview_input";
    private final String searchViewFacetPath = "//*[@class='o_searchview_facet']";
    private final String searchViewFacetCloseIconPath = "//*[contains(@class,'facet_remove')]";
    private final String baseNoContentTextPath = "//*[contains(@class,'oe_view_nocontent')]/p";
    private final String addTableItemPath = "//table/descendant::*[text()='Add an item']";
    private final String linkItemPath = "//a[text()='%s']";
    private final String linkClassItemPath = "//a[contains(@class,'%s']";
    private final String kanbanRecordPath = "//div[contains(@class,'o_has_icon o_kanban_record')]";
    private final String kanbanRecordToggleButtonPath = "//*[contains(@class,'o_kanban_manage_toggle_button')]";
    private final String kanbanActionMenuItemPath = "//*[contains(@class,'oe_kanban_action') and text()='%s']";
    private final String filterDropDownPath = "//button[@data-toggle]/span[@class='fa fa-filter']";
    private final String dropdownMenuOptionPath = "//ul[contains(@class,'dropdown-menu')]/descendant::*[text()='%s']";
    private final String tableElement = "//tbody/descendant::th[text()='%s']";

    public WebElement appItem(String itemName) {
        return baseElementLocator.getWebElement("Xpath", String.format(appElementPath, itemName));
    }

    public WebElement appItemByDataXmlId(String itemName) {
        return baseElementLocator.getWebElement("Xpath", String.format(elementPathByDataXmlId, itemName));
    }

    public WebElement homePage() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", "a[@class='o_menu_toggle']");
    }

    public void selectPageInTopBarMenuByDataXmlId(String topLevel, String secondLevel){
        baseElementLocator.getWebElement("Xpath", String.format(elementPathByDataXmlId, topLevel)).click();
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(elementPathByDataXmlId, secondLevel)).click();
        commonMethods.waitForPageToLoad();

    }

    public WebElement getSidebarPath() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Id", sidebarPath);
    }

    public String getSearchInputPath() {
        return searchInputPath;
    }

    public String getTableElementPath() {
        return tableElementPath;
    }

    public String getSearchViewFacetPath() {
        return searchViewFacetPath;
    }

    public String getSearchViewFacetCloseIconPath() {
        return searchViewFacetCloseIconPath;
    }

    public WebElement getTopbarNamePath() {
        commonMethods.waitForPageToLoad();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return baseElementLocator.getWebElement("Xpath", topbarNamePath);
    }

    public boolean isElementInTable(String elementName) {
        commonMethods.waitForPageToLoad();
        for (WebElement cell : baseElementLocator.getListWebElements("Xpath", tableElementPath)) {
            if (cell.getText().equals(elementName))
                return true;
        }
        return false;
    }

    public String getModalWarningPath() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", modalWarningPath).getText();
    }

    public String getBreadcrumbName() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", baseBreadcrumbNamePath).getText();
    }

    public String getNoContentText() {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", baseNoContentTextPath).getText();
    }

    public void clickBreadcrumbElement(String elementName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath",
                String.format(breadcrumbElementPath, elementName)));
    }

    public void clickBreadcrumbElementByIndex(int index) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(breadcrumbElementByIndex, index)));
    }

    public boolean searchIfElementIsInTable(String elementName) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithClassAttribute(searchInputPath, elementName);
        baseElementLocator.getWebElement("ClassName", searchInputPath).sendKeys(Keys.chord(Keys.ENTER));
        try {
            commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath", tableElementPath));
        } catch (Exception ignore) {
        }
        return commonMethods.isPresent(baseElementLocator.getWebElement("Xpath", tableElementPath));
    }

    public boolean searchIfElementIsKanbanCard(String elementName) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithClassAttribute(searchInputPath, elementName);
        baseElementLocator.getWebElement("ClassName", searchInputPath).sendKeys(Keys.chord(Keys.ENTER));
        try {
            commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath", kanbanRecordPath));
        } catch (Exception ignore) {
        }
        commonMethods.waitForPageToLoad();
        return commonMethods.isPresent(baseElementLocator.getWebElement("Xpath",kanbanRecordPath));
    }

    public void removeFacet() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", searchViewFacetCloseIconPath));
    }

    public void selectTableCell(String cellName) {
        commonMethods.waitForPageToLoad();
        if (commonMethods.isPresent(baseElementLocator.getWebElement("Xpath", searchViewFacetPath))) {
            commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", searchViewFacetCloseIconPath));
        }
        InputElement.setInputWithClassAttribute(searchInputPath, cellName);
        baseElementLocator.getWebElement("ClassName", searchInputPath).sendKeys(Keys.chord(Keys.ENTER));
        try {
            commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath", tableElementPath));
        } catch (Exception ignore) {
        }
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", tableElementPath));
    }

    public void selectTableCellWithoutFacet(String cellName) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithClassAttribute(searchInputPath, cellName);
        baseElementLocator.getWebElement("ClassName", searchInputPath).sendKeys(Keys.chord(Keys.ENTER));
        try {
            commonMethods.waitUntilElementIsDisappeared(baseElementLocator.getWebElement("Xpath", tableElementPath));
        } catch (Exception ignore) {
        }
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", tableElementPath));
    }

    public void clickAddTableItem() {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", addTableItemPath));
    }

    public void clickOnLink(String linkName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(linkItemPath, linkName)));
    }

    public void clickOnLinkByClass(String linkClass) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(linkClassItemPath, linkClass)));
    }

    public WebElement getElementByXpath(String xpath) throws InterruptedException {
        this.waitForPageToLoad();
        return baseElementLocator.getWebElement("Xpath", xpath);
    }

    public void clickOnLinkByXpath(String linkXpath) throws InterruptedException {
        waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", linkXpath));
    }

    public void selectKanbanAction(String actionName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", kanbanRecordToggleButtonPath));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(kanbanActionMenuItemPath, actionName)));
    }

    public void selectEntities(String entityOption) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", filterDropDownPath));
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(dropdownMenuOptionPath, entityOption)));
    }

    public String getDate(int monthDifference) {
        Format f = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = f. format(new Date());
        String[] date = strDate.split("/");
        int resultMonth = Integer.parseInt(date[0]) - monthDifference;
        int resultYear = Integer.parseInt(date[2]);
        if(resultMonth < 1) {
            resultMonth = 12 + resultMonth;
            resultYear = resultYear - 1;
        }
        if(resultMonth < 10) {
            return "0" + resultMonth + "/01" + "/" + resultYear;
        } else {
            return resultMonth + "/01" + "/" + resultYear;
        }
    }

    public String getElementWithNameAttributeText(String nameAttribute) {
        commonMethods.waitForPageToLoad();
        return baseElementLocator.getWebElement("Name", nameAttribute).getText();
    }

    public void selectTableElementName(String tableElementName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(tableElementNamePath, tableElementName)));
    }

    public void selectTableElement(int tableElementIndex) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(tableElement, tableElementIndex)));
    }

    public String getWebElementTextByName(String name) {
        return baseElementLocator.getWebElement("Name", name).getText();
    }

    public void waitForPageToLoad() throws InterruptedException {
        commonMethods.waitForPageToLoad();
        sleep(1000);
    }

    public void gotoAppsPage() {
        World.driver.get(APPS_URL);
    }
}

package test;

import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.InputElement;
import com.self.utils.elements.RadioButtonElement;
import io.qameta.allure.Description;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.self.utils.Constants.*;

public class PrepareTests extends BaseTest {

    @Test(priority = 1)
    @Description("Correct some parameters in services")
    public void testSaleReport1() throws InterruptedException {
        commonPage.gotoAppsPage();
        commonPage.clickOnLinkByXpath("//a[@data-menu-xmlid='sale.sale_menu_root']");
        commonPage.selectPageInTopBarMenuByDataXmlId("sale.product_menu_catalog", "sale.menu_product_template_action");
        InputElement.setInput("//input[@class='o_searchview_input']", constantService1);
        commonPage.waitForPageToLoad();
        commonPage.getElementByXpath("//input[@class='o_searchview_input']").sendKeys(Keys.chord(Keys.ENTER));

//
        commonPage.clickOnLinkByXpath("//*[@class='o_kanban_record_title']/span[contains(text(), '" + constantService1 + "')]");
        commonPage.clickOnLinkByXpath("//div[@class='o_notebook_headers']/descendant::a[@name='purchase']");
        RadioButtonElement.selectRadioButtonByDataValue("purchase");
        ButtonElement.clickOnButtonByClass("o_form_button_save");
    }
}

package test.vat;

import com.selferp.utils.elements.ButtonElement;
import com.selferp.utils.elements.InputElement;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.BaseTest;

import static com.selferp.utils.Constants.*;

public class PointOfSaleTest extends BaseTest {

    @Test(priority = 1)
    @Description("VAT Point of Sale")
    public void testVatByOrder() throws InterruptedException {

        // open session
        commonPage.gotoAppsPage();
        commonPage.appItemByDataXmlId("point_of_sale.menu_point_root").click();
        commonPage.waitForPageToLoad();
        ButtonElement.clickOnButtonXpath("(//button[@name='open_ui'])[1]");
        if (commonPage.checkElementByXpath("//input[@class='pos-input']")) {
            InputElement.setInput("//input[@class='pos-input']", "500");
        } else {
            commonPage.clickOnLinkByXpath("//div[(contains(@class, 'pos-topheader'))]/descendant::div[(contains(@class, 'header-button'))]");
            float allCash = commonPage.priceToFloat(commonPage.getElementByXpath("//td[(contains(@class, 'warning'))]").getText());
            InputElement.setInput("//input[@class='pos-input']", String.valueOf(allCash*-1));
            commonPage.clickOnLinkByXpath("//div[(contains(@class, 'close-pos-popup'))]/footer/div[@class='button highlight']");
            ButtonElement.clickOnButtonXpath("(//button[@name='open_ui'])[1]");
            InputElement.setInput("//input[@class='pos-input']", "500");
        }
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'opening-cash-control'))]/footer/div[@class='button']");

        float price1 = commonPage.priceToFloat(commonPage.getElementByXpath("(//article[(contains(@class, 'product'))][1])/descendant::span[@class='price-tag']").getText());
        float price2 = commonPage.priceToFloat(commonPage.getElementByXpath("(//article[(contains(@class, 'product'))][2])/descendant::span[@class='price-tag']").getText());

        // buy products and close session
        commonPage.clickOnLinkByXpath("(//article[(contains(@class, 'product'))])[1]");
        commonPage.clickOnLinkByXpath("(//article[(contains(@class, 'product'))])[2]");
        commonPage.clickOnLinkByXpath("(//article[(contains(@class, 'product'))])[2]");
        ButtonElement.clickOnButtonXpath("//button[@class='button pay validation']");
        commonPage.clickOnLinkByXpath("(//div[@class='button paymentmethod'])[1]");
        ButtonElement.clickOnButtonXpath("//div[(contains(@class, 'validation'))]");
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'pos-topheader'))]/descendant::div[(contains(@class, 'header-button'))]");
        InputElement.setInput("//input[@class='pos-input']", String.valueOf(500+price1+price2*2));
        commonPage.clickOnLinkByXpath("//div[(contains(@class, 'close-pos-popup'))]/footer/div[@class='button highlight']");

        //create and check vat
        commonPage.selectPageInTopBarMenuByDataXmlId("point_of_sale.menu_point_of_sale", "point_of_sale.menu_pos_session_all");
        commonPage.clickOnLinkByXpath("//td[@name='name'][1]");
        ButtonElement.clickOnButtonXpath("//button[@data-hotkey='u']");
        commonPage.clickOnLinkByXpath("//span[(contains(@class, 'dropdown-item')) and (contains(text(), '" + stvorytyNakladnu + "'))]");
        commonPage.selectPageInTopBarMenuByDataXmlId("point_of_sale.menu_point_of_sale", "point_of_sale.menu_pos_session_all");
        Assert.assertNotNull(commonPage.getElementByXpath("(//tr[@class='o_data_row'])[1]/td[@name='vat_state']/descendant::span[(contains(@class, 'text-bg-success'))]"));
        commonPage.clickOnLinkByXpath("//td[@name='name'][1]");
        ButtonElement.clickOnButtonXpath("//button[@name='action_show_vat_invoice']");

        float vat = commonPage.priceToFloat(commonPage.getElementByXpath("//div[(contains(@class, 'total_container'))]/descendant::span[@name='amount_total']").getText());
        Assert.assertEquals(vat, price1+price2*2);
    }
}

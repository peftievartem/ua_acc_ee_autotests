package test;

import io.qameta.allure.Description;
import com.self.utils.elements.*;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.self.utils.Constants.*;

public class RemoveDataTest extends BaseTest {

    @Test(dataProvider = "pricelist name")
    @Description("Archive pricelist")
    public void archivePricelist(String pricelistName) {
        commonPage.appItem("Sales").click();
        DropDownElement.selectBaseDropDownValueForApp("Sales", "Catalog", "Pricelists");
        InputElement.setInputWithClassAttribute("o_searchview_input", pricelistName);
        baseElementLocator.getWebElement("ClassName", "o_searchview_input").sendKeys(Keys.chord(Keys.ENTER));
        CheckBoxElement.selectTableHeadCheckbox();
        ButtonElement.clickOnButton("Action");
        DropDownElement.selectSimpleDropDownValue("Archive");
        Assert.assertEquals(commonPage.getNoContentText(), "Click to create a pricelist.", "Verifying pricelist has been archived");
    }

}

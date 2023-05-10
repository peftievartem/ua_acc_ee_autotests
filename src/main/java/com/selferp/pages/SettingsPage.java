package com.selferp.pages;

import com.selferp.inject.Inject;
import com.selferp.utils.World;
import com.selferp.utils.elements.InputElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SettingsPage extends CommonPage {

    @Inject
    public SettingsPage(World world) {
        super(world);
    }

    private static final Logger LOG = LoggerFactory.getLogger(SettingsPage.class);
    private final String changePasswordField = "//*[@class='modal-content']/descendant::*[contains(@class,'data_cell') and text()='%s']/following-sibling::*[contains(@class,'required_modifier')]";

    public void setChangePasswordField(String login, String passwordValue) {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("Xpath", String.format(changePasswordField, login)).click();
        InputElement.setInputWithNameAttribute("new_passwd", passwordValue);
    }
}

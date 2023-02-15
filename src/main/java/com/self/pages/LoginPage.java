package com.self.pages;

import io.qameta.allure.Step;
import com.self.utils.World;
import com.self.utils.elements.ButtonElement;
import com.self.utils.elements.DropDownElement;
import com.self.utils.elements.InputElement;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends CommonPage {

    private static Logger LOG = LoggerFactory.getLogger(LoginPage.class);

    SettingsPage settingsPage = new SettingsPage(world);

    public LoginPage(World world) {
        super(world);
    }

    @Step("Set Login value")
    public void setLoginInputField(String loginValue) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithNameAttribute("login", loginValue);
        LOG.info("Entered login");
    }

    @Step("Set password value")
    public void setPasswordInputField(String passwordValue) {
        commonMethods.waitForPageToLoad();
        InputElement.setInputWithNameAttribute("password", passwordValue);
        LOG.info("Entered password");
    }

    @Step("Click login button")
    public void clickLoginButton() {
        commonMethods.waitForPageToLoad();
        ButtonElement.clickOnButtonWithTypeAttribute("submit");
        LOG.info("Clicked login button");
    }

    public void loginMethod(String userName, String password) {
        setLoginInputField(userName);
        setPasswordInputField(password);
        clickLoginButton();
        LOG.info("Logged in application with user - " + userName);
    }

    public void logoutMethod() {
        commonMethods.waitForPageToLoad();
        baseElementLocator.getWebElement("ClassName", "o_user_menu").click();
        baseElementLocator.getWebElement("Xpath", "//a[@data-menu='logout']").click();
    }
}

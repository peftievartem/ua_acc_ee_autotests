package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.CommonMethods;
import com.self.utils.World;
import com.self.utils.elements.BaseElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseSelectorPage extends CommonPage {

    static BaseElementLocator baseElementLocator = new BaseElementLocator();

    static CommonMethods commonMethods = new CommonMethods();

    private static final String baseTabPath = "//a[contains(text(),'%s')]";
    private static final String mainContentTabPath = "//*[@class='o_main_content']/descendant::a[contains(text(),'%s')]";

    @Inject
    public DatabaseSelectorPage(World world) {
        super(world);
    }

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseSelectorPage.class);

    public void selectDatabase(String databaseName) {
        selectTab(databaseName);
        LOG.info("Clicked on database - " + databaseName);
    }

    public static void selectTab(String tabName) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(baseTabPath, tabName)));
    }
}

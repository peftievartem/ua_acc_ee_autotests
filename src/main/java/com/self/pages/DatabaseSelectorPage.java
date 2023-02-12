package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.World;
import com.self.utils.elements.TabElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseSelectorPage extends CommonPage {

    @Inject
    public DatabaseSelectorPage(World world) {
        super(world);
    }

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseSelectorPage.class);

    public void selectDatabase(String databaseName) {
        TabElement.selectTab(databaseName);
        LOG.info("Clicked on database - " + databaseName);
    }
}

package com.selferp.pages;

import com.selferp.utils.World;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BasePage {
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    protected World world;

    public BasePage(World world) {
        this.world = world;
        LOG.debug("Initializing Elements of " + this.getClass() + " Class");
        PageFactory.initElements(World.driver, this);
    }
}

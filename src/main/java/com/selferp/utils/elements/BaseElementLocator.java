package com.selferp.utils.elements;

import com.selferp.inject.Inject;
import com.selferp.utils.World;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BaseElementLocator {
    @Inject
    World world;

    public WebElement getWebElement(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "Xpath":
                return world.driver.findElement(By.xpath(identifierValue));
            case "CSS":
                return world.driver.findElement(By.cssSelector(identifierValue));
            case "Id":
                return world.driver.findElement(By.id(identifierValue));
            case "Name":
                return world.driver.findElement(By.name(identifierValue));
            case "ClassName":
                return world.driver.findElement(By.className(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebElements(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "Xpath":
                return world.driver.findElements(By.xpath(identifierValue));
            case "CSS":
                return world.driver.findElements(By.cssSelector(identifierValue));
            case "Name":
                return world.driver.findElements(By.name(identifierValue));
            default:
                return null;
        }
    }
}

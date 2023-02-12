package com.self.pages;

import com.self.inject.Inject;
import com.self.utils.World;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LeavesPage extends CommonPage {
    @Inject
    public LeavesPage(World world) {
        super(world);
    }

    private final String weekdayPath = "//div[contains(@class,'fc-week')][2]//thead//td[not (contains(@class,'fc-sun')) and not (contains(@class,'fc-sat')) and not (@class='fc-week-number')]";
    private final String employeeEvent = "//td[@class='fc-event-container']/descendant::*[contains(@class,'display_name') and (contains(text(),'%s'))]";

    public void selectRandomWeekdayFromGrid() {
        List<WebElement> weekdays = baseElementLocator.getListWebElements("Xpath", weekdayPath);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        commonMethods.waitAndClickElement(weekdays.get(random.nextInt(1, 5)));
    }

    public void selectEmployeeEvent(String name) {
        commonMethods.waitForPageToLoad();
        commonMethods.waitAndClickElement(baseElementLocator.getWebElement("Xpath", String.format(employeeEvent, name)));
    }

    public boolean isEmployeeEventDisplayed(String name) {
        commonMethods.waitForPageToLoad();
        boolean flag = false;
        try {
             flag = baseElementLocator.getWebElement("Xpath", String.format(employeeEvent, name)).isDisplayed();
        } catch (Exception ignored){}
        return flag;
    }
}

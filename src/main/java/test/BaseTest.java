package test;

import com.self.inject.Inject;
import com.self.pages.*;
import com.self.utils.World;
import com.self.utils.elements.BaseElementLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.self.utils.Constants.*;

public class BaseTest {
    @Inject
    World world;

    protected CommonPage commonPage = new CommonPage(world);
    protected LoginPage loginPage = new LoginPage(world);
    protected ContactsPage contactsPage = new ContactsPage(world);
    protected SettingsPage settingsPage = new SettingsPage(world);
    protected AccountingPage accountingPage = new AccountingPage(world);
    protected EmployeePage employeePage = new EmployeePage(world);
    protected PayrollPage payrollPage = new PayrollPage(world);
    protected LeavesPage leavesPage = new LeavesPage(world);
    DatabaseSelectorPage databaseSelectorPage = new DatabaseSelectorPage(world);
    protected BaseElementLocator baseElementLocator = new BaseElementLocator();

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Random number - " + RANDOM_NUM);
    }

    @BeforeClass
    public void beforeClass() {
        World.driver.get(BASE_URL);
        databaseSelectorPage.selectDatabase(DATABASE_NAME);
        loginPage.loginMethod(USER_LOGIN_LOCAL_ADMIN, USER_PASSWORD);
    }

    @AfterClass
    public void afterClass(){
        loginPage.logoutMethod();
    }

    @AfterSuite
    public void afterSuite() {
        World.driver.quit();
    }
}

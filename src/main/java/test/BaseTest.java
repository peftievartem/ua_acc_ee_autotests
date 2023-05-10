package test;

import com.selferp.inject.Inject;
import com.selferp.pages.*;
import com.selferp.pages.elements.*;
import com.selferp.utils.World;
import com.selferp.utils.elements.BaseElementLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.selferp.utils.Constants.*;

public class BaseTest {
    @Inject
    World world;

    protected CommonPage commonPage = new CommonPage(world);
    protected CompanyPageElements companyPageElements = new CompanyPageElements(world);
    protected AccountingPageElements accountingPageElements = new AccountingPageElements(world);
    protected SalesPageElements salesPageElements = new SalesPageElements(world);

    protected LoginPage loginPage = new LoginPage(world);
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

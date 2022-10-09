import com.aventstack.extentreports.Status;
import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.PageInitManager;

import java.lang.reflect.Method;

import static extents.ExtentTestManager.getTest;
import static extents.ExtentTestManager.startTest;

public class Regression_Test_02_Sort_Products extends BaseTest {

    private AndroidDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    private final String username = "standard_user";
    private final String password = "secret_sauce";
    private final String sortNameAToZ = "Name (A to Z)";
    private final String sortNameZToA = "Name (Z to A)";
    private final String priceLowToHigh = "Price (low to high)";
    private final String priceHighToLow = "Price (high to low)";

    @BeforeMethod
    public void SetUp() {
        driver = initDriver();

        loginPage = PageInitManager.getInstance().getLoginPage(driver);
        homePage = Pre_Conditions.login(driver, username, password, loginPage);
    }

    @Test(description = "Verify that user can sort name from A to Z")
    public void TC_01_Login_With_Valid_Credentials(Method method) {
        String methodName = method.getName();

        startTest(methodName, "User can sort name from A to Z");
        getTest().log(Status.INFO, methodName + " - Step 01: Click to 'Sort' icon");
        homePage.clickToSortIcon(driver);

        getTest().log(Status.INFO, methodName + " - Step 02: Click to " + sortNameAToZ);
        homePage.clickToSortCriteriaButton(driver, sortNameAToZ);

        getTest().log(Status.INFO, methodName + " - Step 03: Verify that all products name are displayed from a to z");
        Assert.assertTrue(homePage.isProductNameSortedCorrectly(driver));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        closeDriver(method.getName());
    }
}

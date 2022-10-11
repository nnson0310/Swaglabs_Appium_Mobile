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
    private final String nameAToZ = "Name (A to Z)";
    private final String nameZToA = "Name (Z to A)";
    private final String priceLowToHigh = "Price (low to high)";
    private final String priceHighToLow = "Price (high to low)";

    @BeforeMethod
    public void SetUp() {
        driver = initDriver();

        loginPage = PageInitManager.getInstance().getLoginPage(driver);
        homePage = Pre_Conditions.login(driver, username, password, loginPage);
    }

//    @Test(description = "Verify that user can sort product name from A to Z")
//    public void TC_01_Sort_Product_Name_Ascending(Method method) {
//        String methodName = method.getName();
//
//        startTest(methodName, "User can sort name from A to Z");
//        getTest().log(Status.INFO, methodName + " - Step 01: Click to 'Sort' icon");
//        homePage.clickToSortIcon(driver);
//
//        getTest().log(Status.INFO, methodName + " - Step 02: Click to sort = " + nameAToZ);
//        homePage.clickToSortCriteriaButton(driver, nameAToZ);
//
//        getTest().log(Status.INFO, methodName + " - Step 03: Verify that all products name are displayed from a to z");
//        Assert.assertTrue(homePage.isProductNameSortedCorrectly(driver, nameAToZ));
//    }
//
//    @Test(description = "Verify that user can sort product name from Z to A")
//    public void TC_02_Sort_Product_Name_Descending(Method method) {
//        String methodName = method.getName();
//
//        startTest(methodName, "User can sort name from A to Z");
//        getTest().log(Status.INFO, methodName + " - Step 01: Click to 'Sort' icon");
//        homePage.clickToSortIcon(driver);
//
//        getTest().log(Status.INFO, methodName + " - Step 02: Click to sort = " + nameZToA);
//        homePage.clickToSortCriteriaButton(driver, nameZToA);
//
//        getTest().log(Status.INFO, methodName + " - Step 03: Verify that all products name are displayed from a to z");
//        Assert.assertTrue(homePage.isProductNameSortedCorrectly(driver, nameZToA));
//    }

    @Test(description = "Verify that user can sort product price from low to high")
    public void TC_03_Sort_Product_Price_Ascending(Method method) {
        String methodName = method.getName();

        startTest(methodName, "User can sort product name from low to high");
        getTest().log(Status.INFO, methodName + " - Step 01: Click to 'Sort' icon");
        homePage.clickToSortIcon(driver);

        getTest().log(Status.INFO, methodName + " - Step 02: Click to sort = " + priceLowToHigh);
        homePage.clickToSortCriteriaButton(driver, priceLowToHigh);

        getTest().log(Status.INFO, methodName + " - Step 03: Verify that all product prices are displayed from low to high");
        Assert.assertTrue(homePage.isProductPriceSortedCorrectly(driver, priceLowToHigh));
    }

    @Test(description = "Verify that user can sort product price from high to low")
    public void TC_04_Sort_Product_Price_Descending(Method method) {
        String methodName = method.getName();

        startTest(methodName, "User can sort product price from high to low");
        getTest().log(Status.INFO, methodName + " - Step 01: Click to 'Sort' icon");
        homePage.clickToSortIcon(driver);

        getTest().log(Status.INFO, methodName + " - Step 02: Click to sort = " + priceHighToLow);
        homePage.clickToSortCriteriaButton(driver, priceHighToLow);

        getTest().log(Status.INFO, methodName + " - Step 03: Verify that all product prices are displayed from high to low");
        Assert.assertTrue(homePage.isProductPriceSortedCorrectly(driver, priceHighToLow));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        closeDriver(method.getName());
    }
}

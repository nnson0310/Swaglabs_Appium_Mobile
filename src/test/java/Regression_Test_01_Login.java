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

public class Regression_Test_01_Login extends BaseTest {

    private AndroidDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    private final String username = "standard_user";
    private final String password = "secret_sauce";
    private final String invalidPassword = "secret_sauce123";
    private final String errorMsg = "Username and password do not match any user in this service.";

    @BeforeMethod
    public void SetUp() {
        driver = initDriver();
        loginPage = PageInitManager.getInstance().getLoginPage(driver);
    }

    @Test(description = "Verify that user can login with valid credentials")
    public void TC_01_Login_With_Valid_Credentials(Method method) {
        String methodName = method.getName();

        startTest(methodName, "Login with valid credentials");
        getTest().log(Status.INFO, methodName + " - Step 01: Enter to username textbox with value = " + username);
        loginPage.enterToUsernameTextbox(driver, username);

        getTest().log(Status.INFO, methodName + " - Step 02: Enter to password textbox with value = " + password);
        loginPage.enterToPasswordTextbox(driver, password);

        getTest().log(Status.INFO, methodName + " - Step 03: Click to Login button");
        loginPage.clickToLoginButton(driver);
        homePage = PageInitManager.getInstance().getHomePage(driver);

        getTest().log(Status.INFO, methodName + " - Step 04: Verify that 'Products' header is displayed");
        Assert.assertTrue(homePage.isProductHeaderDisplayed(driver));
    }

    @Test(description = "Verify that error message will displayed when login with invalid password")
    public void TC_02_Login_With_Invalid_Password(Method method) {
        String methodName = method.getName();

        startTest(methodName, "Login with invalid password");
        getTest().log(Status.INFO, methodName + " - Step 01: Enter to username textbox with value = " + username);
        loginPage.enterToUsernameTextbox(driver, username);

        getTest().log(Status.INFO, methodName + " - Step 02: Enter to password textbox with value = " + invalidPassword);
        loginPage.enterToPasswordTextbox(driver, invalidPassword);

        getTest().log(Status.INFO, methodName + " - Step 03: Click to Login button");
        loginPage.clickToLoginButton(driver);

        getTest().log(Status.INFO, methodName + " - Step 04: Verify that error message  = '" + errorMsg + "' is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(driver, errorMsg));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method) {
        closeDriver(method.getName());
    }
}

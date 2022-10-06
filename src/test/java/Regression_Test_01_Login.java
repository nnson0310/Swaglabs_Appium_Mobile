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

    private final String email = "standard_user";
    private final String password = "secret_sauce";

    @BeforeMethod
    public void SetUp() {
        driver = initDriver();

        loginPage = PageInitManager.getInstance().getLoginPage(driver);
    }

    @Test(description = "Verify that admin can create new address for customer")
    public void TC_01_Login_With_Valid_Credentials(Method method) {
        String methodName = method.getName();

        startTest(methodName, "Login with valid credentials");
        getTest().log(Status.INFO, methodName + " - Step 01: Enter to email textbox with value = " + email);
        loginPage.enterToEmailTextbox(driver, email);

        getTest().log(Status.INFO, methodName + " - Step 02: Enter to password textbox with value = " + password);
        loginPage.enterToPasswordTextbox(driver, password);

        getTest().log(Status.INFO, methodName + " - Step 03: Click to Login button");
        loginPage.clickToLoginButton(driver);
        homePage = PageInitManager.getInstance().getHomePage(driver);

        getTest().log(Status.INFO, methodName + " - Step 04: Verify that 'Products' header is displayed");
        Assert.assertFalse(homePage.isProductHeaderDisplayed(driver));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }
}

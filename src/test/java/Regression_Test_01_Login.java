import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.PageInitManager;

public class Regression_Test_01_Login extends BaseTest {

    private AndroidDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    private final String email = "standard_user";
    private final String password = "secret_sauce";

    @BeforeMethod
    public void SetUp() {
        driver = getDriver();

        loginPage = PageInitManager.getInstance().getLoginPage(driver);
    }

    @Test
    public void TC_01_Login_With_Valid_Credentials() {
        loginPage.enterToEmailTextbox(driver, email);

        loginPage.enterToPasswordTextbox(driver, password);

        loginPage.clickToLoginButton(driver);
        homePage = PageInitManager.getInstance().getHomePage(driver);

        Assert.assertTrue(homePage.isProductHeaderDisplayed(driver));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }
}

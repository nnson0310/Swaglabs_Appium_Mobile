import commons.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.*;
import page_objects.LoginPage;
import page_objects.PageInitManager;

public class Regression_Test_01_Login extends BaseTest {

    private AndroidDriver driver;
    private LoginPage loginPage;

    private String email = "standard_user";
    private String password = "secret_sauce";

    @BeforeMethod
    public void SetUp() {
        driver = getDriver();

        loginPage = PageInitManager.getInstance().getLoginPage(driver);
    }

    @Test
    public void TC_01_Login_With_Valid_Credentials() {
        loginPage.enterToEmailTextbox(driver, email);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        closeDriver();
    }
}

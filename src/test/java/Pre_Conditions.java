import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import page_objects.HomePage;
import page_objects.LoginPage;
import page_objects.PageInitManager;

public class Pre_Conditions {
    public static HomePage login(AndroidDriver driver, String username, String password, LoginPage loginPage) {
        loginPage.enterToUsernameTextbox(driver, username);

        loginPage.enterToPasswordTextbox(driver, password);

        loginPage.clickToLoginButton(driver);
        HomePage homePage = PageInitManager.getInstance().getHomePage(driver);

        Assert.assertTrue(homePage.isProductHeaderDisplayed(driver));

        return PageInitManager.getInstance().getHomePage(driver);
    }
}

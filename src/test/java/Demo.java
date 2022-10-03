import commons.BaseTest;
import commons.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Demo extends BaseTest {

    private static AppiumDriver driver;

    @BeforeClass
    public static void SetUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid("emulator-5554");
        options.setPlatformName("Android");
        options.setAppActivity("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.MainActivity");
        options.setAutoGrantPermissions(true);
        options.setNoReset(true);

        driver = new AndroidDriver(new ServerManager().getServer(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public static void Test() {
        Assert.assertTrue(true);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}

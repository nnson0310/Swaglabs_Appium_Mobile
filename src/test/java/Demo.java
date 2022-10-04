import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.Immutable;
import commons.BaseTest;
import commons.DriverOptionManager;
import commons.ServerManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Collections;

public class Demo extends BaseTest {

    private static AppiumDriver driver;
    private AppiumDriverLocalService server;

    @BeforeClass
    public void SetUp() throws MalformedURLException {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        server = new ServerManager().getServer();

        driver = new AndroidDriver(server, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void Test() {
        AndroidDriver androidDriver = (AndroidDriver) driver;
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}

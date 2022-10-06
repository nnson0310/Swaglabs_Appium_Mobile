package commons;

import configurations.DriverOptionManager;
import configurations.ServerManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;

    public AndroidDriver initDriver() {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        AppiumDriverLocalService server = new ServerManager().getServer();

        driver = new AndroidDriver(server, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.longTimeout));

        return driver;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

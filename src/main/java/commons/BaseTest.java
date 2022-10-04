package commons;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.time.Duration;

public class BaseTest {

    private AndroidDriver driver;

    public AndroidDriver getDriver() {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        AppiumDriverLocalService server = new ServerManager().getServer();

        driver = new AndroidDriver(server, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.longTimeout));

        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

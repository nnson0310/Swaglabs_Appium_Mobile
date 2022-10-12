package commons;

import configurations.DriverOptionManager;
import configurations.ServerManager;
import helpers.MethodHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.time.Duration;

public class BaseTest {

    protected AndroidDriver driver;
    protected AppiumDriverLocalService server;

    public AndroidDriver initDriver() {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        server = new ServerManager().getServer();

        driver = new AndroidDriver(server, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.longTimeout));
        MethodHelper.startRecordVideo(driver);

        return driver;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void closeDriver(String testName) {
        if (driver != null) {
            MethodHelper.saveRecordVideo(driver, testName);
            driver.quit();
            server.stop();
        }
    }
}

package commons;

import configurations.DriverOptionManager;
import configurations.ServerManager;
import helpers.MethodHelper;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;

public class BaseTest {

    protected AndroidDriver driver;

    public AndroidDriver initDriver() {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        AppiumDriverLocalService server = new ServerManager().getServer();

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
        }
    }
}

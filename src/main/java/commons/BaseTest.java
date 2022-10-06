package commons;

import configurations.DriverOptionManager;
import configurations.ServerManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;

import static commons.GlobalConstants.projectPath;

public class BaseTest {

    protected AndroidDriver driver;

    public AndroidDriver initDriver() {
        UiAutomator2Options options = new DriverOptionManager().getOptions();
        AppiumDriverLocalService server = new ServerManager().getServer();

        driver = new AndroidDriver(server, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.longTimeout));
        driver.startRecordingScreen(
                new AndroidStartScreenRecordingOptions()
                        .withVideoSize("1280x720")
                        .withTimeLimit(Duration.ofSeconds(200))
        );

        return driver;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void closeDriverAndSaveVideo() {
        if (driver != null) {
            String video = driver.stopRecordingScreen();
            byte[] decode = Base64.getDecoder().decode(video);
            try {
                FileUtils.writeByteArrayToFile(new File(projectPath + File.separator + "record_videos" + File.separator + "android.mp4"), decode);
            } catch (IOException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}

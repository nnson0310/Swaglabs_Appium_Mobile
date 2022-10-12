package configurations;

import commons.GlobalConstants;
import helpers.ConfigHelper;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverOptionManager {
    private final ConfigHelper configHelper;

    public DriverOptionManager() {
        configHelper = ConfigHelper.getInstance();
    }

    public UiAutomator2Options getOptions( ){
        UiAutomator2Options options = new UiAutomator2Options();
        options.setUdid(configHelper.getProperty("udid"));
        options.setPlatformName(configHelper.getProperty("platformName"));
        options.setAppPackage(configHelper.getProperty("appPackage"));
        options.setAppActivity(configHelper.getProperty("appActivity"));
        options.setAutomationName(configHelper.getProperty("automationName"));
        options.setAutoGrantPermissions(true);
        options.setNoReset(true);
        options.setFullReset(false);

        return options;
    }
}

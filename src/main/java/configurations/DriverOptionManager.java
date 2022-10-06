package configurations;

import helpers.ConfigHelper;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverOptionManager {
    private UiAutomator2Options options;
    private ConfigHelper configHelper;

    public DriverOptionManager() {
        configHelper = ConfigHelper.getInstance();
    }

    public UiAutomator2Options getOptions( ){
        options = new UiAutomator2Options();
        options.setUdid(configHelper.getProperty("udid"));
        options.setPlatformName(configHelper.getProperty("platformName"));
        options.setAppPackage(configHelper.getProperty("appPackage"));
        options.setAppActivity(configHelper.getProperty("appActivity"));
        options.setAutomationName(configHelper.getProperty("automationName"));
        options.setAutoGrantPermissions(true);
        options.setNoReset(true);

        return options;
    }
}

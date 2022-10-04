package page_objects;

import io.appium.java_client.android.AndroidDriver;

public class PageInitManager {

    private static PageInitManager pageInitManager;

    public static PageInitManager getInstance() {
        if (pageInitManager == null) {
            return new PageInitManager();
        }
        return pageInitManager;
    }

    public LoginPage getLoginPage(AndroidDriver driver) {
        return new LoginPage(driver);
    }

    public HomePage getHomePage(AndroidDriver driver) { return new HomePage(driver); }
}

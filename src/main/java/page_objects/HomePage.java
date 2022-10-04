package page_objects;

import io.appium.java_client.android.AndroidDriver;
import page_interfaces.HomePageUI;

public class HomePage extends CommonPage {
    private AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isProductHeaderDisplayed(AndroidDriver driver) {
        waitForElementVisible(driver, HomePageUI.PRODUCT_HEADER_LABEL);
        return isElementDisplayed(driver, HomePageUI.PRODUCT_HEADER_LABEL);
    }
}

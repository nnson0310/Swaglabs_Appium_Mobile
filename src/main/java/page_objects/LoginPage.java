package page_objects;

import commons.BasePage;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {
    private AndroidDriver driver;
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(AndroidDriver driver, String email) {

    }
}

package page_objects;

import io.appium.java_client.android.AndroidDriver;
import page_interfaces.LoginPageUI;

public class LoginPage extends CommonPage {
    private AndroidDriver driver;
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(AndroidDriver driver, String email) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(AndroidDriver driver, String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton(AndroidDriver driver) {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}

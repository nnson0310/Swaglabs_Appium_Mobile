package page_objects;

import io.appium.java_client.android.AndroidDriver;
import page_interfaces.LoginPageUI;

public class LoginPage extends CommonPage {
    private AndroidDriver driver;
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterToUsernameTextbox(AndroidDriver driver, String username) {
        waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    public void enterToPasswordTextbox(AndroidDriver driver, String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton(AndroidDriver driver) {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }

    public boolean isErrorMessageDisplayed(AndroidDriver driver, String errorMessage) {
        waitForElementVisible(driver, LoginPageUI.LOGIN_ERROR_MESSAGE, errorMessage);
        return isElementDisplayed(driver, LoginPageUI.LOGIN_ERROR_MESSAGE, errorMessage);
    }
}

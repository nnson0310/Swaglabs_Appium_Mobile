package commons;

import org.openqa.selenium.WebDriver;

public class BaseTest {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void closeBrowserAndKillDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

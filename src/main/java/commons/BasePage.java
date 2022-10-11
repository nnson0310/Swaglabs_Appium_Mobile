package commons;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

/**
 * Define all methods of selenium API to interact with browser
 * PageObject will extend from this class
 *
 * @author Son
 */
public abstract class BasePage {

    private WebDriverWait explicitWait;

    private final long longTimeout = GlobalConstants.longTimeout;

    private final long shortTimeout = GlobalConstants.shortTimeout;

    protected String getAppiumStatus(AndroidDriver driver) {
        return driver.getStatus().toString();
    }

    protected String getChargeStatus(AndroidDriver driver) {
        return driver.getBatteryInfo().getState().toString();
    }

    protected String getScreenOrientation(AndroidDriver driver) {
        return driver.getOrientation().value();
    }

    protected void rotateScreenIntoLandscape(AndroidDriver driver) {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void rotateScreenInfoPortrait(AndroidDriver driver) {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected Location getGeoLocation(AndroidDriver driver) {
        return driver.location();
    }

    protected void setGeoLocation(AndroidDriver driver, double latitude, double longitude, double altitude) {
        driver.setLocation(new Location(latitude, longitude, altitude));
    }

    protected void startAppActivity(AndroidDriver driver, String packageName, String activityName) {
        driver.startActivity(new Activity(packageName, activityName));
    }

    protected String getCurrentAppActivity(AndroidDriver driver) {
        return driver.currentActivity();
    }

    protected String getCurrentPackage(AndroidDriver driver) {
        return driver.getCurrentPackage();
    }

    protected void installApp(AndroidDriver driver, String appPath) {
        driver.installApp(appPath);
    }

    protected Boolean isAppInstalled(AndroidDriver driver, String packageName) {
        return driver.isAppInstalled(packageName);
    }

    protected void runAppInBackground(AndroidDriver driver, int duration) {
        driver.runAppInBackground(Duration.ofSeconds(duration));
    }

    protected void activateApp(AndroidDriver driver, String packageName) {
        driver.activateApp(packageName);
    }

    protected void terminateApp(AndroidDriver driver, String packageName) {
        driver.terminateApp(packageName);
    }

    protected String getAppState(AndroidDriver driver, String packageName) {
        return driver.queryAppState(packageName).name();
    }

    private String getLocatorValue(String locator) {
        return locator.substring(locator.indexOf("=") + 1);
    }

    /**
     * Get Android Element locator strategies.
     * aid = AccessibilityId
     * uia2 = AndroidUiAutomator2
     * @param locator
     * @return
     */
    private By getByLocator(String locator) {
        By by = null;
        String trueLocator = locator.toLowerCase();

        if (trueLocator.startsWith("id=")) {
            by = AppiumBy.id(getLocatorValue(locator));
        } else if (trueLocator.startsWith("aid=")) {
            by = AppiumBy.accessibilityId(getLocatorValue(locator));
        } else if (trueLocator.startsWith("class")) {
            by = AppiumBy.className(getLocatorValue(locator));
        } else if (trueLocator.startsWith("name=")) {
            by = AppiumBy.name(getLocatorValue(locator));
        } else if (trueLocator.startsWith("xpath=")) {
            by = AppiumBy.xpath(getLocatorValue(locator));
        } else if (trueLocator.startsWith("img=")) {
            by = AppiumBy.image(getLocatorValue("img="));
        } else if (trueLocator.startsWith("uia2=")) {
            by = AppiumBy.androidUIAutomator(getLocatorValue("uia2="));
        } else {
            throw new RuntimeException("Locator type is invalid");
        }
        return by;
    }

    private String getDynamicXpath(String locator, String... dynamicValues) {
        if (locator.startsWith("Xpath") || locator.startsWith("xpath=") || locator.startsWith("XPATH=")) {
            locator = String.format(locator, (Object[]) dynamicValues);
        }
        return locator;
    }

    private WebElement getElement(AndroidDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    private WebElement getElement(AndroidDriver driver, String locator, String... dynamicXpath) {
        return driver.findElement(getByLocator(getDynamicXpath(locator, dynamicXpath)));
    }

    protected List<String> getTextOfAllElements(AndroidDriver driver, String locator) {
        List<WebElement> elements = getElements(driver, locator);
        List<String> elementText = new ArrayList<>();
        for(WebElement element: elements) {
            elementText.add(element.getText());
        }
        return elementText;
    }

    private List<WebElement> getElements(AndroidDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    private List<WebElement> getElements(AndroidDriver driver, String locator, String... dynamicXpath) {
        return driver.findElements(getByLocator(getDynamicXpath(locator, dynamicXpath)));
    }

    protected void clickToElement(AndroidDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    protected void clickToElement(AndroidDriver driver, String locator, String... dynamicXpath) {
        getElement(driver, locator, dynamicXpath).click();
    }

    protected void clearElementValue(AndroidDriver driver, String locator) {
        getElement(driver, locator).clear();
    }

    protected void clearElementValue(AndroidDriver driver, String locator, String... dynamicXpath) {
        getElement(driver, locator, dynamicXpath).clear();
    }

    protected void sendKeyToElement(AndroidDriver driver, String locator, String value) {
        getElement(driver, locator).sendKeys(value);
    }

    protected void sendKeyToElement(AndroidDriver driver, String locator, String value, String... dynamicXpath) {
        getElement(driver, locator, dynamicXpath).sendKeys(value);
    }

    protected void submitForm(AndroidDriver driver, String locator) {
        getElement(driver, locator).submit();
    }

    protected String getElementText(AndroidDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    protected Boolean isElementDisplayed(AndroidDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected Boolean isElementDisplayed(AndroidDriver driver, String locator, String... dynamicValues) {
        return getElement(driver, getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    protected Boolean isElementEnabled(AndroidDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected Boolean isElementSelected(AndroidDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected void waitForElementVisible(AndroidDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOf(getElement(driver, locator)));
    }

    protected void waitForElementVisible(AndroidDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOf(getElement(driver, getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementClickable(AndroidDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getElement(driver, locator)));
    }

    protected void waitForElementClickable(AndroidDriver driver, String locator, String... dynamicValues) {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getElement(driver, getDynamicXpath(locator, dynamicValues))));
    }

    protected String getCssValue(AndroidDriver driver, String locator, String cssPropertyName) {
        return getElement(driver, locator).getCssValue(cssPropertyName);
    }

    protected Point getElementPosition(AndroidDriver driver, String locator) {
        return getElement(driver, locator).getLocation();
    }

    protected Dimension getElementSize(AndroidDriver driver, String locator) {
        return getElement(driver, locator).getSize();
    }

    protected Rectangle getElementRectangle(AndroidDriver driver, String locator) {
        return getElement(driver, locator).getRect();
    }

    protected String getElementAttribute(AndroidDriver driver, String locator, String attrName) {
        return getElement(driver, locator).getAttribute(attrName);
    }

    protected String getAppContext(AndroidDriver driver) {
        return driver.getContext();
    }

    // all touch actions definitions

    private final PointerInput FINGER = new PointerInput(TOUCH, "finger");
    private Dimension screenSize;

    protected Dimension getScreenSize(AndroidDriver driver) {
        return driver.manage().window().getSize();
    }

    protected void doSwipe(AndroidDriver driver, Point start, Point end, int duration) {
        Sequence swipe = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ofSeconds(0), viewport(), start.getX(), start.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(FINGER.createPointerMove(Duration.ofSeconds(duration), viewport(), end.getX(), end.getY()))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    protected void doTap(AndroidDriver driver, Point point, int duration) {
        Sequence tap = new Sequence(FINGER, 1)
                .addAction(FINGER.createPointerMove(Duration.ofSeconds(0), viewport(), point.getX(), point.getY()))
                .addAction(FINGER.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(FINGER, Duration.ofSeconds(duration)))
                .addAction(FINGER.createPointerUp(LEFT.asArg()));
        driver.perform(Arrays.asList(tap));
    }

    protected void scrollDownToElement(AndroidDriver driver, String locator, int duration) {
        screenSize = getScreenSize(driver);
        Point start = new Point((int) (screenSize.width * 0.5), (int) (screenSize.height * 0.9));
        Point end = getElement(driver, locator).getLocation();
        System.out.println(getElement(driver, locator).isDisplayed());
        do{
            doSwipe(driver, start, end, duration);
            if (getElement(driver, locator).isDisplayed()) {
                break;
            }
        }while(true);
    }

    protected void scrollToBottom(AndroidDriver driver, int duration) {
        screenSize = getScreenSize(driver);
        Point start = new Point((int) (screenSize.width * 0.5), (int) (screenSize.height * 0.9));
        Point end = new Point((int) (screenSize.width * 0.5), (int) (screenSize.height * 0.2));
        doSwipe(driver, start, end, duration);
    }

}


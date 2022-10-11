package page_objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import page_interfaces.CommonPageUI;
import page_interfaces.HomePageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends CommonPage {
    private AndroidDriver driver;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public boolean isProductHeaderDisplayed(AndroidDriver driver) {
        waitForElementVisible(driver, HomePageUI.PRODUCT_HEADER_LABEL);
        return isElementDisplayed(driver, HomePageUI.PRODUCT_HEADER_LABEL);
    }

    public void clickToSortIcon(AndroidDriver driver) {
        waitForElementClickable(driver, HomePageUI.SORT_PRODUCT_ICON);
        clickToElement(driver, HomePageUI.SORT_PRODUCT_ICON);
    }

    public void clickToSortCriteriaButton(AndroidDriver driver, String sortCriteria) {
        waitForElementClickable(driver, HomePageUI.SORT_CRITERIA_BUTTON, sortCriteria);
        clickToElement(driver, HomePageUI.SORT_CRITERIA_BUTTON, sortCriteria);
    }

    public boolean isProductNameSortedCorrectly(AndroidDriver driver, String sortCriteria) {
        List<String> productNames = new ArrayList<>();
        for(int i = 0 ; i < 2; i++) {
            productNames.addAll(getTextOfAllElements(driver, HomePageUI.PRODUCT_NAME_LABEL));
            scrollToBottom(driver, 2);
        }
        List<String> originProductNames = new ArrayList<>(productNames);
        if (sortCriteria.equals("Name (A to Z)")) {
            Collections.sort(productNames);
        }
        else {
            Collections.sort(productNames, Collections.reverseOrder());
        }
        return productNames.equals(originProductNames);
    }
}

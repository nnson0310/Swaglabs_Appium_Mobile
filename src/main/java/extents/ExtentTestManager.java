package extents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
    static ExtentReports extentReports = ExtentManager.getExtentReports();

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int)Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(String testName, String testDesc) {
        ExtentTest test = extentReports.createTest(testName, testDesc);
        extentTestMap.put((int)Thread.currentThread().getId(), test);
        return test;
    }
}

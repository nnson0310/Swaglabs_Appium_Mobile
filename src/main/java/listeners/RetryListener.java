package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static listeners.ExtentTestListener.takeScreenshotAsBase64;

public class RetryListener implements IRetryAnalyzer {
    private int count = 0;
    private int retryTimes = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (iTestResult.isSuccess()) {
            iTestResult.setStatus(ITestResult.SUCCESS);
            return false;
        }
        if (count < retryTimes) {
            iTestResult.setStatus(ITestResult.FAILURE);
            takeScreenshotAsBase64(iTestResult);
            count++;
            return true;
        }
        return false;
    }
}

package listeners;

import helpers.MethodHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;

public class RecordVideoListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        MethodHelper.cleanRecordVideoDir();
    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

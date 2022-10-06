package extents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import commons.GlobalConstants;

import java.io.File;
import java.io.IOException;

import static commons.GlobalConstants.projectPath;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(projectPath + File.separator + "extent-report/extent-report.html");
        try {
            final File pathToConfigFile = new File(GlobalConstants.pathToTestResource + File.separator + "reporter_configs" + File.separator + "spark-config.json");
            sparkReporter.loadJSONConfig(pathToConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Author Name:", "Ngoc Son");
        extentReports.setSystemInfo("Os Name:", GlobalConstants.osName);
        extentReports.setSystemInfo("Os Version:", GlobalConstants.osVersion);

        return extentReports;
    }
}

package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Map<String, ExtentTest> extentTestMap = new HashMap<>();

    @Override
    public void onStart(ISuite suite) {
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extent-report.html");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Test Report");
        htmlReporter.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onFinish(ISuite suite) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
        extentTestMap.put(result.getMethod().getMethodName(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        // Not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        // Not implemented
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}

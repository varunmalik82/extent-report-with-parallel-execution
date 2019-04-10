package reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    static ExtentTest test;


    static ExtentReports extentReports = ExtentManager.getInstance();

    public static ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static ExtentTest startTest(String testName, String description) {
        test = ExtentManager.extentReports.createTest(testName);
        return test;
    }

}

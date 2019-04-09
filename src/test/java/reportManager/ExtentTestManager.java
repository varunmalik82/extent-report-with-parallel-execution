package reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {
    static ExtentTest test;
    static ExtentTest node;

    static ExtentReports report = ExtentManager.getInstance();

    static Map<Integer, ExtentTest> map = new HashMap<Integer, ExtentTest>();

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        test = ExtentManager.extentReports.createTest(testName);
        map.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return map.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest createNode(ExtentTest test, String description, String testName) {
        return test.createNode(testName, description);
    }

}

package example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

public class ReportingProblemWithParallelExecution {

    /*
    * Since we are going to created only one report for all the test execution irrespective of the number of browsers
    * Variables for 'ExtentHtmlReporter' & 'ExtentReports' should be static as they would be shared among threads for there
    * own test / node logging. 'ExtentTest' & 'WebDriver' variable shouldn't be static as this would cause issues.
    *
    * */

    private static ExtentHtmlReporter extentHtmlReporter;
    private static ExtentReports extentReport;
    /*
    * keeping below variables (test & node) static would cause anomaly in test logging in report
    * keeping 'driver' as static would create issue in test execution on browsers
    *
    **/
    private ExtentTest test;//
    private ExtentTest node;
    private WebDriver driver;

    @BeforeSuite
    public void setupReport() {
        extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReport.html");
        System.out.println(System.getProperty("user.dir") + "/ExtentReport.html");
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentHtmlReporter);
        extentReport.setSystemInfo("Host Name", "Test Report - Parallel Execution");
        extentReport.setSystemInfo("Project Name", "Test Automation");

        extentHtmlReporter.config().setDocumentTitle("Test Automation for Parallel Threads");
        extentHtmlReporter.config().setReportName("Parallel Testing");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
        extentHtmlReporter.config().setEncoding("UTF-8");
        System.out.println("*****Initialized Report*****");
    }

    @BeforeClass
    @Parameters("browser")
    public void beforeTest(String browser) {
        if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        }

        test = extentReport.createTest(browser);
    }

    @Test
    public void executSessionOne() {


        node = test.createNode("Test 1 ", "Test1 Execution");

        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 1");
        table(10, node);

        ExtentTest node2 = test.createNode("Test 2", "Test2 Execution");
        table(20, node2);

        extentReport.flush();
    }

    @Test
    public void executeSessionTwo() {
        node = test.createNode("Test 3 ", "Test3 Execution");

        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 3");
        table(30, node);


        ExtentTest node2 = test.createNode("Test 4", "Test4 Execution");
        table(40, node2);

        extentReport.flush();
    }

    @Test
    public void executSessionThree() {

        node = test.createNode("Test 5 ", "Test5 Execution");

        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 3");
        table(50, node);

        ExtentTest node2 = test.createNode("Test 6", "Test6 Execution");
        table(60, node2);

        extentReport.flush();
    }

    @AfterTest
    public void destroyDriver() {
        driver.close();
    }

    public void table(int num, ExtentTest node) {
        for (int i = 1; i <= 10; i++) {
            synchronized (this) {
                node.info(num + " * " + i + " = " + String.valueOf(num * i));
            }

        }
    }

}
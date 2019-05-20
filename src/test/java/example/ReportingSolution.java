package example;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import reportManager.ExtentManager;
import reportManager.ExtentTestManager;


public class ReportingSolution {
    volatile WebDriver driver;
    volatile ExtentTest test;
    volatile ExtentTest node;

    @BeforeClass
    @Parameters("browser")
    public void beforeTest(String browser) {
        if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            driver = new ChromeDriver();
        }
        test = ExtentTestManager.startTest(browser);
    }

    @Test
    public void executSessionOne() {

        node = test.createNode("executSessionOne", "Test Case1");
        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 1");

        table(10, node);

        ExtentTest node2 = test.createNode("executSessionOne", "Test Case 2");
        table(20, node2);

        ExtentManager.extentReports.flush();


    }

    @Test
    public void executeSessionTwo() {
        node = test.createNode("executeSessionTwo", "Test Case 3");
        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 1");

        table(30, node);

        ExtentTest node2 = test.createNode("executeSessionTwo", "Test Case 4");
        table(40, node2);

        ExtentManager.extentReports.flush();
    }

    @Test
    public void executSessionThree() {
        node = test.createNode("executSessionThree", "Test Case 5");
        driver.get("http://demo.guru99.com/V4/");
        driver.findElement(By.name("uid")).sendKeys("Driver 1");

        table(50, node);

        ExtentTest node2 = test.createNode("executSessionThree", "Test Case 6");
        table(60, node2);

        ExtentManager.extentReports.flush();
    }

    @AfterTest
    public void destroyDriver() {
        driver.close();
    }

    public void table(int num, ExtentTest node) {
        for (int i = 1; i <= 10; i++) {
            node.info(num + " * " + i + " = " + String.valueOf(num * i));
        }
    }
}

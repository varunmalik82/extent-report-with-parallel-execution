package reportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports extentReports;

	public static ExtentReports getInstance()
	{
		if(extentReports == null)
			setupReport();
		return extentReports;
		
	}

	public static void setupReport() {
		extentHtmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReport.html");
		System.out.println(System.getProperty("user.dir") + "/ExtentReport.html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentHtmlReporter);
		extentReports.setSystemInfo("Host Name", "Test Report - Parallel Execution");
		extentReports.setSystemInfo("Project Name", "Test Automation");

		extentHtmlReporter.config().setDocumentTitle("Test Automation for Parallel Threads");
		extentHtmlReporter.config().setReportName("Parallel Testing");
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		extentHtmlReporter.config().setEncoding("UTF-8");
		System.out.println("*****Initialized Report*****");
	}
	
}

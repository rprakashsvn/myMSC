package Reports;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utilities.Helper;

public class ExtentReport {

	public static ExtentTest child;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent = new ExtentReports();	
	public static ExtentTest test;

	@AfterTest
	public void endReport() {
		extent.flush();
		System.out.println("Report has been generated successfully.!!!");
	}

	@BeforeClass
	public void GenerateExtentReports() {
	
		System.out.println("Extent Report started to generate.");
		String TestCaseClassName = getClassName();
		System.out.println("Test Class Name Is : " + TestCaseClassName);
		
		htmlReporter = new ExtentHtmlReporter("./Reports\\" + TestCaseClassName + ".html");
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "Home");
		extent.setSystemInfo("OS", "Windows-10");
		extent.setSystemInfo("Envrionment", "QA Test");
		extent.setSystemInfo("Username", "Prakash Rajaram");

		htmlReporter.config().setDocumentTitle("Automation Testing Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setReportName("myMSC Scripts Status");
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public String getClassName() {
		String TestclassName = this.getClass().getSimpleName();
		return TestclassName;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test = extent.createTest(result.getName());
			test.log(Status.PASS, MarkupHelper.createLabel(result.getStatus() + "The Case Passed", ExtentColor.GREEN));
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test = extent.createTest(result.getName());

			String screenShotsDateTime = Helper.getDataTime();
			String screenshot = Helper.getScreenShot(screenShotsDateTime);
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Test Case Failed", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Snapshot Below : " + test.addScreenCaptureFromPath(screenshot));
		} else {
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "The Case Skipped", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}
}

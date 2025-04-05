package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "------Passed---------");
		//report.createTest(methodName);
		test = report.createTest(methodName);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "------Passed---------");
		test.log(Status.PASS, methodName + "------Passed---------");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "------Failed-------");
		test.log(Status.FAIL, methodName + "------Failed-------");
		test.log(Status.INFO, result.getThrowable());

		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshotname = methodName + "." + jutil.toGetSystemDateAndTime();
		try {

			String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshotname);
			test.addScreenCaptureFromPath(path);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "-------Skipped--------");
		test.log(Status.SKIP, methodName + "-------Skipped--------");
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("-----Suite Execution Started---------");
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				"./ExtentReports/Reports-" + new JavaUtility().toGetSystemDateAndTime() + ".html");
		htmlReport.config().setDocumentTitle("VTiger Execution Report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlReport);
		report.setSystemInfo("BAseUrl", "http://localhost:8888");
		report.setSystemInfo("Username", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("Reporter Name", "Shreya");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("------Suite Execution Finished-------");
		report.flush();
	}

}

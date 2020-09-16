package testCases;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	public static ExtentReports getInstance() {
		ExtentReports extent;
		String path = "C:\\Users\\rechi\\eclipse-workspace\\ExtentReportPractice\\reports\\usingextentfactory_report.html";
		extent = new ExtentReports(path, false);
		extent.addSystemInfo("Selenium VErsion", "3.14");
		extent.addSystemInfo("Server Environment", "QA");
		extent.addSystemInfo("Platform", "Windows");
		return extent;
	}

}

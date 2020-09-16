package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.TransferFundPage;

public class TestCase003_verifyTransferFund  extends BaseClass {

	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void setup() {
		report = new ExtentReports ("C:\\Users\\rechi\\eclipse-workspace\\Log4jVersion2\\src\\test\\resources\\reports\\TestCase003_report.html");
	}
	
	@Test 
	public void verifyTransferFund() {
		
		test = report.startTest("verifyTransferFund Test Started");
		
		app_logs.info("verifyTransferFund test started");
		
		AccountSummaryPage asp=new AccountSummaryPage(driver);
		asp.doClickTransferFund();
		test.log(LogStatus.INFO,"Clicked Transfer tab");
		
		TransferFundPage tf=new TransferFundPage(driver);		
		tf.doTransferFund("1", "2", "300", "August Transfer");
		test.log(LogStatus.INFO,"Entered From Account, To Account, Amount, Description, clicked Continue and clicked Submit");
		
		Assert.assertTrue(tf.alertContainer.isDisplayed());
		test.log(LogStatus.PASS,"Successfully Transferred a Fund");
		
		app_logs.info("verifyTransferFund test completed");

	}
	
	@AfterMethod
	public void screenshot(ITestResult testResult) throws IOException {				
		if (testResult.getStatus() == ITestResult.SUCCESS ) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName());
			String imagepath = test.addScreenCapture(path);
			test.log(LogStatus.PASS,"Test Passed" , imagepath);				
		}
		else if (testResult.getStatus() == ITestResult.FAILURE){
			String path = Screenshots.takeScreenshot(driver, testResult.getName());
			String imagepath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL,"Test Failed" , imagepath);
		}
	}

	@AfterClass
	public void closesetup() {
		report.endTest(test);
		report.flush();
	}	

	
}

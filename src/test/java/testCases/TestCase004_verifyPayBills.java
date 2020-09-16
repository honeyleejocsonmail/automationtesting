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
import pageClasses.PayBillsPage;


public class TestCase004_verifyPayBills  extends BaseClass {
		
	ExtentReports report;
		ExtentTest test;

		@BeforeClass
		public void setup() {
		report = new ExtentReports ("C:\\Users\\rechi\\eclipse-workspace\\Log4jVersion2\\src\\test\\resources\\reports\\TestCase004_report.html");
		
		}
		
		
		@Test  
		public void verifyPayBills() {
			
			test = report.startTest("verifyPayBills Test Started");
			
			app_logs.info("verifyPayBills test started");
			
			AccountSummaryPage asp=new AccountSummaryPage(driver);
			asp.doClickPayBills(); 
			test.log(LogStatus.INFO,"Clicked Pay Bills tab");
			
			PayBillsPage pb=new PayBillsPage(driver);	
			pb.doClickPaySavedPayee(); 
			test.log(LogStatus.INFO,"Clicked Pay Saved Payee tab");
			
			pb.doPayBills("Wells Fargo", "Checking", "250", "2020-08-20", "payment for mortgage");
			test.log(LogStatus.INFO,"Entered Payee Name , Account, Amount,  Date, Description and clicked Pay Buttton");
		
			Assert.assertTrue(pb.alertContainer.isDisplayed());
			test.log(LogStatus.PASS,"Successfully Paid the Bill");
			
			app_logs.info("verifyPayBills test started");
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

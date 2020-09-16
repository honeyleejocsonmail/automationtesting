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



public class TestCase002_verifyAddPayee extends BaseClass {

	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void setup() {
		report = new ExtentReports ("C:\\Users\\rechi\\eclipse-workspace\\Log4jVersion2\\src\\test\\resources\\reports\\TestCase002_report.html");
		
	}
	
	
	@Test  
	public void verifyAddPayee() {
		
		test = report.startTest("verifyAddPayee Test Started");
		
		app_logs.info("verifyAddPayee test started");
		
		AccountSummaryPage asp=new AccountSummaryPage(driver);
		asp.doClickPayBills(); 
		test.log(LogStatus.INFO,"Clicked Pay Bills tab");

		PayBillsPage pb=new PayBillsPage(driver);
		pb.doClickAddPayee(); 
		test.log(LogStatus.INFO,"Clicked Add Payee tab");
		
		pb.doAddPayee("HydroOne Utility", "200 RoberSpec Pkwy, Mississauga, ON L6R1K9", "123234434", "Natural Gas Utility");
		test.log(LogStatus.INFO,"Entered Payee Name , Adress, Account Number, Account Details and clicked Add Button");
		
		Assert.assertTrue(pb.alertContainer.isDisplayed());
		test.log(LogStatus.PASS,"Payee Successfully Added");
		
		app_logs.info("verifyAddPayee test completed");
		
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

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
import pageClasses.HomePage;
import pageClasses.LoginPage;

public class TestCase001_verifyLogin extends BaseClass {

	HomePage hp;
	LoginPage lp;
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeClass
	public void setup() {
		report = new ExtentReports ("C:\\Users\\rechi\\eclipse-workspace\\Log4jVersion2\\src\\test\\resources\\reports\\TestCase001_report.html");
		
		//report = ExtentFactory.getInstance();
	}
	
	@Test 
	public void verifyLogin() {
		
		test = report.startTest("verifyLogin Test Started");
		
		app_logs.info("verify login test started");
		
		hp = new HomePage(driver);
		hp.clickSignInbtn();
		test.log(LogStatus.INFO,"Clicked Signin Button");
		
		
		lp=new LoginPage(driver);
		lp.doLogin("username", "password");
		test.log(LogStatus.INFO,"Entered Username, Password and click Submit Button");
		
		String actual = driver.getTitle();
		String expected="Zero - Account Summary";
		Assert.assertEquals(actual, expected);
		test.log(LogStatus.PASS,"Login Successfully");
		
		app_logs.info("verify login test completed");
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

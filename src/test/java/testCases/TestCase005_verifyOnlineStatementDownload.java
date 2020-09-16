package testCases;

import java.io.IOException;

import org.openqa.selenium.By;
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
import pageClasses.OnlineStatementsPage;


public class TestCase005_verifyOnlineStatementDownload extends BaseClass {
		
		ExtentReports report;
		ExtentTest test;
	
		@BeforeClass
		public void setup() {
			report = new ExtentReports ("C:\\Users\\rechi\\eclipse-workspace\\Log4jVersion2\\src\\test\\resources\\reports\\TestCase005_report.html");
		
		}
		
			@Test  
			public void verifyOnlineStatement() {
				
				test = report.startTest("verifyOnlineStatement Test Started");
				
				app_logs.info("verifyOnlineStatement test started");
				
				AccountSummaryPage asp=new AccountSummaryPage(driver);
				asp.doClickOnlineStatement(); 
				test.log(LogStatus.INFO,"Clicked Online Statement tab");
				
				
				OnlineStatementsPage osp=new OnlineStatementsPage(driver);
				osp.doAccount("Checking");
				test.log(LogStatus.INFO,"Entered Account Type");
						
				
				//click the year - try/catch code for StaleElementReferenceException Error
				try {
					osp.doclickyearlink();
					test.log(LogStatus.INFO,"Clicked the year link");
				}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
				{
					osp.doclickyearlink();
					test.log(LogStatus.INFO,"Clicked the year link");
				}
				
				osp.doclickstatementlink();
				test.log(LogStatus.INFO,"Clicked the statement link");
				
				//navigate to downloads
				driver.navigate().to("chrome://Downloads/");
				test.log(LogStatus.INFO,"Opened the path = chrome://Downloads/");
				
				String filename = driver.findElement(By.tagName("body")).getText();
				Assert.assertTrue(filename.contains("8534567-05-12-11"), "File not Found");
				test.log(LogStatus.PASS,"Successfully Downloaded an Online Statement");
				
				app_logs.info("verifyOnlineStatement test completed");
				
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

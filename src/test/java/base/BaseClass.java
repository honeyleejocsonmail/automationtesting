package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;





public class BaseClass {

	//Initialise Webdriver, properties, extent reports, extent test, log4j
	public static Properties config = new Properties();
	public static WebDriver driver = null;
	public static Logger app_logs = Logger.getLogger("HoneyleeLogger");


	
	@BeforeSuite()
	public static void init() throws IOException {
		
		
		
		if (driver == null) {
			// load config property file
			FileInputStream fis = new FileInputStream(
			System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");		
			config.load(fis);
			
			app_logs.info("loading the config property file");
			
			
			
			if (config.getProperty("browser").equals("chrome")) { 
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();		
				app_logs.info("chrome browser launched");
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("edge")) {
				System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
			}

			driver.get(config.getProperty("testsiteURL"));
			driver.manage().window().maximize();
			app_logs.info("window maximized");

			// Global implicit Wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		}
	}
	

	
	
	
	@AfterSuite()
	public static void tearDown() throws IOException {
		
		if(driver!=null) {
		 driver.quit();
			}
	
	}
}

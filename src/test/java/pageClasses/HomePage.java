package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage {
	
	
	public HomePage(WebDriver driver) {
		super(driver);
		}

	//Locate Web Elements
	@FindBy(id="signin_button")
	public WebElement btnsignin;
	
	public void clickSignInbtn() {
		btnsignin.click();
	}
	

}

package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class DownloadPage extends BasePage {
	
	public DownloadPage(WebDriver driver) {
		super(driver);
		}

	@FindBy(id="file-link")
	public WebElement actualfile;
	
	public void doclickfile() {
		actualfile.click();
	}
}

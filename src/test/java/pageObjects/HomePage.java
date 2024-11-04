package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
	@FindBy(xpath="//*[@id='main-auto']") 
	WebElement searchBox;
	
	@FindBy(xpath="//*[text()=\"Free Listing\"]")
	WebElement listing;
	
	@FindBy(xpath="//img[contains(@title,'Gym')]")
	WebElement gym;

	
	By loginPopLocator = By.xpath("//*[@id='loginPop']");
	By maybeLaterLocator = By.xpath("//a[normalize-space(text())='Maybe Later']");
	By autoSuggestionLocator = By.xpath("//ul/li/a/div[2]/b");
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	public void handleLoginPopup() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPopLocator));
        driver.findElement(maybeLaterLocator).click();
       
	}
	
	public void searchCarWashingServices() {
		searchBox.sendKeys("Car Washing Services");
		try {
			Thread.sleep(1);
		}catch(Exception e) {};
        wait.until(ExpectedConditions.presenceOfElementLocated(autoSuggestionLocator)).click();

	}
	public void listServices() {
		listing.click();
	}
	
	public void gymServices() {
		gym.click();
	}

}

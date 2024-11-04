package pageObjects;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FreeListingPage extends BasePage {
	
	By inputBoxLocator = By.xpath("//input[normalize-space(@aria-label=\"Enter Mobile Number\")]");
	By buttonLocator = By.xpath("//button[normalize-space(@aria-label=\"Start Now\")]");
	By errorMsgLocator = By.xpath("//*[contains(@class,'entermobilenumber_error__text')]");

	public FreeListingPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void listWithInvalidMobileNo() {
		Random rm = new Random();
        wait.until(ExpectedConditions.presenceOfElementLocated(inputBoxLocator)).sendKeys(String.valueOf(rm.nextInt(900000000)));
    
        driver.findElement(buttonLocator).click();
        
	}
	
	public void retriveErrorMsg() {
		String err = wait.until(ExpectedConditions.presenceOfElementLocated(errorMsgLocator)).getText();
        System.out.println(err);
	}
	

}

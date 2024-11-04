package pageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;

public class GymPage extends BasePage{
	By filtersLocator = By.xpath("//nav//li[@role='menuitem']");
	public GymPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public void retriveAllFilters() throws IOException {
		
    	ExcelUtility excelUtility = new ExcelUtility(".\\reports\\GymDetails.xlsx");
    	wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(filtersLocator));
		List<WebElement> list = driver.findElements(filtersLocator);
        
        for(int i=0; i<list.size();i++) {
        	excelUtility.setCellData("sheet1", i, 0, list.get(i).getText());
        }
	}
}


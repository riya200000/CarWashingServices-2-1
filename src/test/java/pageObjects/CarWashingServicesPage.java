package pageObjects;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;

public class CarWashingServicesPage extends BasePage {
    
    By topRatedFilterLocator = By.xpath("//*[text()=\"Top Rated\"]");
    
  
    public CarWashingServicesPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    public void topRatedFilter() {
        wait.until(ExpectedConditions.presenceOfElementLocated(topRatedFilterLocator)).click();
    }
    
    public void retriveFiveWashingServices() throws IOException {
    	ExcelUtility excelUtility = new ExcelUtility(".\\reports\\CarDetails.xlsx");
    	excelUtility.setCellData("sheet1", 0, 0,"Name");
    	excelUtility.setCellData("sheet1", 0, 1,"Rating");
    	excelUtility.setCellData("sheet1", 0, 2,"Votes");
    	excelUtility.setCellData("sheet1", 0, 3,"Phone");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@keyid='0']//*[contains(@class, ' resultbox_title_anchor ')]")));
        for (int i = 0; i < 5; i++) {
        	String name = driver.findElement(By.xpath("//*[@keyid='"+i+"']//*[contains(@class, ' resultbox_title_anchor ')]")).getText();
        	String rating = driver.findElement(By.xpath("//*[@keyid='"+i+"']//*[contains(@class, ' whitestar ')]/parent::div")).getText();
        	String str = driver.findElement(By.xpath("//*[@keyid='"+i+"']//div[contains(@class,\" resultbox_countrate  \")]")).getText();
        	String votes = str.split(" ")[0];
        	driver.findElement(By.xpath("//*[@keyid='"+i+"']//*[contains(normalize-space(@class), 'callcontent')]")).click();
        	String phone= wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@keyid='"+i+"']//*[contains(normalize-space(@class), 'whitecall_icon')]/parent::*"))).getText();
        	excelUtility.setCellData("sheet1", i+1, 0,name);
        	excelUtility.setCellData("sheet1", i+1, 1,rating);
        	excelUtility.setCellData("sheet1", i+1, 2,votes);
        	excelUtility.setCellData("sheet1", i+1, 3,phone);
        }
    }
}

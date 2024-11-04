package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties properties;

	@BeforeClass(groups= {"sanity"})
	@Parameters({"os", "browser"})
	public void setup(String os,String browser) throws IOException {

		FileReader file=new FileReader(".//src//test//resources//config.properties");
		properties=new Properties();
		properties.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		if (properties.getProperty("execution_env").equalsIgnoreCase("remote")) {
		    DesiredCapabilities capabilities = new DesiredCapabilities();

		    // OS
		    if (os.equalsIgnoreCase("windows")) {
		        capabilities.setPlatform(Platform.WIN11);
		    } else if (os.equalsIgnoreCase("mac")) {
		        capabilities.setPlatform(Platform.MAC);
		    } else {
		        System.out.println("No matching OS");
		        return;
		    }

		    // Browser
		    switch (browser.toLowerCase()) {
		        case "chrome":{
		            WebDriverManager.chromedriver().setup();
		            ChromeOptions chromeOptions = new ChromeOptions();
		            chromeOptions.addArguments("start-maximized");
					chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
					chromeOptions.addArguments("--disable-notifications");
		            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		            capabilities.setBrowserName("chrome");
		            break;
		        }
		        case "edge":{
		            WebDriverManager.edgedriver().setup();
		            EdgeOptions edgeOptions = new EdgeOptions();
		            edgeOptions.addArguments("start-maximized");
					edgeOptions.addArguments("--disable-blink-features=AutomationControlled");
					edgeOptions.addArguments("--disable-notifications");
					capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
		            capabilities.setBrowserName("MicrosoftEdge");
		            break;
		        }
//		        case "firefox": {
//		            WebDriverManager.firefoxdriver().setup();
//		            FirefoxOptions firefoxOptions = new FirefoxOptions();
//		            firefoxOptions.addArguments("--start-maximized"); // Start maximized
//		            firefoxOptions.addPreference("dom.webnotifications.enabled", false); // Disable notifications
//		            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
//		            capabilities.setBrowserName("firefox");
//		            break;
//		        }
		        default:
		            System.out.println("No matching browser");
		            return;
		    }

		    try {
		        URI uri = new URI("http://localhost:4444/wd/hub");
		        driver = new RemoteWebDriver(uri.toURL(), capabilities);
		    } catch (URISyntaxException e) {
		        e.printStackTrace();
		    }
		}



		if(properties.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {

			case "edge":{
				EdgeOptions options = new EdgeOptions();
				options.addArguments("start-maximized");
				options.addArguments("--disable-blink-features=AutomationControlled");
				options.addArguments("--disable-notifications");
				driver = new EdgeDriver(options);
				break;

			}
			case "chrome":{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				options.addArguments("--disable-blink-features=AutomationControlled");
				options.addArguments("--disable-notifications");
				driver = new ChromeDriver(options);
				break;
			}
//			case "firefox": {
//				FirefoxOptions options = new FirefoxOptions();
//				options.addArguments("start-maximized");
//				options.addArguments("--disable-blink-features=AutomationControlled");
//				options.addArguments("--disable-notifications");
//				driver = new FirefoxDriver(options);
//				break;
//			}
			default:
				System.out.println("Invalid browser name!!!");
				return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("appURL"));


	}
	@AfterClass(groups= {"sanity"})
	public void tearDown()
	{
		if (driver != null) { 
            driver.quit();
        }
	}
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}


}

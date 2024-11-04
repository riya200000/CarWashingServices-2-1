package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CarWashingServicesPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_ListOfCarWashingServices extends BaseClass{
	@Test(groups= {"sanity"})
	public void topFiveCarWashingCenter() throws IOException {

		logger.info("**** Starting TC_001_ListOfCarWashingServices*****");

		try {
			HomePage hp = new HomePage(driver);
			try {
				hp.handleLoginPopup();
			}catch(Exception e) {
				logger.info("login pop did not appeared");
			}
			hp.searchCarWashingServices();

			CarWashingServicesPage cw = new CarWashingServicesPage(driver);
			cw.topRatedFilter();
			cw.retriveFiveWashingServices();
		}catch(Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}

		logger.info("**** TC_001_ListOfCarWashingServices Completed Successfully *****");
	}

}

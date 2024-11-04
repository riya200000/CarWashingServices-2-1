package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FreeListingPage;
import pageObjects.GymPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_003_RetriveAllGymServicesFilters extends BaseClass {
	
	@Test(groups= {"sanity"})
	public void retriveFilters() throws IOException {

		logger.info("**** Starting TC_003_RetriveAllGymServicesFilters *****");
		
		try {
			HomePage hp = new HomePage(driver);
			try {
				hp.handleLoginPopup();
			}catch(Exception e) {
				logger.info("login pop did not appeared");
			}
			hp.gymServices();
			
			GymPage gp = new GymPage(driver);
			gp.retriveAllFilters();
		}catch(Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		
		logger.info("**** TC_003_RetriveAllGymServicesFilters Completed Successfully *****");
		
	}

}

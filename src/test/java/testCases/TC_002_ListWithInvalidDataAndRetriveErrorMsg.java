package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FreeListingPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_002_ListWithInvalidDataAndRetriveErrorMsg extends BaseClass {

	@Test(groups= {"smoke"})
	public void errorMsgForInvalidData() {
		
		logger.info("**** Starting TC_002_ListWithInvalidDataAndRetriveErrorMsg *****");
		
		try {
		HomePage hp = new HomePage(driver);
		try {
			hp.handleLoginPopup();
		}catch(Exception e) {
			logger.info("login pop did not appeared");
		}
		hp.listServices();
		
		FreeListingPage flp = new FreeListingPage(driver);
		flp.listWithInvalidMobileNo();
		flp.retriveErrorMsg();
		}catch(Exception e) {
			Assert.fail("An exception occurred: " + e.getMessage());
		}
		
		logger.info("**** TC_002_ListWithInvalidDataAndRetriveErrorMsg Completed Successfully *****");

	}
}

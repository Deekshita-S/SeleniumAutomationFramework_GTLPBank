package com.inetBanking.TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.inetBanking.pageObjects.BaseClass;
import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
			
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPwd(password);
		logger.info("Entered userID and pwd");
		lp.clickSubmit();
		logger.info("logged in");
		
		softAssert.assertEquals(driver.getTitle(), " Guru99 Bank Manager HomePage ", "Page title not matched");
		
		if(driver.getTitle()!=" Guru99 Bank Manager HomePage ") {
			captureScreenshot(driver,"loginTest");
		}
		
	}

}

package com.inetBanking.TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetBanking.pageObjects.*;
import com.inetBanking.utilities.XLUtils;

public class TC_Login_DataDrivenTest extends BaseClass {
	
	
/*Scenarion: As we r using dataprovider, we will want to navigate back to login page for each data set.
 * Valid credentials :- user logs in. User must then log out, logout alert popup, accept to navigate back to login page.
 * Invalid credentials:- alert pops up, accept the alert to navigate back to login page*/
	@Test(dataProvider="LoginData")
	public void loginDDT(String uName, String pwd, String dataValid)throws IOException, InterruptedException {
		
		LoginPage lp =new LoginPage(driver);
		lp.setUserName(uName);
		lp.setPwd(pwd);
		lp.clickSubmit();
		
		if(isAlertPresent()&& dataValid.equals("invalid")) {
			logger.info("TC Passed:Login failed due to invalid credentials");			
			softAssert.assertTrue(true);
		}
		else if(isAlertPresent()&& dataValid.equals("valid")) {
			logger.info("TC Failed: Login failed with valid credentials");	
			softAssert.assertTrue(false);
		}
		else {
			logger.info("TC Passed: Login passed with valid credentials");
			softAssert.assertTrue(true);
			lp.clickLogOut();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	
	// Check if alert is present. Alert msg displayed on login failure
	public boolean isAlertPresent() throws IOException, InterruptedException {
		try {
		driver.switchTo().alert();
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
	}
	
	//data provider method
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException{
		String path=System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetBanking\\TestData\\mavenData.xlsx"; //path to excel sheet
		
		int rowNum =XLUtils.getRowCount(path,"Sheet1");
		int colCount =XLUtils.getColCount(path,"Sheet1",1);
		
		// 2D array to store the values from excel
		String loginData[][]= new String[rowNum][colCount];
		
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colCount;j++) {
				//array start from 00 index and we want to store values from 2nd row first col in excel hence i-1 in loginData but i in getCell
				loginData[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);	
			}
		}
		return loginData;
	}

}

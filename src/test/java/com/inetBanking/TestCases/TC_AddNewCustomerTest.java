package com.inetBanking.TestCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.*;

public class TC_AddNewCustomerTest extends BaseClass {
	
	
	//e2e flow of adding new customer with valid details
	@Test
	public void addNewCustomerValidDetails() throws IOException, InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPwd(password);
		lp.clickSubmit();
		logger.info("loggen in");
		
		AddNewCustomerPage addCust = new AddNewCustomerPage(driver);
		
		logger.info("adding new customer details");
		addCust.clickNewCust();
		System.out.println("In new Cust page");
//		handleAds();
		
		addCust.setName("ABC");
		addCust.setGender("female");
		addCust.setDOB("28", "05", "1960");
		addCust.setAddress("Test Street");
		addCust.setCity("BLR");
		addCust.setState("KAR");
		addCust.setPIN("560002");
		addCust.setPhno("1234567890");
		addCust.setEmail(strRandom(5)+"@gmail.com");
		addCust.setPwd("abc123");
		addCust.clickSubmit();
		
		logger.info("start validation ...");
		boolean result = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if(result) {
			logger.info("new customer successfully added");
			captureScreenshot(driver,"Pass_AddNewCustomer");
			softAssert.assertTrue(true);
		}
		else {
			logger.info("failed to add new customer");
			captureScreenshot(driver,"Fail_AddNewCustomer");
			softAssert.assertTrue(false);
			
		}
	logger.info("End validation ...");
		
		
	}
	
	
	//e2e flow of adding new customer with invalid details
	@Test
	public void AddNewCustInvalidDetails() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPwd(password);
		lp.clickSubmit();
		logger.info("loggen in");
		
		AddNewCustomerPage addCust = new AddNewCustomerPage(driver);
		
		logger.info("adding new customer details");
		addCust.clickNewCust();
		System.out.println("In new Cust page");
//		handleAds();
		
		addCust.setName("abc1");
		addCust.setCity("BLR12");
		addCust.setState("KAR23");
		addCust.setPIN("abcd23");
		addCust.setPhno("1234567def");
		addCust.setEmail(strRandom(5));
		
		logger.info("start validation ...");
		addCust.isMessageDisplayed(addCust.txtName, "Numbers are not allowed");
		addCust.isMessageDisplayed(addCust.txtCity, "Numbers are not allowed");
		addCust.isMessageDisplayed(addCust.txtState, "Numbers are not allowed");
		addCust.isMessageDisplayed(addCust.txtPIN, "Characters are not allowed");
		addCust.isMessageDisplayed(addCust.txtPhno, "Characters are not allowed");
		addCust.isMessageDisplayed(addCust.txtEmail, "Email-ID is not valid");
		
		captureScreenshot(driver,"InvalidDataPassed");
		logger.info("End validation ...");	
		
	}
	
	//e2e flow of adding new customer with blank details
	@Test
	public void AddNewCustBlankFields() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(userName);
		lp.setPwd(password);
		lp.clickSubmit();
		logger.info("loggen in");
		
		AddNewCustomerPage addCust = new AddNewCustomerPage(driver);
		
		logger.info("adding new customer details");
		addCust.clickNewCust();
		System.out.println("In new Cust page");
//		handleAds();
		
		addCust.setName("");
		addCust.setCity("");
		addCust.setState("");
		addCust.setPIN("");
		addCust.setPhno("");
		addCust.setEmail("");  
		addCust.setPwd("");
		
		logger.info("start validation ...");
		addCust.isMessageDisplayed(addCust.txtName, "Customer name must not be blank");
		addCust.isMessageDisplayed(addCust.txtCity, "City Field must not be blank");
		addCust.isMessageDisplayed(addCust.txtState, "State must not be blank");
		addCust.isMessageDisplayed(addCust.txtPIN, "PIN Code must not be blank");
		addCust.isMessageDisplayed(addCust.txtPhno, "Mobile no must not be blank");
		addCust.isMessageDisplayed(addCust.txtEmail, "Email-ID must not be blank");
		
		
		addCust.clickSubmit();
		softAssert.assertEquals("Alert Message:No/wrong alert message displayed", driver.switchTo().alert().getText(), "please fill all fields");
		driver.switchTo().alert().accept();
		captureScreenshot(driver,"BlankDataPassed");
		logger.info("End validation ...");
			
	}
	
	
	

}

package com.inetBanking.pageObjects;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.inetBanking.utilities.*;
import org.openqa.selenium.TakesScreenshot;


public class BaseClass {
	// creating object of ReadConfig class
	ReadConfig readConfig = new ReadConfig();

	public String baseURL=readConfig.getApplicationURL();
	public String userName = readConfig.getUserName();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public  SoftAssert softAssert = new SoftAssert();
	
	By iframe1Ads =By.xpath("//ins/div/iframe[@title='3rd party ad content']");
	By iframe2Ads =By.id("ad_iframe");
	By closeAds =By.xpath("//div[@id='dismiss-button']");
	
	public static Logger logger;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void setup(String browser) {
		logger =Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("starting a TC");
		
		if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
		driver = new ChromeDriver();
		
		}
		else {
			logger.info("Enter valid browser");
		}
		logger.info("open url");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
		
	}
	
	@AfterMethod
	public void TesttearDown() {
		driver.quit();
	}
	
	
	
	public void captureScreenshot(WebDriver driver, String tname) throws IOException{ //tname - TC name
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source,target);
		System.out.println("Screenshot Taken");
		
	}
	
	//returns a random string of desired length. Email has to be unique when adding a new cust, thus generating a random str to append to email
		public String strRandom(int len) {
			String randomStr = RandomStringUtils.randomAlphabetic(len);
			return randomStr;
		}
		
		//returns a random number of desired length
		public String strNumber(int len) {
			String randomStr = RandomStringUtils.randomNumeric(len);
			return randomStr;
		}
		
		
		//handle ads
		public void handleAds() throws InterruptedException {
			if(!driver.findElements(iframe1Ads).isEmpty()) {
				try { // if close button in outer iframe
			driver.switchTo().frame(driver.findElement(iframe1Ads));
			driver.findElement(closeAds).click();
			driver.switchTo().defaultContent();
				}
	
				catch(Exception e) { //if close button in inner iframe
					driver.switchTo().frame(driver.findElement(iframe2Ads));
					driver.findElement(closeAds).click();
					driver.switchTo().defaultContent();
				}
			}
		}
		
}

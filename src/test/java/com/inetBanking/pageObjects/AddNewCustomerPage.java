package com.inetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import junit.framework.Assert;

public class AddNewCustomerPage {

	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();
	
	By lnkNewCust =By.xpath("//a[text()='New Customer']");
	public By txtName = By.xpath("//input[@name='name']");
	By btnMale =By.xpath("//input[@name='rad1'][@value='m']");
	By btnFemale =By.xpath("//input[@name='rad1'][@value='f']");
	By dateDOB =By.xpath("//input[@name='dob']");
	By txtAddress =By.xpath("//textarea[@name='addr']");
	public By txtCity =By.xpath("//input[@name='city']");
	public By txtState =By.xpath("//input[@name='state']");
	public By txtPIN =By.xpath("//input[@name='pinno']");
	public By txtPhno =By.xpath("//input[@name='telephoneno']");
	public By txtEmail =By.xpath("//input[@name='emailid']");
	By txtPwd =By.xpath("//input[@name='password']");
	By btnSubmit =By.xpath("//input[@name='sub']");
	
	
	//constructor
	public AddNewCustomerPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void clickNewCust() {
		driver.findElement(lnkNewCust).click();
	}
	
	public void clickSubmit() {
		driver.findElement(btnSubmit).click();
	}
	
	
	public void setName(String name) {
		driver.findElement(txtName).sendKeys(name);
	}
	
	public void setGender(String gen) {
		if(gen.equalsIgnoreCase("female"))
			driver.findElement(btnFemale).click();
		else
			driver.findElement(btnMale).click();
	}
	
	public void setDOB(String dd,String mm,String yyyy) {
		driver.findElement(dateDOB).sendKeys(dd);
		driver.findElement(dateDOB).sendKeys(mm);
		driver.findElement(dateDOB).sendKeys(Keys.ARROW_RIGHT); // right arrow key must be pressed to move from month to year
		driver.findElement(dateDOB).sendKeys(yyyy);
	}
	
	public void setAddress(String addr) {
		driver.findElement(txtAddress).sendKeys(addr);
	}
	
	public void setCity(String city) {
		driver.findElement(txtCity).sendKeys(city);
	}
	
	public void setState(String state) {
		driver.findElement(txtState).sendKeys(state);
	}
	
	public void setPIN(String pin) {
		driver.findElement(txtPIN).sendKeys(pin);
	}
	
	public void setEmail(String email) {
		driver.findElement(txtEmail).sendKeys(email);
	}
	
	public void setPhno(String ph) {
		driver.findElement(txtPhno).sendKeys(ph);
	}
	
	public void setPwd(String pwd) {
		driver.findElement(txtPwd).sendKeys(pwd);
	}
	
	
	//checks if appropriate message is displayed when invalid data is passed
	public boolean isMessageDisplayed(By xPath , String expectedMsg) {
		String partialMsgXpath="/following-sibling::label[contains(@id,'message')]";
		String msgXpath=xPath.toString().concat(partialMsgXpath); 
		
		/*converting to string gives us this 'By.xpath: //input[@name='name']/following-sibling::label[contains(@id,'message')]', 
		 * but we dont want 'By.xpath: ' part and thus we get the substring from the index of the space btw 'By.xpath:' and '//input' to the end of the string*/
		msgXpath=msgXpath.substring(msgXpath.indexOf(" ") , msgXpath.length());
		if(!driver.findElement(By.xpath(msgXpath)).getText().isEmpty()) {
		String actualMsg = driver.findElement(By.xpath(msgXpath)).getText();
		softAssert.assertEquals(expectedMsg+" - Message not displayed", actualMsg,expectedMsg);
		return actualMsg.equals(expectedMsg);
		}
		else{
			return false;
		}
		
		
	}
}

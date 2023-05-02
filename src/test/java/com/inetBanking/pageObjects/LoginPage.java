package com.inetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	By txtUserID = By.xpath("//input[@name='uid']");
	By txtPwd = By.xpath("//input[@name='password']");
	By btnLogin = By.xpath("//input[@name='btnLogin']");
	By linkLogout = By.xpath("//a[text()='Log out']");

	
	//constructor
	public LoginPage(WebDriver driver){
		this.driver=driver; 
	}
	
	//set username and pwd
	public void setUserName(String uname) {
		driver.findElement(txtUserID).sendKeys(uname);
	}

	public void setPwd(String pwd) {
		driver.findElement(txtPwd).sendKeys(pwd);
	}
	
	//Action - click on login button
	public void clickSubmit() {
		driver.findElement(btnLogin).click();
	}
	
	public void clickLogOut() {
		driver.findElement(linkLogout).click();
	}
}

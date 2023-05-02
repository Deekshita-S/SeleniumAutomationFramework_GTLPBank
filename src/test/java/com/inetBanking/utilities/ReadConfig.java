package com.inetBanking.utilities;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;


public class ReadConfig {
	Properties pro;
	
	//constructor
	public ReadConfig() {
		// './' represents project home directory. './Configuration/config.properties' directs to config.prop file under utilities package
		File src = new File("./Configurations/config.properties");
		
		try {
			//reading data from config.properties file
			FileInputStream fis = new FileInputStream(src); //created an input stream that will be linked to the file specified by the path
			pro=new Properties();
			pro.load(fis); //Reads a property list (key and element pairs) from the input byte stream.
		}
		catch(Exception e) {
			System.out.println("Exception: "+e.getMessage().toString());
		}
	}
	
	
	//reads 'baseURL' key from config.prop file and returns value 
	public String getApplicationURL() {
		String url=pro.getProperty("baseURL"); //Searches for the property with the specified key in this property list.
		return url;
	}
	
	public String getUserName() {
		String userName=pro.getProperty("userName");
		return userName;
	}
	
	public String getPassword() {
		String password=pro.getProperty("password");
		return password;
	}
	
	public String getChromePath() {
		String chromepath=pro.getProperty("chromepath");
		return chromepath;
	}
	
	

}

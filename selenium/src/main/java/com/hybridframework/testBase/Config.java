package com.hybridframework.testBase;

public class Config extends BaseTest {
	
	public String GetUserName() {
		return OR.getProperty("UserName");
	}
	public String GetPassword() {
		return OR.getProperty("Password");
	}
	public String GetWebsite() {
		return OR.getProperty("Website");
	}
	public int GetPageloadTimeOut() {
    return Integer.parseInt(OR.getProperty("PageloadTimeOut"));
	}
	public int GetImplicitWait() {
	    return Integer.parseInt(OR.getProperty("Implicitwait"));
		}
	public int GetExplicitWait() {
	    return Integer.parseInt(OR.getProperty("Explicitwait"));
	}
	public String getDbType() {
		return OR.getProperty("DataBase.Type");
	}
	public String getDbConnectionStr() {
		return OR.getProperty("DataBase.ConnectionStr");
	}
	public String getBrowser() {
		return OR.getProperty("Browser");
	}
}

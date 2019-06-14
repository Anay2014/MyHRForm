package com.hybridframework.homepage;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import com.hybridframework.testBase.BaseTest;

public class TestDataDrivenScript extends BaseTest {
	
	@DataProvider(name = "TestData")
	public String[][] dataSource() {
		return getData("excelName", "sheetName");
	}

	public void testLogin(String userName ,String password ) {
		System.out.println("username:" + userName);
		System.out.println("password:" + password);
		//driver.findElement(By.xpath("")).sendKeys("userName");
		//driver.findElement(By.xpath("")).sendKeys("password");
	}
}

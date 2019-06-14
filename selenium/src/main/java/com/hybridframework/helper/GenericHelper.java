package com.hybridframework.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class GenericHelper {

	private static final Logger log = Logger.getLogger(GenericHelper.class);
	
	public String readValueFromElement(WebElement element) {
		
		if(null == element) { 
			log.info("webelement is null");
			return null;
		}
		
		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		}
		catch(Exception e) {
			log.error(e);
			return null;
		}
		if(!displayed) {
			return null ;
		}
		String text = element.getText();
		log.info("webelement value is :" +text);
		return text ;
	}
	public String readValuefromInput(WebElement element) {
	 
		if(null ==element) 
			return null;
		if(!isDisplayed(element))
			return null;
		String value = element.getAttribute("value");
		log.info("webelement value is :" +value);
		return value ;
		}

	public boolean isDisplayed(WebElement element) {
try {
	element.isDisplayed();
	log.info("element is displayed."+element);
	return true;
}catch (Exception e) {
	log.info(e);
}
	return false;
	}
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed."+element);
			return false;
		}catch (Exception e) {
			log.info(e);
		}
			return true;
}
	
	}

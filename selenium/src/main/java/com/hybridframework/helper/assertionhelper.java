package com.hybridframework.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class assertionhelper {

	private static final Logger log = Logger.getLogger(assertionhelper.class);
	private static final String actualText = null;
	public static synchronized boolean verifyElementPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed =element.isDisplayed();
			log.info(element.equals(element.getText()+"is Displayed"));
		}
		catch(Exception e){
			log.error("Element not found " +e);
		}
		return isDisplayed;
		}
	public static synchronized boolean verifyElementNotPresent(WebElement element) {
		boolean isDisplayed = false;
		try {
			isDisplayed =element.isDisplayed();
			log.info(element.getText()+"is Displayed");
		}
		catch(Exception e){
			log.error("Element not found " +e);
			isDisplayed = true;
		}
		return isDisplayed;
		}
	public static synchronized boolean verifyTextEquals(WebElement element , String expectedText) {
		boolean flag = false;
		try {
			String actualText = element.getText();
			if (actualText.equals(expectedText)){
				log.info("actualText is :"+actualText+"expected text is:"+expectedText);
				return flag = true;
			}
		else {
			log.error("actualText is :"+actualText+"expected text is:"+expectedText);
			return flag;
		}
	}
		catch(Exception e) {
        log.error("actualText is :" +actualText+"expected text is: "+expectedText);
          log.info("text not matching" + e);
          return flag;
		}
		
	}
}
	


package com.hybridframework.helper;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	private WebDriver driver;
	
	private Logger log = Logger.getLogger(WaitHelper.class);
	
	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("WaitHelper :" + this.driver.hashCode());
	}
	public void setImplicitWait(long timeout, TimeUnit unit) {
		log.info(timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit == null?TimeUnit.SECONDS:unit);
	}
	private WebDriverWait getWait(int timeOutInSeconds,int pollingEveryInMiliSce) {
		log.debug("");
		WebDriverWait wait =new WebDriverWait(driver , timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSce, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		
		return wait;
		
	}
	public void waitElementVisible(WebElement locator , int timeOutInSecond , int pollingEveryInMilosec) {
		log.info(locator);
		WebDriverWait wait = getWait(timeOutInSecond , pollingEveryInMilosec);
		wait.until(ExpectedConditions.visibilityOf(locator));
			
		}
	public void waitElement(WebDriver driver , WebElement element , long timeout) {
		WebDriverWait wait = new WebDriverWait(driver , timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element found..."+element.getText());
	}
	public static  WebElement waitForElement(WebDriver driver , WebElement signin, long time) {
		WebDriverWait wait = new WebDriverWait(driver , time);
		 By phonenumber = null;
		return wait.until(ExpectedConditions.elementToBeClickable(phonenumber));
		
}
}

package com.hybridframework.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

	private WebDriver driver;
	private Logger log = Logger.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver=driver;
		log.debug("JavaScriptHelper :" + this.driver.hashCode());
		
	}
	public Object executeScript(String script, int x, int y) {
		JavascriptExecutor exe = (JavascriptExecutor)driver;
		log.info(script);
		return exe.executeScript(script);
	}
	
		public Object executeScript1(String script ,Object...args) { 
			JavascriptExecutor exe = (JavascriptExecutor)driver;
			log.info(script);
			return exe.executeScript(script, args);
}
		
		public void scrollToElement(WebElement element) {
			executeScript("window.scroolTo(arguments[0].argument[1])",element.getLocation().x,element.getLocation().y);
			log.info(element);
		}
		public void scrollToElementAndClick(WebElement element) {
			scrollToElement(element);
			element.click();
			log.info(element);
		}
		public void scrollIntoView(WebElement element) {
			executeScript1("arguments[0].scrollIntoView()",element);
			log.info(element);
}
		public void scrollToViewAndClick(WebElement element) {
			scrollIntoView(element);
			element.click();
			log.info(element);
		}
		
		public void scrollDownVertically() {
			executeScript("window.scrollTo(0,document.body.scrollHeight)");
		}
		public void scrollUpVertically() {
			executeScript("window.scrollTo(0,-document.body.scrollHeight)");
		}
		public void scrollDownPixel() {
			executeScript("window.scrollBy(0,1500)");
		}
		public void scrollUpByPixel() {
			executeScript("window.scrollTo(0,document.-1500)");
		}
		public void ZoomInBypercentage() {
			executeScript("document.body.style.zoom='40%'");
		}
		public void ZoomBypercentage() {
			executeScript("document.body.style.zoom='100%'");
		}
		
		private void executeScript(String string) {
			// TODO Auto-generated method stub
			
		}
		}
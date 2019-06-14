package com.hybridframework.helper;

import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserHelper {

	private WebDriver driver ;
	private Logger log = Logger.getLogger(BrowserHelper.class);
	
	public BrowserHelper(WebDriver driver) {
	this.driver = driver;
	log.debug("BrowserHelper :" + this.driver.hashCode());
}

	public void goBack() {
		driver.navigate().back();
		log.info("");
	}
	public void goForward() {
		driver.navigate().forward();
		log.info("");
	}
	public void refresh() {
		driver.navigate().refresh();
		log.info(" ");
	}
	public Set<String> getwindowhandles() {
		log.info(" ");
		return driver.getWindowHandles();
	}
	public void SwitchToWindow(int index) {
		LinkedList<String> windowsID = new LinkedList<String>(getwindowhandles());
		
		if(index<0 || index> windowsID.size()) {
		throw new IllegalArgumentException("Invalid Index :" +index);
		}
		driver.switchTo().window(windowsID.get(index));
		log.info(index);
	}
	
	public void switchToParentWindow() {
		LinkedList<String> windowsID = new LinkedList<String>(getwindowhandles());
		driver.switchTo().window(windowsID.get(0));
		log.info(" ");
		
	}
		public void switchToParentWithChildClose() {
			LinkedList<String> windowsID = new LinkedList<String>(getwindowhandles());
			
			for(int i = 1;i <windowsID.size();i++) {
				log.info(windowsID.get(i));
				driver.close();
			}
		
		switchToParentWindow();
		}
		public void switchToFrame(String nameOrID) {
			driver.switchTo().frame(nameOrID);
		log.info(nameOrID);
		
	}
}
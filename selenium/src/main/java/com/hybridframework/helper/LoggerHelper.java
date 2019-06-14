package com.hybridframework.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import Utility.ResourceHelper;

public class LoggerHelper {

	private static boolean root = false;
	
	public static Logger getlogger(Class clas) {
		if(root) {
			return Logger.getLogger(clas);
		}
	//String log4jLocation = System.getProperty("user.dir")+"src/main/Resources/log4j.properties";
	//	or
	PropertyConfigurator.configure(ResourceHelper.getResourcePath("src/main/Resources/log4j.properties"));
	root = true;
	return Logger.getLogger(clas);
	}
}

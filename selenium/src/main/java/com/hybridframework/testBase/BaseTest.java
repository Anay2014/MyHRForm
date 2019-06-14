package com.hybridframework.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;

import com.hybridframework.excelReader.Excel_reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.apache.xpath.internal.operations.Or;

public class BaseTest {
	public static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(BaseTest.class.getName());
	public WebDriver driver;
	public Properties OR ;
	public FileInputStream file;
	public File f1 ;
	Excel_reader excelreader;
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;
	
	//Dependencies we have to add ,these help us to get the html report , and in case Testcases fails .
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir")+"src/main/java/com/hybridframework/report/test" + formater.format(calendar.getTime())+".html", false);
		
	}
	
	public void getBrowser(String browser) {
		
		if(System.getProperty("os.name").contains("Window")) {
			if(browser.equalsIgnoreCase("firefox")) {
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver" ,System.getProperty("user.dir")+"/driver/geckodriver");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver" ,System.getProperty("user.dir")+"/driver/chromedriver");
				driver = new ChromeDriver();
		}
	}
       
}
	public void loadPropertiesFile() throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
	  OR = new Properties();
	  f1 = new File(System.getProperty("user.dir")+"src/main/java/com/hybridframework/config/config.properties");
	  file = new FileInputStream(f1);
	  OR.load(file);
	  logger.info("loading config.properties");
	  f1 = new File(System.getProperty("user.dir")+"src/main/java/com/hybridframework/config/or.properties");
	  file = new FileInputStream(f1);
	  OR.load(file);
	  logger.info("loading or.properties");
	  f1 = new File(System.getProperty("user.dir")+"/src/main/java/com/hybridframework/properties/homepage.properties");
	  file = new FileInputStream(f1);
	  OR.load(file);
	  logger.info("loading config.Homepage.properties");
	}
	public String getScreenShot(String imagename) throws IOException {
		
		if(imagename.equals("")) {
			imagename = "blank";
		}
	  File image = ((TakesScreenshot)driver). getScreenshotAs(OutputType.FILE);
	  String imagelocation = System.getProperty("user.dir")+"/src/main/java/com/hybridframework/screenshot/";
	    
	   Calendar calender  = Calendar.getInstance();
	   SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
	   String actualImageName = imagelocation+imagename+"_"+formater.format(calender.getTime())+".png";
	   File destFile = new File(actualImageName);
	   	   
	   FileUtils.copyFile(image, destFile);
	return actualImageName;
	}
	public WebElement waitForElement(WebDriver driver ,long time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver , time);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	public WebElement waitForElementWithPollingInterval(WebDriver driver ,long time,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver , time);
		wait.pollingEvery(5,TimeUnit.SECONDS);
		//(mostly we get noSuchelementException)
		wait.ignoring(Exception.class);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
}
	
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus()==ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName()+"Test is pass");
		}else if (result.getStatus()==ITestResult.SKIP){
			test.log(LogStatus.SKIP, result.getName()+"Test is skipped and reason is :"+result.getThrowable());
			
		}else if (result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.FAIL, result.getName()+"Test is failled and reason is :"+result.getThrowable());
		String screen = getScreenShot("");
		test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		}
		else if (result.getStatus()==ITestResult.STARTED) {
			test.log(LogStatus.INFO , result.getName()+ " test is started ");
			
		}
	}
	@AfterMethod()
	public void aftermethod(ITestResult result) throws IOException {
		getResult(result);
	}
	@BeforeMethod
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName()+"test started");
	}
	
	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		extent.endTest(test);
		extent.flush();
		
	}

	public WebElement getLocator(String Locator) throws Exception {
		String[] split = Locator.split (":");
		String locatorType = split[0];
		String locatorValue = split[1];
		
		
		if (locatorType.toLowerCase().equals("id")){
			return driver.findElement(By.id(locatorValue));}
			else if (locatorType.toLowerCase().equals("name")){
				return driver.findElement(By.name(locatorValue));
			}
		    else if (locatorType.toLowerCase().equals("classname")||locatorType.toLowerCase().equals("class")){
					return driver.findElement(By.className(locatorValue));	
		    }
		    else if (locatorType.toLowerCase().equals("tagname")||locatorType.toLowerCase().equals("tag")){
				return driver.findElement(By.tagName(locatorValue));
		    }
		    else if (locatorType.toLowerCase().equals("linktext")||locatorType.toLowerCase().equals("link")){
				return driver.findElement(By.linkText(locatorValue));
		    }
		    else if (locatorType.toLowerCase().equals("partiallinktext")){
				return driver.findElement(By.partialLinkText(locatorValue));
		    }
		    else if (locatorType.toLowerCase().equals("cssselctor")||locatorType.toLowerCase().equals("css")){
				return driver.findElement(By.cssSelector(locatorValue));
		    }
		    else if (locatorType.toLowerCase().equals("xpath")){
				return driver.findElement(By.xpath(locatorValue));
		    }
				else{
					throw new Exception("Unknown locator type" + locatorType + " ' ");
				}
		}
		 
			public WebElement getWebElement(String locator) throws Exception  {
				return getLocator(OR.getProperty(locator));
				}
			
		public String[][] getData(String excelName,String sheetName) {
			String excellocation =System.getProperty("user.dir")+"src\\main\\java\\com\\hybridframework\\data\\truweight.xlsx";
			System.out.println(excellocation);
		excelreader = new Excel_reader();
	   return excelreader.getExcelData(excellocation, sheetName);
	  
		}
			    
			    
	
		    
			
		
		
		
		
	
	
		
	
	private org.openqa.selenium.WebElement getLocators(String property) {
		// TODO Auto-generated method stub
		return null;
	}
	private int getWebElements(String locator) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void getPropertiesData() {
		
	}
	public static void main(String[] args) throws IOException{
		BaseTest  test = new BaseTest();
		//test.getBrowser("firefox");
		test.loadPropertiesFile();
		//System.out.println(test.OR.getProperty("url"));
		//System.out.println(test.OR.getProperty("url"));
		test.getWebElements(test.OR.getProperty("username"));
		
		
		
	}
	
}
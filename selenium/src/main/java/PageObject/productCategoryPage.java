package PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridframework.helper.LoggerHelper;
import com.hybridframework.helper.WaitHelper;
import com.hybridframework.testBase.Config;

public class productCategoryPage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getlogger(productCategoryPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath= "//*[id = 'phoneNumber']" )
	WebElement signin;

	

	public productCategoryPage(WebDriver driver) {
		this.driver=driver;
	PageFactory.initElements(driver, this);
	 waitHelper = new WaitHelper(driver);
	waitHelper.waitForElement(driver,signin ,new Config().GetExplicitWait());
	}
}

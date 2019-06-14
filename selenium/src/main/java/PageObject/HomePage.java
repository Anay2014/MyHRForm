package PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridframework.helper.LoggerHelper;
import com.hybridframework.helper.WaitHelper;
import com.hybridframework.testBase.Config;

public class HomePage {

	WebDriver driver ;
	private final Logger log = LoggerHelper.getlogger(HomePage.class);
	WaitHelper waitHelper;
	
	
	@FindBy(xpath="//*[id = 'clientPhone']")
	public WebElement phoneNumber ;
	
	@FindBy(xpath="//*[type = 'button']")
	public WebElement  button ;
	
	@FindBy(xpath="//*[id = 'forPresentation']")
	public WebElement forPresentation ;
	
	@FindBy(xpath="//*[id = 'submit']")
	public WebElement  submit  ;
	
	@FindBy(xpath="//*[id = 'navigate_next']")
	public WebElement navigate_next   ;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	PageFactory.initElements(driver, this);
	waitHelper = new WaitHelper(driver);
	waitHelper.waitForElement(driver,phoneNumber,new Config().GetExplicitWait());
	}
	public void mouseOver(String data) {
		log.info("Doing mouse over on :"+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	public productCategoryPage clickOnIteam(String data) {
		log.info("clickin on :"+data);
		driver.findElement(By.xpath("//*[Contains(text() , button,"+data+")]")).click();
		return new productCategoryPage(driver);
	}
//public productCategoryPage clickOnMenu(WebElement element) {
	//log.info("clickin on :"+element.getText());
//	element.click();
	//return new productCategoryPage(driver);
//}
}


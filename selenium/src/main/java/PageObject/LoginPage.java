package PageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hybridframework.helper.GenericHelper;
import com.hybridframework.helper.LoggerHelper;
import com.hybridframework.helper.WaitHelper;
import com.hybridframework.testBase.Config;

public class LoginPage {
WebDriver driver ;
private final static Logger log = LoggerHelper.getlogger(LoginPage.class);
WaitHelper waitHelper;

@FindBy(xpath= "//*[id = 'phoneNumber']" )
WebElement signin;

@FindBy(xpath= "//*[type = 'button']" )
WebElement button;

@FindBy(xpath="//*[id = 'submit']")
public WebElement  submit ;


public LoginPage(WebDriver driver) {
this.driver = driver ;
PageFactory.initElements(driver,this);
waitHelper = new WaitHelper(driver);
waitHelper.waitForElement(driver,signin, new Config().GetExplicitWait());
}
public void clickOnGetDetailLink() {
	log.info("clicked on get detail link....");
button.click();
}
//public void phonenumber(String emailAddress) {
	//log.info("enter the email Address .."+emailAddress);
	//this.emailAddress.sendkeys(emailAddress);
//}
//public void enterpassword(String password) {
	//log.info("entering password..."+password);
	//this.password.sendkeys(password);
//}
public HomePage clikOnSubmitButton() {
	log.info("clicking on submit button..");
	submit.click();
	return new HomePage(driver);
}
public boolean verifySuccessLoginMsg() {
	return new GenericHelper().isDisplayed(button);
	
}

}
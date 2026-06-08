package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtlis;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	private By Firstname = By.id("input-firstname");
	private By Lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confpwd = By.id("input-confirm");
	
	private By subscribeyes = By.xpath("(//label[@class=\"radio-inline\"])[position()=1]/input[@type=\"radio\"]");
	private By subscribeno = By.xpath("(//label[@class=\"radio-inline\"])[position()=2]/input[@type=\"radio\"]");

	private By agreecheckbox = By.name("agree");
	private By continuebtn = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successmsg = By.cssSelector("div#content h1");
	private By logoutlink = By.linkText("Logout");
	private By registerlink = By.linkText("Register");


	public RegisterPage(WebDriver driver) {
		this.driver =driver;
		eleUtils = new ElementUtil(driver);
	}
	
	public boolean userRegisteration(String Firstname, String Lastname, String telephone,
			String password, String subscribe) {

		eleUtils.waitForElementVisible(this.Firstname, AppConstants.DEFAULT_TIMEOUT).sendKeys(Firstname);
		eleUtils.doSendKeys(this.Lastname, Lastname);
		eleUtils.doSendKeys(this.email, StringUtlis.getRandomEmailId());
		eleUtils.doSendKeys(this.telephone, telephone);
		eleUtils.doSendKeys(this.password, password);
		eleUtils.doSendKeys(this.confpwd, password);
		if(subscribe.equalsIgnoreCase("yes"))
		{
			eleUtils.doClick(subscribeyes);
		}
		else {
			eleUtils.doClick(subscribeno);

		}
		eleUtils.doClick(agreecheckbox);
		eleUtils.doClick(continuebtn);
		
		if(eleUtils.waitForElementVisible(successmsg, AppConstants.MEDIUM_DEFAULT_TIMEOUT).getText().contains(AppConstants.REGISTER_SUCCESS_MESSG))
		{
			eleUtils.doClick(logoutlink);
			eleUtils.doClick(registerlink);
			return true;
		}
		return false;
	} 
	
}

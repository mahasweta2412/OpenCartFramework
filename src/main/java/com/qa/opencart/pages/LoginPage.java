package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	//1.private By locator
	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By loginbtn = By.xpath("//input[@value=\"Login\"]");
	private final By forgotpwdlink = By.linkText("Forgotten Password");
	private final By registerlink = By.linkText("Register");
	
	//2.public page constructor
	public LoginPage(WebDriver driver) {
		this.driver =driver;
		eleUtils = new ElementUtil(driver);
	}
	
	@Step("getting login page Title")
	//3. public page actions/methods
	public String getLoginPageTitle()
	{
		String title= eleUtils.waitForTitleIs(LOGIN_PAGE_TITLE,DEFAULT_TIMEOUT);
		System.out.println("Login Page Title"+ title);
		return title;
	}
	
	public String getLoginPageUrl()
	{
		String Url= eleUtils.waitForURLContains(LOGIN_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
		System.out.println("Login Page URL"+ Url);
		return Url;
	}
	
	public boolean isForgotPwdLinkExist()
	{
		return eleUtils.isElementDisplayed(forgotpwdlink);
	}
	@Step("login with username:{0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd)
	{
		System.out.println("Username Creds"+ username + " " + pwd);
		eleUtils.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
		eleUtils.doSendKeys(password, pwd);
		eleUtils.doClick(loginbtn);
		
//	String title = eleUtils.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
//		System.out.println("Account page Title" + title);
//		return title;
		//if we are landing on next page then it is methods responsiblity to return page
		 return new AccountsPage(driver); //creates new object in heaps
	}
	
	public RegisterPage navigateToRegisterpage() {
		eleUtils.clickWhenReady(registerlink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}
	
	
	
	
}

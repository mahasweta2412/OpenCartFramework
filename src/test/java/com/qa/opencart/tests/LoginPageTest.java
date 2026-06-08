package com.qa.opencart.tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.opencart.constants.AppConstants.*;
//Testng code --testcases
@Feature("F 50: Open cart - login feature")
@Epic("Epic 100:design pages for open cart applications")
@Story("US 101: implement login page for open cart applications")
public class LoginPageTest extends BaseTest {
	@Description("Checking open cart login page title..")
	@Severity(SeverityLevel.MINOR)
	@Owner("Maha")
	@Test
	public void loginPageTitleTest()
	{
		String actualTitle= loginpage.getLoginPageTitle();
		ChainTestListener.log("Checking login page title"+actualTitle);
		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE);
	}
	@Description("Checking open cart login..")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Maha")
	@Test
	public void loginPageUrlTest()
	{
		String actualUrl= loginpage.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPwdLinkExistTest()
	{
		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Test(priority =Short.MAX_VALUE)
	public  void dologinTest() {
		accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
	
	@Test(enabled = true, description = "Forgot pwd check")
		public void forgotpwd() {
		System.out.println("forgot pwd");
	}
	
}

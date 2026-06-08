package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void registerSetup() {
		registerpage =loginpage.navigateToRegisterpage();
	}
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object [][] {
			{"Swetha","siva","9999999999", "Maha@12", "yes"},
			{"San","ram","9999999949", "San@05", "no"},
			{"Kalpana", "gowtham","6789045340","Kal@45","yes"},
			{"Mercy", "go","6789045340","mercy@34","no"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegData() {
		Object regdata[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regdata;
	}

	@Test(dataProvider ="getUserRegData")
	public void userRegisterTest(String Firstname, String Lastname,String telephone,
			String password, String subscribe) {
		Assert.assertTrue(registerpage.userRegisteration(Firstname,Lastname,telephone,password,subscribe));
	}

}

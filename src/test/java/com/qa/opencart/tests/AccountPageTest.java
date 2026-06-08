package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

public class AccountPageTest extends BaseTest {
	@BeforeClass //BT-->BC
	public void accPageSetup() {
	 accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest() {
		Assert.assertEquals(accpage.getAccPageTitle(),HOME_PAGE_TITLE);
	}
	
	@Test
	public void accPageUrlTest() {
		Assert.assertTrue(accpage.getAccPageUrl().contains(HOME_PAGE_FRACTION_URL));
	}
	@Test
	public void accPageHeadersTest() {
		List<String>accHeaderList =accpage.getAccPageHeaders();
		Assert.assertEquals( accHeaderList, expectedAccHeaderList);
		
	}
}

package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest {
	
	@BeforeClass
	public void searchSetup() {
		accpage =
			loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void searchTest() {
		searchResultpage =accpage.doSearch("macbook");
		int actResultCount = searchResultpage.getResultProductCount();
		Assert.assertEquals(actResultCount, 3);
	}

}

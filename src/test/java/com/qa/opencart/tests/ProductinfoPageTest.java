package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductinfoPageTest extends BaseTest {
	
	@BeforeClass
	public void ProductinfoSetup() {
		 accpage = loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		}
	//dataprovider: test data
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object [][] {
			{"macbook","MacBook Pro"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	@Test(dataProvider="getProductTestData")
	public void productHeaderTest(String searchkey, String productname) {
		searchResultpage = accpage.doSearch(searchkey);
		productinfopage = searchResultpage.selectProduct(productname);
		String productheader = productinfopage.getProductHeader();
		Assert.assertEquals(productheader,productname);
	}
	
	@DataProvider
	public Object[][] getProductImageTestData() {
		return new Object [][] {
			{"macbook","MacBook Pro", 4},
			{"macbook","MacBook Air", 4},
			{"imac","iMac", 3},
			{"samsung","Samsung SyncMaster 941BW", 1},
			{"samsung","Samsung Galaxy Tab 10.1", 7}
		};
	}
	@DataProvider
	public Object[][] getProductSheetData(){
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getProductCSVData(){
		return CSVUtil.csvData("product");
		
	}
	@Test(dataProvider="getProductCSVData")
	public void productimageCountTest(String searchkey, String productname, String expimgcount) {
		searchResultpage = accpage.doSearch(searchkey);
		productinfopage = searchResultpage.selectProduct(productname);
		int actualimgcount = productinfopage.getProductImageCount();
		Assert.assertEquals(String.valueOf(actualimgcount), expimgcount);
	}
	
	
	@Test
	public void productInfoTest() {
		searchResultpage = accpage.doSearch("macbook");
		productinfopage = searchResultpage.selectProduct("MacBook Pro");
		Map<String,String> acualproductDetailsMap = productinfopage.getProductDetailsMap();
		
		SoftAssert softAssert = new SoftAssert();
		Assert.assertEquals(acualproductDetailsMap.get("Brand"), "Apple");
		Assert.assertEquals(acualproductDetailsMap.get("Product Code"), "Product 18");
		Assert.assertEquals(acualproductDetailsMap.get("Reward Points"), "800");
		Assert.assertEquals(acualproductDetailsMap.get("Availability"), "Out Of Stock");
		Assert.assertEquals(acualproductDetailsMap.get("productprice"), "$2,000.00");
		Assert.assertEquals(acualproductDetailsMap.get("exTaxprice"), "$2,000.00");

		softAssert.assertAll();
		
	}

}


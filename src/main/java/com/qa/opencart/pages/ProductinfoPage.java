package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.TreeMultimap;
import com.qa.opencart.utils.ElementUtil;

public class ProductinfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	private final By productHeader = By.tagName("h1");
	private final By productimages = By.cssSelector("ul.thumbnails img");
	private final By productMetaData = By.xpath("//div[@id=\"content\"]//ul[@class = 'list-unstyled'][1]/li");
	private final By productpriceData = By.xpath("	//div[@id=\"content\"]//ul[@class = 'list-unstyled'][2]/li");

	private  Map<String,String>productMap;
	
	public ProductinfoPage(WebDriver driver) {
		this.driver =driver;
		eleUtils = new ElementUtil(driver);
	}
	
	public String getProductHeader() {
		String header=
			eleUtils.waitForElementVisible(productHeader,DEFAULT_TIMEOUT).getText();
		System.out.println("Header Name: "+ header);
		return header;
	}
	public int getProductImageCount() {
		int imagecount=
				eleUtils.waitForAllElementsVisible(productimages,DEFAULT_TIMEOUT).size();
		System.out.println("Total no.of image present: "+ imagecount);
		return imagecount;
	}
	//wrapper function
	public Map<String,String> getProductDetailsMap() {
	//	productMap = new HashMap<String, String>(); //changing to linkedhashmap -- for insertions order
		//treemap for sorted order for key
		productMap = new TreeMap<String, String>();
		productMap.put("productHeader", getProductHeader()); 
		productMap.put("productimages", String.valueOf(getProductImageCount())); 
		getProductMetaData();
		getProductpriceData();
		System.out.println("Full details:"+productMap);
		return productMap;
	}
	//Brand: Apple //spliting into 2 and storing in hasmap
	private void getProductMetaData() {
	List<WebElement> productmetadata= eleUtils.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
			for(WebElement e:productmetadata)	
			{
				String data =e.getText();
				String meta[]= data.split(":");
				String metaKey = meta[0].trim();
				String metaVal = meta[1].trim();
				productMap.put(metaKey, metaVal);
			}
	}
	//$2,000.00
	//Ex Tax: $2,000.00
	private void getProductpriceData()
	{
		List<WebElement> pricelist = eleUtils.waitForAllElementsVisible(productpriceData, DEFAULT_TIMEOUT);
		String productprice = pricelist.get(0).getText();
		String exTaxprice= pricelist.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productprice);
		productMap.put("exTaxprice", exTaxprice);
	}

}

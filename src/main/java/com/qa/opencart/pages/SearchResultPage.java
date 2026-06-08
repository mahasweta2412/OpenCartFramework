package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	private final By productcount = By.cssSelector("div.product-thumb");
	public SearchResultPage(WebDriver driver) {
		this.driver =driver;
		eleUtils = new ElementUtil(driver);
	}
	
	public int getResultProductCount() {
		int searchcount = 
			eleUtils.waitForAllElementsVisible(productcount, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("Total no.of products:"+ searchcount);
		return searchcount;
	}
	
	public ProductinfoPage selectProduct(String productName) {
		System.out.println("Product Name:" + productName );
		eleUtils.doClick(By.linkText(productName));
		
		return new ProductinfoPage(driver);
	}
	

}

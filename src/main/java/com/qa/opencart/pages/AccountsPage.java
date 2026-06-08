package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtils;
	
	private final By headers = By.cssSelector("div#content > h2");
	private final By searchbar = By.name("search");
	private final By searchbtn = By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver) {
		this.driver =driver;
		eleUtils = new ElementUtil(driver);
	}

	public String getAccPageTitle() 
		{
			String title= eleUtils.waitForTitleIs(HOME_PAGE_TITLE,DEFAULT_TIMEOUT);
			System.out.println("Home Page Title"+ title);
			return title;
		}	
	public String getAccPageUrl() 
	{
		String url= eleUtils.waitForURLContains(HOME_PAGE_FRACTION_URL,DEFAULT_TIMEOUT);
		System.out.println("Home Page Title"+ url);
		return url;
	}	
	
	public List<String> getAccPageHeaders() {
		List<WebElement> headerLists = eleUtils.getElements(headers);
		List<String> headerValList = new ArrayList<String>();
		for(WebElement e : headerLists) {
			String text = e.getText();
			headerValList.add(text);
		}
		System.out.println(headerValList);
		return headerValList;
	}
	public SearchResultPage doSearch(String search) {
		System.out.println("Search key :"+ search);
		eleUtils.doSendKeys(searchbar, search);
		eleUtils.doClick(searchbtn);
		return new SearchResultPage(driver);
		
	}
}

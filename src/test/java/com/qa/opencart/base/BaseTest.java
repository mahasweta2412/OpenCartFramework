package com.qa.opencart.base;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductinfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultPage;
import com.qa.opencart.utils.LogUtil;

import io.qameta.allure.Description;

import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.constants.AppConstants.*;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	
	DriverFactory df;
	protected Properties prop;
	//protected - work within the class, outside of the package. Child class can use that 
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected SearchResultPage searchResultpage;
	protected ProductinfoPage productinfopage;
    protected RegisterPage registerpage;
    
	private static final Logger log = LogManager.getLogger(BaseTest.class);

	
    @Description("Init the driver and properties")
    @Parameters({"browser"})
    @BeforeTest
	
	public void setup(String browserName) {
		
		df = new DriverFactory();
		prop = df.initProp();//This initializes the properties file.
		//browsername is passed from .xml file
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}
    
    @AfterMethod //will be running after each @test method
    public void attachScreenshot(ITestResult result) {
    	if(!result.isSuccess()) {
    		log.info("--Screenshots is taken----");
    		ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
    	}//if i need every test i need to take screenshot then remove if condition
    }
	
    @Description("Closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
		log.info("--Driver is closed----");
	}

}

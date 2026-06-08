package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

public class DriverFactory {
	WebDriver driver;
	Properties prop;
	OptionsManager optionsmanager;
	
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
	public static String highlight;
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	@Step("init driver with properties: {0}")
	public WebDriver initDriver(Properties prop)
	{
		log.info("properties"+ prop);
		String browsername= prop.getProperty("browser");
//		System.out.println("Browsername : "+ browsername);
		log.info("Browsername"+ browsername);
		optionsmanager = new OptionsManager(prop);
		
		highlight = prop.getProperty("highlight");
		
		switch(browsername.toLowerCase().trim()) {
		case "chrome":
			tDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
		//	driver = new ChromeDriver(optionsmanager.getChromeOptions());
			break;
		case "firefox":
			tDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		//	driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
			break;
		case "edge":
			tDriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
		//	driver = new EdgeDriver(optionsmanager.getEdgeOptions());
			break;
		case "safari":
			driver = new SafariDriver();
			break;
			
		default:
			log.info("Plz provide right browser"+browsername );
		//	System.out.println("Plz provide right browser"+ browsername);
			throw new BrowserException("==INVALID BROWSER===");
		}
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
			
			
		}
	
	public static WebDriver getDriver() {
		return tDriver.get();
	}
	/** This is used to initilise the properties */
	//mvn clean install -D (supplying command line ) -Denv="qa"
	public Properties initProp() {
		String envName= System.getProperty("env");
		System.out.println("Running tests on env " + envName);
		FileInputStream ip = null;
		prop = new Properties();
		try {
		if(envName == null) {
			log.warn("Env is null running the test on Prod env by default..");
		//	System.out.println("Env is null running the test on Prod env by default..");
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		}
		else
		{
			log.info("Running tests on env:"+ envName);
			//System.out.println("Running tests on env:"+envName);
			switch(envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/config/config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
				
				default:
					log.error("\"==Invalid Environment===\" +envName");
					throw new FrameworkException("==Invalid Environment===" +envName);
			}
		}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		}	
		/*prop = new Properties();
		//get connection with properties - sometimes will get file not found exceptions so need to handle it
		try {
			FileInputStream ip = new FileInputStream("./src\\test\\resources\\config\\config.properties");
			prop.load(ip); //load all values when loading we can handle that exceptions	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}*/
	
	public static File getScreenshotFile() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;//temp dir
	}
	
	}

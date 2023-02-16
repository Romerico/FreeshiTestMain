package com.base;

import com.utilities.ReadConfig;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;


public class BaseClass {
	static ReadConfig readconfig = new ReadConfig(); // Creating object
	// Integrating data from ReadConfig
	public static String baseURL = readconfig.getApplicationURL();
	public String username1 = readconfig.getUsername1();
	public String password = readconfig.getPassword();
	public String browser = readconfig.getbrowser();
	public static ChromeOptions handlingSSL = new ChromeOptions();
	public static FirefoxOptions handlingSSL0 = new FirefoxOptions();
	public static InternetExplorerOptions handlingSSL1 = new InternetExplorerOptions();
	public static WebDriver driver;
	public static Logger logger;

	public WebDriver getDriver() {
		return driver;
	}


	@BeforeClass
	public void setup() {
		if (browser.equalsIgnoreCase("chrome")) {
			//Create instance of ChromeOptions Class

			//Using the accept insecure cert method with true as parameter to accept the untrusted certificate
			handlingSSL.setAcceptInsecureCerts(true);
			handlingSSL.addArguments("--lang=en");

			//Creating instance of Chrome driver by passing reference of ChromeOptions object
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver = new ChromeDriver(handlingSSL);
			// Initialization // Logger initiated within the setup method
			logger = Logger.getLogger("Freshii");// Project Name
			PropertyConfigurator.configure("log4j.properties"); // Added Logger
			logger.setLevel(Level.DEBUG); // to get the debug log
			logger.debug("Debug logging has started ");


		} if(browser.equalsIgnoreCase("firefox")) {
			handlingSSL.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(handlingSSL0);
			logger = Logger.getLogger("Freshii");// Project Name
			PropertyConfigurator.configure("log4j.properties"); // Added Logger
			logger.setLevel(Level.DEBUG); // to get the debug log
			logger.debug("Debug logging has started ");



			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver0320.exe");
			logger.info("Opening base URL - in FireFox");
		}


		 if (browser.equalsIgnoreCase("ie")) {
			 handlingSSL.setAcceptInsecureCerts(true);
			 driver = new InternetExplorerDriver(handlingSSL1);
			 logger = Logger.getLogger("Freshii");// Project Name
			 PropertyConfigurator.configure("log4j.properties"); // Added Logger
			 logger.setLevel(Level.DEBUG); // to get the debug log
			 logger.debug("Debug logging has started ");



			 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
			logger.info("Opening base URL - in Internet Explorer");
		}
		 if(browser.equalsIgnoreCase("mobile-web")){
			 handlingSSL.setAcceptInsecureCerts(true);


			 Map<String, String> mobileEmulation = new HashMap<>();

			 mobileEmulation.put("deviceName", "Pixel 5");

			 handlingSSL.setExperimentalOption("mobileEmulation", mobileEmulation);

			 driver = new ChromeDriver(handlingSSL);


			 logger = Logger.getLogger("Freshii");// Project Name
			 PropertyConfigurator.configure("log4j.properties"); // Added Logger
			 logger.setLevel(Level.DEBUG); // to get the debug log
			 logger.debug("Debug logging has started ");



		 }
		driver.get(baseURL);
		driver.manage().window().maximize();
	}



	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}



	public static String randomestring() 
	{
		String generatedString1 = RandomStringUtils.randomAlphabetic(5); // generate random char string with the specified values passed 
		return (generatedString1);										 
	}

	public static String randomeNum() 
	{
		String generatedString2 = RandomStringUtils.randomNumeric(4);// generate random digits with the specified values passed
		return (generatedString2);
	}
	
}
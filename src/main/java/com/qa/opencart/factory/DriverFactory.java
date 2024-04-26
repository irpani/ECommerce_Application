package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	// Just Created One Method for Driverfactory,then Immediately go to Loginpage
	// Later we will Create How to Launch Remote WebDriver Different Env

	// WheN Ever u write Utilities In Framework Please write the Document so that It
	// will helpful Others
	/**
	 * 
	 * This Method is Initializing the driver On the basis Of Browser Type S
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver init_Driver(Properties prop) { // Now u can see the Advantage Of prop here to passing the prop
													// Parmeter
		optionsManager = new OptionsManager(prop); // Thesame prop we can pass here
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser Name is " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			// you can remember this we did this new ChromeOptions(co);
			// optionsManager.getChromeOptions() --> It returns the co
			driver = new ChromeDriver(optionsManager.getChromeOptions());
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver(optionsManager.getFirefoxeOptions());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Browser Not Matched..." + browserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/**
	 * This method is reading the propertie from Config.properies file
	 * 
	 * @return
	 */
	public Properties initProp() {

		prop = new Properties(); // Properties Object file Created

		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties"); // For this I add
																										// 1 Exception
			prop.load(ip); // for this I add One More Catch Block
			// Now in prop All the( Key ,Value) are Available ....
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}

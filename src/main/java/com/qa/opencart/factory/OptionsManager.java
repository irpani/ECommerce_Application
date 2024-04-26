package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// What I need firt Of All
	// All the Properties are defined headless Incongnito Defined under
	// Config.properties
	// I need to use the propertie reference here only So that I Can use it

	// Can I take for this class it's own Properties drfined like Private Properties

	private Properties prop; // Right Now It is pointing to null

	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	// Co given By this Method How to call this Method in Driver factory
	public ChromeOptions getChromeOptions() {

		co = new ChromeOptions();
		// Which Method we have to Use for headless?
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("******Running Chrome Browser In Headless mode******");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		return co;
	}

	public FirefoxOptions getFirefoxeOptions() {

		fo = new FirefoxOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("===Running Firefox Browser In Headless mode======");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--incognito");
		return fo;
	}

	public EdgeOptions getEdgeOptions() {

		eo = new EdgeOptions();

		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("===Running Edge Browser In Headless mode======");
			eo.addArguments("--headless");
		}

		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			eo.addArguments("--incognito");
		return eo;
	}

	// You can also write Remaining Options here ...
	// When we Write & Execute the testCases through Grid At that time we start add
	// more Options
}

package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoNewPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.SearchPage;

//Don't Use Unnecessary Inheritance Just create Object of Driverfactory start utlizing here
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	public Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchPage searchPage; // Do I need to create Object for it A: No Object will be already created at
	protected ProductInfoPage productInfoPage; // Runtime
	protected ProductInfoNewPage productInfoNewPage;
	// Protected Means Either in same Package or Other Package I can use it
	// Public Means Every where we can access it
	// If No Access Modifier It is default

	@BeforeTest
	public void setUp() {

		df = new DriverFactory();
		prop = df.initProp();
		driver = df.init_Driver(prop); // This Method wil gives us driver so Inilize webDriver Give reference here

		loginPage = new LoginPage(driver);
		AccountsPage accPage = new AccountsPage(driver);

	}

	@AfterTest
	public void testDown() {
		driver.quit();
		// driver.close();

	}

}

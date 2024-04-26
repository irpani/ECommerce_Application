package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

// Page Class is just a Java Class we cann't Run this ..
public class LoginPage {
	// (1),(2),(3),(4),(5) These Combinedly we called as Templte
	// Before Writing LoginPage You need a driver
	// How do you will get driver??
	// Q:Which Class is responsible for that (A): DriverFactory

	// Structure of the Page Class According to POM Page Object Model we have to
	// follow

	// When Ever you are Creatin Page Every Page Have it's own webDriver
	// I will tell u y we are using private WebDriver
	// wait for 5 to 10 min
	// A:As per the POM Design pattern This is the Basic Rule u have to follow
	// Private varible Cann't expose to another Page, If we want to access access
	// Via
	// Public Layer Only

	// (1).Private WebDriver
	private WebDriver driver; // To Intialize this global var we are using Constructor
	private ElementUtil elementUtil;
	// (2).Private By Locator's
	// -------------------------
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By register = By.linkText("Register");
	private By forgotpwdlink = By.linkText("Forgotten Password");
	private By errorMessage = By.cssSelector("div.alert.alert-danger.alert-dismisaable");

	// (3).Page Constructor:
	// -----------------------
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// (4).Page Actions/Methods:
	// --------------------------

	public String getLoginPageTitle() {
		String title = elementUtil.waitForTitleIsAndFetch(10, "Account Login");
		System.out.println("Login Page Title is" + title);
		return title;
	}

	public String getLoginPageUrl() {
		String pageURL = elementUtil.waitForURLContainsAndFetch(10, "route=account/login");
		System.out.println("Login Page Url is" + pageURL);
		return pageURL;
	}

	public boolean isForgotPwdLinkExist() {
		// return driver.findElement(forgotpwdlink).isDisplayed();
		return elementUtil.waitForElementVisible(forgotpwdlink, 10).isDisplayed();
	}

	// (5).Page Navigation:
	// -------------------
	// Positive TestCase with Right UsserName & Password
	public AccountsPage doLoginPage(String un, String pwd) {

		elementUtil.waitForElementVisible(emailID, 10).sendKeys(un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	// Please Start Writing more test cases here ...... And Validating in
	// LoginPageTest

	// Check -ve LoginScenario with Wrong INPUT & PASSWORD
	// Check All the Links Available (or) Not ...
	//

	// Note:- Using @FindBy() --- Completely Deprecated from Market
	// WebElement userName
	// @FindBy Annotation of PageFactory will leads to get StaleElment Reference
	// Exception
	// Selenium Experts advised not to use wit POM page Object Model

}

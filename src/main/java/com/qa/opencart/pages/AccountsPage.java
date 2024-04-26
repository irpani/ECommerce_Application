package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstansts;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	// (1).Private WebDriver
	private WebDriver driver;
	private ElementUtil elementUtil;
	// (1),(2),(3),(4),(5) These Combinedly we called as Templte
	// (2).Private By Locator's of AccoutPage
	// ----------------------- -------------
	private By logoutlnk = By.linkText("Logout");
	private By headerList = By.tagName("h2");
	private By linksList = By.tagName("a");

	// (2(a).Private By Locator's of SearchPage
	// ----------------------- -------------
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");

	// (3).Page Constructor
	// -----------------------
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// (4).Page Actions/Methods
	// -----------------------
	public String getAccPageTitle() {
		String title = elementUtil.waitForTitleIsAndFetch(10, "My Account");
		System.out.println("Login Page Title is" + title);
		return title;
	}

	public String getAccPageUrl() {

		String pageURL = elementUtil.waitForURLContainsAndFetch(10, "route=account/account");
		System.out.println("Account Page Url is" + pageURL);
		return pageURL;
	}

	public List<String> accPageheader() {

		List<WebElement> headerlist = elementUtil.waitForElementsVisible(headerList,
				AppConstansts.DEFAULT_MEDIUM_TIME_OUT);
		// List<WebElement> headerlist = driver.findElements(headerList);
		List<String> accHeadList = new ArrayList<String>();
		for (WebElement e : headerlist) {
			String text = e.getText();
			accHeadList.add(text);
		}
		return accHeadList;
	}

	public List<String> accPagelinks() {

		List<WebElement> acclinklist = driver.findElements(linksList);
		List<String> linknames = new ArrayList<String>();
		for (WebElement e : acclinklist) {
			String text = e.getText();
			linknames.add(text);
		}
		return linknames;
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.waitForElementVisible(logoutlnk, AppConstansts.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();

	}

	public boolean isSearchExist() {

		return elementUtil.waitForElementVisible(search, AppConstansts.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}

	// search Operation I can Perform Only when Search Option available on Ui
	public SearchPage performSearch(String searchKey) {

		if (isSearchExist()) {
			elementUtil.doSendKeys(search, searchKey);
			elementUtil.doClick(searchIcon);
			return new SearchPage(driver);
		} else {

			System.out.println("Search field Not Avaialable On UI");
			return null;
		}

	}

	// (5).Page Navigation

}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstansts;
import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	// (1),(2),(3),(4),(5) These Combinedly we called as Templte
	// (1).Page Having it's own WebDriver & ElementUtil
	private WebDriver driver;
	private ElementUtil elementUtil;

	// (2).By Locator's
	private By searchProductResults = By.cssSelector("div#content div.product-layout");

	// (3).Constructor
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// (4).Methods
	// I have to Check while Searching How many Product Details Deisplayed?
	// No Results 1 Result / 2 Results / 3 Results / n results
	// So How can we Vaidated This?
	// testNg Assertion will validate this How many products Are there
	public int getSearchProoductCount() {
		int productCount = elementUtil.waitForElementsVisible(searchProductResults, AppConstansts.DEFAULT_LONG_TIME_OUT)
				.size();
		System.out.println("PRODUCT COUNT is ..." + " " + productCount);
		return productCount;
	}

	// (5).Navigation to another Pages
	// ############ProductInfoPage#############
	// Search Gives products Clicked On any Product will Navigated to Product Page
	public ProductInfoNewPage selectProduct(String productName) {
		// Imp point this***** We are sending 1 Dynamic Xpath*******
		By productLocator = By.linkText(productName);
		elementUtil.waitForElementVisible(productLocator, AppConstansts.DEFAULT_SHORT_TIME_OUT).click();
		return new ProductInfoNewPage(driver);
	}

}

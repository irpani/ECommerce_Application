package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstansts;
import com.qa.opencart.utils.ElementUtil;

//(1),(2),(3),(4),(5) These Combinedly we called as Templte
public class ProductInfoNewPage extends BaseTest {

	// (1).Private WebDriver
	private WebDriver driver;
	ElementUtil elementUtil;

	// (2).Constructor
	public ProductInfoNewPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	// (3).Private By Locator's
	private By productHeader = By.linkText("MacBook Pro");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li"); // **HashMap Ex
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	private Map<String, String> productInfoMap;
	// (4).Methods/Actions

	public String getproductHeaderValue() {
		String productHeaderValue = elementUtil.doElementGetText(productHeader);
		System.out.println("Product Header" + productHeaderValue);
		return productHeaderValue;
	}

	public int getProductImagesCount() {

		int imagesCount = elementUtil.waitForElementsPresence(productImages, AppConstansts.DEFAULT_MEDIUM_TIME_OUT)
				.size();
		System.out.println("ProductInfo Page Images Count is " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		// (1).Header
		productInfoMap.put("productName", getproductHeaderValue());
		// Below 2 private Methods Calling This is an Example of Encapsulation
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaList = elementUtil.getElements(productMetaData);
		for (WebElement e : metaList) {
			String meta = e.getText(); // Brand : Apple
			String[] metaInfo = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(key, value); // I prefer HashMap becasue It is visible as( Key pair)
		}
	}

	private void getProductPriceData() {

		List<WebElement> priceList = elementUtil.getElements(productPriceData);
		String price = priceList.get(0).getText(); // contains this Value ---- $2,000.00
		String exTax = priceList.get(1).getText(); // contains this Value ---- Ex Tax: $2,000.00

		String extaxVal = exTax.split(":")[1].trim(); // I don't need first value[0th Index],I need 1st Index i.e
														// $2,000.00
		productInfoMap.put("productprice", price);// here "productprice" is the Custom Key
		productInfoMap.put("exTax", extaxVal); // here "exTax" is the Custom Key
	}

	// (5).Navigation To another Page

}

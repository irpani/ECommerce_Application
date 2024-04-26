package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

//So For ALl Product Related Page Validation we can do On this 
public class ProductInfoPageTest extends BaseTest {

	// What is The Precondition Here First Do Login Right Go to Accountstest page
	// Copy paste the First 2 Lines
	// This is the Precondition For ProductInfopage
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLoginPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {

		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }, { "Samsung", "Samsung SyncMaster 941BW", 1 }, };
	}

	@Test(dataProvider = "getProductImagesTestData")
	public void ProductPageInfoTest(String searchKey, String productName, int imagesCount) {

		searchPage = accPage.performSearch(searchKey);
		productInfoNewPage = searchPage.selectProduct(productName); // Which means Select On MacBook Pro
		int actualImages = productInfoNewPage.getProductImagesCount();
		System.out.println("List oF IMAGES" + actualImages);
		Assert.assertEquals(actualImages, imagesCount);
	}

	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoNewPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoNewPage.getProductInfo();
		System.out.println(actProductInfoMap);
		// *****Validating One test case With Multile Assertions*******
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actProductInfoMap.get("productName"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");

	}

}

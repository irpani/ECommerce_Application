package com.qa.opencart.tests;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstansts;

import junit.framework.Assert;

public class AccountPageTest extends BaseTest {

	// This is the Precondition For Accounts page
	@BeforeClass
	public void accPageSetup() {

		accPage = loginPage.doLoginPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}

	@Test
	public void accPageTitleTest() {

		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstansts.ACCOUNTS_PAGE_TITLE_VALUE);

	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccPageUrl();
		Assert.assertTrue(actURL.contains(AppConstansts.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void logoutLinkTest() {

		Assert.assertTrue(accPage.isLogoutLinkExist());

	}

	@Test
	public void accPageheaderCountTest() {
		List<String> actheaders = accPage.accPageheader();
		// Assert.assertEquals(actheaders.size(), 4); So wee can chnage this hardcode
		// value also Mostly it is functionaliy level not changed
		Assert.assertEquals(actheaders.size(), AppConstansts.ACCOUNTS_PAGE_HEADERS_COUNT);
	}

	// ***Note****
	// Assert.assertEquals() will Compare any value
	// Collections,Strings,Primitives,long,Object ......
	// HERE Blow Scenario we are going to Compare two Collections
	@Test
	public void accPageheadervalueTest() {
		List<String> actheaders = accPage.accPageheader();
		Assert.assertEquals(actheaders, AppConstansts.EXPECTED_ACCOUTS_PAGE_HEADERS_LIST);
	}

	@Test
	public void accPageLinksCountTest() {
		List<String> actheaders = accPage.accPagelinks();
		for (String text : actheaders) {

			if (!text.isEmpty()) {
				System.out.println(text);
			}

		}
		// System.out.println(actheaders.);
		int no = actheaders.size();
		System.out.println(no);
		Assert.assertEquals(actheaders.size(), AppConstansts.ACCOUNTS_LINKS__COUNT);
	}

	@DataProvider
	public Object[][] getProductData() {

		return new Object[][] { // This is 2 Dimensional Array Having [4]Rows & [1] Column

				{ "MacBook" }, { "iMac" }, { "Apple" }, { "Samsung" } };

	}

	@Test(dataProvider = "getProductData") // Hwew I mapped the Test Data
	public void searchProductCountTest(String searchKey) { // Passing the Parameter
		searchPage = accPage.performSearch(searchKey); // I got the SearchPage reference
		Assert.assertTrue(searchPage.getSearchProoductCount() > 0); // Now we start validating Search Page TESTCASES
	}

	// Now we Have to Proceed further We have to Click On Specific Product as well
	// **********Started 3 level Page Chaining*****************

	@DataProvider
	public Object[][] getProductTestData() {

		return new Object[][] { // This is 2 Dimensional Array Having [2]Rows & [2] Columns
				{ "MacBook", "MacBook Pro" }, { "MacBook", "MacBook Air" }, { "iMac", "iMac" },
				{ "Apple", "Apple Cinema 30\"" }, { "Samsung", "Samsung SyncMaster 941BW" },
				{ "Samsung", "Samsung Galaxy Tab 10.1" } };

	}

	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) { // 2 Columns 1for Search another one
																			// Prductname to
																			// click
		searchPage = accPage.performSearch(searchKey);
		if (searchPage.getSearchProoductCount() > 0) {
			productInfoNewPage = searchPage.selectProduct(productName); // With this Step we are on Product Info page
			String actProductHeader = productInfoPage.getproductHeaderValue();// Now Start Validating ProductInfPage
			Assert.assertEquals(actProductHeader, productName);
		}

	}

	// Note:-
	// We have 10 Pages Is It Mandatory to create 10 Testcase Script Pages
	// A:No
	// Tomorrow there are 100 Pages Are u Cratin g 100 Testcase Classes ?A: No Not
	// required
	// Q:How u will write 10 Manaual Test Cases ?
	// (A):Manual Test Cases u can write in 1 Excel Sheet
	// We can't Create 100 Sheets Rght like
	// This Excel for => LoginPage Another Sheet for =>AccountPage

	// Now I can Perform SearchPage Test class Validatin fron Accouts Page

}
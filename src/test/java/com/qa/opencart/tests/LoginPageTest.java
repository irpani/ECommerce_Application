package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstansts;

public class LoginPageTest extends BaseTest {
	// Can I strat writing My testcases Directly from here .....with @Test

	// I don't want to create Unnecessary Objects here so I created Objcts At Base
	// Class Level Then Strat utilizing them from here

	// I have @Test 5 test cases For each @Test case I cann't Create Object, I
	// created At Once in Base Test
	// Place so that I Inherited it I can use any where in my Testcases
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		// ***One Impoprtant Concept here This method will gives us Title

		// Actual Title is this we need to validate this then Only Your test case Got
		// validated
		// I just take the hardcode value later we will change this
		Assert.assertEquals(actTitle, AppConstansts.LOGIN_PAGE_TITLE_VALUE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {

		String actURL = loginPage.getLoginPageUrl();
		Assert.assertTrue(actURL.contains(AppConstansts.LOGIN_PAGE_URL_FRACTION_VALUE));
	}

	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());

	}

	// DoLogin Method will not give any thing so How we will assert it?

	// It will not retun any thing how can we assert it ???????
	// So we can see this in Some time
	// we fix like This is a Precondition for ACCOUNT PAGE Class
	@Test(priority = 4)
	public void loginTest() {
		// Now u understand What this method returned
		// It returned the Reference Of => AccountPage accPage
		accPage = loginPage.doLoginPage(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	// Note:-
	// Assert.assertEquals() will Compare any value
	// Collections,Strings,Primitives,long ......

}

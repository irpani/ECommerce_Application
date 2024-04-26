package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstansts {
	// Naming Convention for Constants CAPITALLETTERS WITH UNDERSCORE
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 20;

	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";

	public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION_VALUE = "route=account/account";

	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final int ACCOUNTS_LINKS__COUNT = 97;
	public static final List<String> EXPECTED_ACCOUTS_PAGE_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

}

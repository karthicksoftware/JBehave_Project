package com.jbehave.pageobjects;

import org.testng.Assert;

import com.jbehave.core.TestBase;

public class HomePage extends TestBase {

	
	
	public void validatePageTitle(String actual, String expected) throws Exception {
		Assert.assertEquals(actual, expected);
	}

}

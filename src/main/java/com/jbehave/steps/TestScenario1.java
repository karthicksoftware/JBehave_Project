package com.jbehave.steps;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.jbehave.pageobjects.HomePage;

public class TestScenario1 {

	HomePage homepage = new HomePage();

	@BeforeStory
	public void startBrowerInstance() throws Exception {
		homepage.initializeBrowser("chrome", "local");
	}

	@AfterStory
	public void killBrowserInstance() throws Exception {
		homepage.tearDown();
	}

	String url = null;

	@Given(value = "a URL '$url'")
	public void setURL(String url) throws Exception {
		this.url = url;
	}

	@When(value = "user hits on a webpage")
	public void getURL() throws Exception {
		homepage.getURL(url);
	}

	@Then(value = "validate the title on the webpage as '$title'")
	public void validatePageTitle(String title) throws Exception {
		homepage.validatePageTitle(homepage.getTitle(), title);
	}
}

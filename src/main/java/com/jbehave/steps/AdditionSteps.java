package com.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.jbehave.pageobjects.Addition;

import junit.framework.Assert;

public class AdditionSteps {

	Addition obj;
	int actualValue = 0;

	@Given(value = "a scenario")
	public void createObject() throws Exception {
		obj = new Addition();
	}

	@When(value = "user enters $number1 and $number2")
	public void doAddition(int a, int b) throws Exception {
		actualValue = obj.addValues(a, b);
	}

	@Then(value = "result should be displayed as $result")
	public void validateResult(int expectedValue) throws Exception {
		Assert.assertTrue("Values are not matched",expectedValue == actualValue);
	}

}

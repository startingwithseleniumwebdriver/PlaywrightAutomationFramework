package com.automation.cucumber.web.steps;

import static org.testng.Assert.assertEquals;

import com.automation.cucumber.web.config.WebTestContext;
import com.automation.cucumber.web.context.WebScenarioContext;
import com.automation.cucumber.web.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	private final WebScenarioContext context;
	private LoginPage loginPage;

	public LoginSteps(WebTestContext testContext) {
		this.context = testContext.getScenarioContext();
	}

	@Given("I open the login page")
	public void openLoginPage() {
		loginPage = new LoginPage(context.getPage());
		loginPage.navigate();
		context.getLogger().info("Opened login page");
	}

	@When("I login with username {string} and password {string}")
	public void loginWithCredentials(String user, String pass) {
		loginPage.login(user, pass);
		context.getLogger().info("Attempted login for user: {}", user);
	}

	@Then("I should see the login status as {string}")
	public void verifyStatus(String expected) {
		String actual = loginPage.getStatus();
		assertEquals(actual.trim(), expected);
		context.getLogger().info("Login status verified: {}", actual);
	}
}

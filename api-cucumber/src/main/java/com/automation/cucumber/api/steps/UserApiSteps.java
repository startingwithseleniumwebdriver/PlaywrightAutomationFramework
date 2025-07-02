package com.automation.cucumber.api.steps;

import static org.testng.Assert.assertEquals;

import com.automation.common.framework.dto.UserResponseDTO;
import com.automation.common.framework.utils.JsonUtils;
import com.automation.cucumber.api.config.TestContext;
import com.automation.cucumber.api.context.ScenarioContext;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserApiSteps {
	private final ScenarioContext context;
	private String response;

	public UserApiSteps(TestContext testContext) {
		this.context = testContext.getScenarioContext();
	}

	@When("I request user data for user id {string}")
	public void requestUserDataForUserId(String id) {
		context.getLogger().info("Requesting user data for ID: {}", id);
		String url = "https://reqres.in/api/users/" + id;
		response = context.getRequestContext().get(url).text();
		context.getLogger().info("Received response: {}", response);
	}

	@Then("I should receive user details with id {string}")
	public void verifyUserDetailsWithId(String expectedId) {
		UserResponseDTO userResponse = JsonUtils.fromJson(response, UserResponseDTO.class);
		assertEquals(userResponse.getData().getUserId(), expectedId);
	}
}

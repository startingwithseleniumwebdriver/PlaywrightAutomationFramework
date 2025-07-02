package com.automation.testng.api.validation;

import org.testng.Assert;

import com.automation.common.framework.dto.UserDTO;

public class UserValidator {

	private UserValidator() {
	}

	public static void validateBasicUserFields(UserDTO user) {
		Assert.assertNotNull(user.getUserId(), "User ID is null");
		Assert.assertTrue(user.getEmail().contains("@"), "Email format invalid");
	}
}

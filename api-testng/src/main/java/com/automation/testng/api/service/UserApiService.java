package com.automation.testng.api.service;

import java.util.Collections;

import com.automation.common.framework.dto.UserDTO;
import com.automation.common.framework.dto.UserResponseDTO;
import com.automation.common.framework.utils.JsonUtils;
import com.automation.core.framework.api.client.ApiClient;

public class UserApiService {

	private static final String BASE_URL = "https://reqres.in/api/users";

	public UserDTO getUser(String id) {
		String response = ApiClient.sendRequest("GET", BASE_URL + "/" + id, null,
				Collections.singletonMap("Content-Type", "application/json"));
		return JsonUtils.fromJson(response, UserResponseDTO.class).getData();
	}
}

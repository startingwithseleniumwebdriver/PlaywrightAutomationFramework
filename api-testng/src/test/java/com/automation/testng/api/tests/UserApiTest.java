package com.automation.testng.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.common.framework.dto.UserDTO;
import com.automation.core.framework.base.BaseApiTest;
import com.automation.testng.api.service.UserApiService;

public class UserApiTest  extends BaseApiTest {

    private final UserApiService api = new UserApiService();

    @Test(description = "Verify basic GET API returns Email & Id Properly")
    public void testGetUser() {
        UserDTO user = api.getUser("2");

        Assert.assertEquals(user.getUserId(), "2");
        Assert.assertEquals(user.getEmail(), "janet.weaver@reqres.in");
    }
}

package com.automation.testng.web.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.core.framework.base.BaseWebTest;
import com.automation.webtestng.pages.LoginPage;
import com.microsoft.playwright.Page;

public class LoginTest extends BaseWebTest {

    @Test
    public void testValidLogin() {
        Page page = super.page;

        LoginPage login = new LoginPage(page);
        login.login("testuser", "testpass");

        Assert.assertTrue(login.getLoginStatus().contains("Welcome"));
    }
}

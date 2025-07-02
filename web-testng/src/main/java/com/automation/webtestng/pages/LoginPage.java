package com.automation.webtestng.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
    }

    public void login(String username, String password) {
        page.fill("#username", username);
        page.fill("#password", password);
        page.click("#login");
    }

    public String getLoginStatus() {
        return page.textContent("#status");
    }
}


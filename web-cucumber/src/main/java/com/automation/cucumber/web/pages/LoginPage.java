package com.automation.cucumber.web.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	private final Page page;

	public LoginPage(Page page) {
		this.page = page;
	}

	public void navigate() {
		page.navigate("https://example.com/login");
	}

	public void login(String username, String password) {
		page.fill("#username", username);
		page.fill("#password", password);
		page.click("#login");
	}

	public String getStatus() {
		return page.textContent("#status");
	}
}

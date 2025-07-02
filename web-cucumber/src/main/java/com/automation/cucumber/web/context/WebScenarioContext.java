package com.automation.cucumber.web.context;

import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;

public class WebScenarioContext {
	private final Page page;
	private final Logger logger;

	public WebScenarioContext(Page page, Logger logger) {
		this.page = page;
		this.logger = logger;
	}

	public Page getPage() {
		return page;
	}

	public Logger getLogger() {
		return logger;
	}
}

package com.automation.cucumber.api.hooks;

import org.apache.logging.log4j.Logger;

import com.automation.core.framework.logging.LoggerManager;
import com.automation.core.framework.utils.AllureAttachmentUtil;
import com.automation.core.framework.utils.ThreadSafeContext;
import com.automation.cucumber.api.config.TestContext;
import com.automation.cucumber.api.context.ScenarioContext;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApiCucumberHooks {

	private final TestContext testContext;
	private Playwright playwright;
	private Logger logger;

	public ApiCucumberHooks(TestContext testContext) {
		this.testContext = testContext;
	}

	@Before
	public void beforeScenario(Scenario scenario) {
		String testName = scenario.getName().replaceAll("\\s+", "_");
		ThreadSafeContext.setTestId(testName);
		logger = LoggerManager.getLogger();
		logger.info("Starting API scenario: {}", testName);

		// Setup Playwright API context
		playwright = Playwright.create();
		APIRequestContext context = playwright.request().newContext();

		ScenarioContext scenarioContext = new ScenarioContext(context, logger);
		testContext.setScenarioContext(scenarioContext);
	}

	@After
	public void afterScenario(Scenario scenario) {
		String testName = ThreadSafeContext.getTestId();
		logger.info("Ending API scenario: {}", testName);

		try {
			if (testContext.getScenarioContext() != null) {
				testContext.getScenarioContext().getRequestContext().dispose();
			}
		} catch (Exception e) {
			logger.warn("Failed to dispose request context: {}", e.getMessage());
		}

		if (playwright != null) {
			playwright.close();
		}

	    AllureAttachmentUtil.attachLogToAllure("Web Log - " + scenario.getName(), LoggerManager.getLogFilePath());
		LoggerManager.removeLogger();
		ThreadSafeContext.clear();
	}
}

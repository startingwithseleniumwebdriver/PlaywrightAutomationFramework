package com.automation.core.framework.listners;

import java.util.List;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automation.core.framework.config.ConfigFactorySingleton;

import io.cucumber.testng.PickleWrapper;

public class TaggedRetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	private final int maxRetryCount;

	public TaggedRetryAnalyzer() {
		// Retry count can be fetched from config or set statically
		int retry = ConfigFactorySingleton.getConfig().retryCount();
		// Enforce minimum retry of 1 if misconfigured as 0
		this.maxRetryCount = retry > 0 ? retry : 1;
	}

	@Override
	public boolean retry(ITestResult result) {
		Object[] parameters = result.getParameters();

		if (parameters.length == 0 || !(parameters[0] instanceof PickleWrapper)) {
			return false; // Not a Cucumber scenario
		}

		PickleWrapper pickleWrapper = (PickleWrapper) parameters[0];

		List<String> tags = pickleWrapper.getPickle().getTags();
		boolean hasRetryTag = tags.stream().anyMatch(tag -> tag.equalsIgnoreCase("@Retry"));

		if (!hasRetryTag) {
			return false; // No @Retry tag
		}

		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}

		return false;
	}
}

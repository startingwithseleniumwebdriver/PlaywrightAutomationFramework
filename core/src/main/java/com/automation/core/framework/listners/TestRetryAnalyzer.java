package com.automation.core.framework.listners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automation.core.framework.config.ConfigFactorySingleton;

public class TestRetryAnalyzer implements IRetryAnalyzer {
	private int retryCount = 0;
	private final int maxRetryCount;

	public TestRetryAnalyzer() {
		// Retry count can be fetched from config or set statically
		int retry = ConfigFactorySingleton.getConfig().retryCount();
		this.maxRetryCount = retry != 0 ? retry : 1;
	}

	@Override
	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}

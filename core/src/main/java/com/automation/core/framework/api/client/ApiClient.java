package com.automation.core.framework.api.client;

import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.automation.common.framework.exceptions.FrameworkException;
import com.automation.core.framework.logging.LoggerManager;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

public class ApiClient {
	private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
	private static final ThreadLocal<APIRequestContext> contextThread = new ThreadLocal<>();

	private ApiClient() {
	}

	public static void init() {
		try {
			Playwright playwright = Playwright.create();
			playwrightThread.set(playwright);
			contextThread.set(playwright.request().newContext());
		} catch (Exception e) {
			throw new FrameworkException("Failed to initialize Playwright API context", e);
		}
	}

	public static String sendRequest(String method, String url, String body, Map<String, String> headers) {
		APIRequestContext context = contextThread.get();
		
		if (context == null) {
			throw new FrameworkException("API context is not initialized for this thread.");
		}

		RequestOptions options = RequestOptions.create();
		
	    Logger log = LoggerManager.getLogger();
	    log.info("Sending {} request to: {}", method, url);
	    
		if (headers != null) {
			log.debug("Headers: {}", headers);
			headers.forEach(options::setHeader);
		}
		if (body != null)
		{
			log.debug("Request Body: {}", body);
			options.setData(body);
		}
		try {
			APIResponse response = null;
			switch (method.toUpperCase()) {
			case "GET":
				response = context.get(url, options);
				break;
			case "POST":
				response = context.post(url, options);
				break;
			case "PUT":
				response = context.put(url, options);
				break;
			case "DELETE":
				response = context.delete(url, options);
				break;
			default:
				throw new FrameworkException("Unsupported method: " + method);
			}
			
		    if (response.status() < 200 || response.status() >= 300) {
		        String errorBody = response.text();
		        log.error("API Error {}: {}", response.status(), errorBody);
		        throw new FrameworkException("API request failed. Status: " + response.status());
		    }
		    
	        log.debug("Response Status: {}", response.status());
	        log.debug("Response Body: {}", response.text());
	        return response.text();
		} catch (Exception e) {
			log.error("API request failed: {}", e.getMessage(), e);
			throw new FrameworkException("API request failed for URL: " + url, e);
		}
	}

	public static void close() {
		try {
			if (contextThread.get() != null) {
				contextThread.get().dispose();
			}
			if (playwrightThread.get() != null) {
				playwrightThread.get().close();
			}
		} finally {
			contextThread.remove();
			playwrightThread.remove();
		}
	}
}

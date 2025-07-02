package com.automation.core.framework.factory;

import com.automation.common.framework.enums.BrowserTypeEnum;
import com.automation.common.framework.exceptions.FrameworkException;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {

    private BrowserFactory() {}

    public static BrowserType getBrowserType(Playwright playwright, BrowserTypeEnum browserName) {

        switch (browserName) {
            case CHROME:
            case EDGE: // EDGE is Chromium-based
                return playwright.chromium();
            case FIREFOX:
                return playwright.firefox();
            case WEBKIT:
                return playwright.webkit();
            default:
                throw new FrameworkException("Unsupported browser type: " + browserName);
        }
    }
}




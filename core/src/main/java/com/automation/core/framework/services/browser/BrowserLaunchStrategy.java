package com.automation.core.framework.services.browser;

import com.automation.core.framework.config.FrameworkConfig;
import com.microsoft.playwright.Playwright;

public interface BrowserLaunchStrategy {
    void launch(Playwright playwright, FrameworkConfig config);
}


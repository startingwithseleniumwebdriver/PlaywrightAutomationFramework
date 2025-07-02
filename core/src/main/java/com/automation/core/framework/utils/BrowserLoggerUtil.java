package com.automation.core.framework.utils;

import org.apache.logging.log4j.Logger;

import com.automation.common.framework.enums.DeviceProfileEnum;
import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.config.FrameworkConfig;
import com.automation.core.framework.logging.LoggerManager;

public class BrowserLoggerUtil {

    private BrowserLoggerUtil() {}

    public static void logBrowserSummary() {
    	FrameworkConfig config = ConfigFactorySingleton.getConfig();
        Logger log = LoggerManager.getLogger();

        log.info("Browser Config Summary: \n"
        		+ "  → Thread- {}\n"
        		+ "  → Device: {}\n"
                + "  → Browser: {}\n"
                + "  → Headless: {}\n"
                + "  → Args: {}\n"
                + "  → Downloads: {}\n"
                + "  → Tracing: {}\n"
                + "  → Video: {}\n"
                + "  → Emulation: {}\n"
                + "  → User Agent: {}\n"
                + "  → Viewport: {}x{}\n"
                + "  → DPR: {}\n"
                + "  → Landscape: {}",
            Thread.currentThread().getId(),
            config.deviceProfile(),
            config.browser(),
            config.headless(),
            BrowserConfigResolver.getBrowserArgs(),
            BrowserConfigResolver.isDownloadEnabled(),
            BrowserConfigResolver.isTracingEnabled(),
            BrowserConfigResolver.isVideoEnabled(),
            DeviceProfileEnum.fromString(config.deviceProfile()).name(),
            config.mobileUserAgent(),
            config.mobileViewportWidth(),
            config.mobileViewportHeight(),
            config.mobileDevicePixelRatio(),
            config.mobileLandscape());
    }
}


package com.automation.core.framework.services.browser;

import com.automation.common.framework.enums.BrowserTypeEnum;
import com.automation.common.framework.enums.DeviceProfileEnum;
import com.automation.common.framework.exceptions.FrameworkException;

public class BrowserLauncherFactory {

    public static BrowserLaunchStrategy getStrategy(BrowserTypeEnum browser, DeviceProfileEnum device) {
        switch (browser) {
            case CHROME:
            case EDGE: // both use chromium internally
                return device == DeviceProfileEnum.MOBILE
                        ? new ChromiumMobileStrategy()
                        : new ChromiumDesktopStrategy();

            case FIREFOX:
                return device == DeviceProfileEnum.MOBILE
                        ? new FirefoxMobileStrategy()
                        : new FirefoxDesktopStrategy();

            case WEBKIT:
                return device == DeviceProfileEnum.MOBILE
                        ? new WebkitMobileStrategy()
                        : new WebkitDesktopStrategy();

            default:
                throw new FrameworkException("Unsupported browser/device combo: " + browser + "/" + device);
        }
    }
}




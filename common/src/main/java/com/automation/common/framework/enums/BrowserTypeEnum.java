package com.automation.common.framework.enums;

import com.automation.common.framework.exceptions.FrameworkException;

public enum BrowserTypeEnum {
    CHROME,
    FIREFOX,
    WEBKIT,
    EDGE;

    public static BrowserTypeEnum fromString(String browserName) {
        try {
            return BrowserTypeEnum.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new FrameworkException("Invalid browser type: " + browserName);
        }
    }
}

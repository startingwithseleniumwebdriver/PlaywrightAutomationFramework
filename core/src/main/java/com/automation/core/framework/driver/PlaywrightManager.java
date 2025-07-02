package com.automation.core.framework.driver;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightManager {

    private static final ThreadLocal<Playwright> playwrightThread = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThread = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThread = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThread = new ThreadLocal<>();

    private PlaywrightManager() {}

    public static void setPlaywright(Playwright p) {
        playwrightThread.set(p);
    }

    public static void setBrowser(Browser b) {
        browserThread.set(b);
    }

    public static void setContext(BrowserContext c) {
        contextThread.set(c);
    }

    public static void setPage(Page p) {
        pageThread.set(p);
    }

    public static Playwright getPlaywright() {
        return playwrightThread.get();
    }

    public static Browser getBrowser() {
        return browserThread.get();
    }

    public static BrowserContext getContext() {
        return contextThread.get();
    }

    public static Page getPage() {
        return pageThread.get();
    }

    public static void close() {
    	{
            Page page = pageThread.get();
            if (page != null) {
                page.close();
                pageThread.remove();
            }
            
            BrowserContext context = contextThread.get();
            if (context != null) {
                context.close();
                contextThread.remove();
            }
            
            Browser browser = browserThread.get();
            if (browser != null) {
                browser.close();
                browserThread.remove();
            }
            
            Playwright playwright = playwrightThread.get();
            if (playwright != null) {
                playwright.close();
                playwrightThread.remove();
            }
        }
    }
}

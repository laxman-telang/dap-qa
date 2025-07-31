package utils;

import com.microsoft.playwright.*;
import java.util.Objects;

public class PlaywrightDriver {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page getPage() {
        if (Objects.isNull(page)) {
            playwright = Playwright.create();
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            context = browser.newContext();
            page = context.newPage();
        }
        return page;
    }

    public static void closeBrowser() {
        if (Objects.nonNull(page)) {
            page.close();
            context.close();
            browser.close();
            playwright.close();
        }
    }
}

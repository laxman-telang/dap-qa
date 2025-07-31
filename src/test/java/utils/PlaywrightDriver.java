package utils;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
public class PlaywrightDriver {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    public PlaywrightDriver() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    public Page getPage() {
        return page;
    }

    public void close() {
        browser.close();
        playwright.close();
    }
}

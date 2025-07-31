package stepdefinitions;

import com.microsoft.playwright.*;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class LoginSteps {
    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @Given("I open the SauceDemo login page")
    public void openLoginPage() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
        page.navigate("https://www.saucedemo.com/");
    }

    @When("I enter valid username and password")
    public void enterCredentials() {
        page.fill("#user-name", "standard_user");
        page.fill("#password", "secret_sauce");
    }

    @When("I click the login button")
    public void clickLogin() {
        page.click("#login-button");
    }

    @Then("I should be redirected to the inventory page")
    public void verifyInventoryPage() {
        assertTrue(page.url().contains("inventory"));
        browser.close();
        playwright.close();
    }
}

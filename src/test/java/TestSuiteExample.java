import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestSuiteExample {

    private static Playwright pw;
    private static Browser browser;
    private static Page page;

    @BeforeAll
    public static void createBrowser() {

        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterAll
    public static void closeBrowser() {
        browser.close();
        pw.close();
    }

    @Test
    public void Test1() {

        page.navigate("https://www.google.com");
        page.fill("input[aria-label='Search']", "test");
        page.keyboard().press("Enter");

        Assertions.assertTrue(page.waitForSelector("button[aria-label='Google Search']").isVisible());
    }

    @Test
    public void Test2() {
        page.navigate("http://playwright.dev/java");

        Assertions.assertTrue(page.waitForSelector("h1").textContent().contains("Playwright"));

    }
}

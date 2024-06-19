import lv.acodemy.page_object.CartPage;
import lv.acodemy.page_object.HeaderPage;
import lv.acodemy.page_object.LoginPage;
import lv.acodemy.page_object.ProductsPage;
import lv.acodemy.utils.Config;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest {

    private static final Logger log = LoggerFactory.getLogger(SauceDemoTest.class);
    Config config = Config.readConfig();
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    HeaderPage headerPage;
    CartPage cartPage;

    @BeforeMethod
    public void beforeTest() {
        // Initialize driver
        driver = new ChromeDriver();

        // Initialize pages
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);

        driver.get(config.getWebAppConfig().getUrl());
    }

    @Test
    public void testSauceDemoSuccessLogin() {
        log.info("Authorize using credentials");
        loginPage.authorize(config.getCredentials().getLogin(), config.getCredentials().getPassword());

        log.info("Asserting Products page title");
        Assert.assertEquals(productsPage.getProductPageTitle().getText(), "Products");
    }

    @Test
    public void testAddItemToCart() {
        log.info("Authorize using credentials");
        loginPage.authorize(config.getCredentials().getLogin(), config.getCredentials().getPassword());

        log.info("Asserting Products page title");
        Assert.assertEquals(productsPage.getProductPageTitle().getText(), "Products");

        log.info("Add product to Cart");
        productsPage.addItemToCartByNameContains("Onesie");

        log.info("Open the cart");
        headerPage.getOpenCartButton().click();

        log.info("Assert that cart contains one product");
        Assertions.assertThat(cartPage.getCartItems()).hasSize(1);
    }

    @AfterMethod
    public void afterTest() {
        // Close driver
        driver.close();
        driver.quit();
    }
}

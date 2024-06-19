import lv.acodemy.page_object.*;
import lv.acodemy.utils.Config;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void beforeTest() {
        // Initialize driver
        driver = new ChromeDriver();

        // Initialize pages
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

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

    @Test
    public void testCheckout() {
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

        log.info("Click on checkout button");
        cartPage.getCheckoutButton().click();

        checkoutPage.enterCheckoutData("John", "Doe", "1111");
        checkoutPage.getContinueButton().click();
    }

    @AfterMethod
    public void afterTest() {
        // Close driver
        driver.close();
        driver.quit();
    }
}

package lv.acodemy.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public WebElement getCheckoutButton() {
        return checkoutButton;
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

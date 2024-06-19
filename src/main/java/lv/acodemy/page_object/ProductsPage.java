package lv.acodemy.page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    WebDriver driver;

    @FindBy(xpath = "//span[@data-test='title']")
    private WebElement productPageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> productList;

    private final By addToCartButton = By.xpath(".//div[@class='pricebar']//button");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductPageTitle() {
        return productPageTitle;
    }

    public List<WebElement> getProductList() {
        return productList;
    }

    public By getAddToCartButton() {
        return addToCartButton;
    }

    /** Add item to cart by name contains
     * @param productName desired product name to be added in the cart
     */
    public void addItemToCartByNameContains(String productName) {
        for (WebElement element : getProductList()) {
            if(element.getText().contains(productName)) {
                element.findElement(getAddToCartButton()).click();
                break;
            }
        }
    }
}

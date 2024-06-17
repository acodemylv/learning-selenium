package lv.acodemy.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The {@code LoginPage} class provides all Login page elements
 * and available methods
 *
 * @author nikitam
 * @version 1.0
 */
public class LoginPage {

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    /**
     * Authorize user by name and password
     * @param username valid or invalid username for WEB page
     * @param password valid or invalid password for WEB page
     */
    public void authorize(String username, String password) {
        getUsernameField().sendKeys(username);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
    }
}

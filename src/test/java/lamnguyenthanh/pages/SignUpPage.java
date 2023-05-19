package lamnguyenthanh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class SignUpPage {
    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Create an Account")
    private WebElement createAccountLink;

    @FindBy(id = "firstname")
    private WebElement firstNameField;

    @FindBy(id = "lastname")
    private WebElement lastNameField;

    @FindBy(id = "email_address")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//div[@class='actions-toolbar']/div/button/span[contains(text(),'Create an Account')]")
    private WebElement createButton;

    @FindBy(xpath = "//div[contains(text(),'Thank you for registering with Main Website Store.')]")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class= 'panel header']//button[@type='button' and @class='action switch']")
    private WebElement accountDropdown;

    @FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[2]/div[1]/ul[1]/li[3]/a[1]")
    private WebElement signOutLink;

    public void clickCreateAccountLink() {
        createAccountLink.click();
    }

    public void setFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String confirmPassword) {
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void clickCreateButton() {
        createButton.click();
    }

    public void logOut() {
        accountDropdown.click();
        signOutLink.click();
    }

    public void assertRegistrationSuccess() {
        Assert.assertTrue(successMessage.isDisplayed(), "Registration success message is not displayed.");
    }
}


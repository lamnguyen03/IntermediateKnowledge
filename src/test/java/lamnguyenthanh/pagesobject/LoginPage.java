package lamnguyenthanh.pagesobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Sign In")
    private WebElement logInLink;

    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "pass")
    private WebElement passWord;
    @FindBy(id = "send2")
    private WebElement logInBtn;
    @FindBy(className = "mage-error")
    private WebElement failMessage;
    @FindBy(className = "logged-in")
    private WebElement successMessage;
    public WebElement getsuccessLocator() {
        return successMessage;
    }
    public void LogIn(String email, String password) {
        logInLink.click();
        this.email.sendKeys(email);
        passWord.sendKeys(password);
        waitUtillLogInBtnClicked(logInBtn);
        logInBtn.click();
    }

    public boolean isLogInSuccess() {
        return successMessage.isDisplayed();
    }

    public boolean isLogInFail() {
        return failMessage.isDisplayed();
    }

    public void waitUtillLogInBtnClicked(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

//    public void waitForElementVisible(WebElement element, Duration timeout) {
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.visibilityOf(element));
//    }

}

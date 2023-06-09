package lamnguyenthanh.pagesobject;

import lamnguyenthanh.commons.BaseSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MiniCartPage extends BaseSetUp {
    private WebDriver driver;

    public MiniCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='minicart-wrapper']//a[@class='action showcart']")
    private WebElement shoppingCartIcon;

    @FindBy(linkText = "View and Edit Cart")
    private WebElement viewCartLink;
    @FindBy(xpath = "//div[contains(text(),'You added Argus All-Weather Tank to your')]")
    private WebElement addToCartSuccessMessage;

    public void navigateToCheckOutCart() {
        waitToBeAvailable(addToCartSuccessMessage);
        shoppingCartIcon.click();
        waitToBeAvailable(viewCartLink);
        waitToBeClickAble(viewCartLink);
        viewCartLink.click();
    }

    public void waitToBeAvailable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeClickAble(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

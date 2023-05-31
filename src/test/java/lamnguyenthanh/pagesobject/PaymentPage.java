package lamnguyenthanh.pagesobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    private WebDriver driver;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='opc-wrapper']//button[@class='action primary checkout']")
    private WebElement placeOrderBtn;
    @FindBy(xpath = "//div[@class='payment-group']/div[@class='step-title']")
    private WebElement titleOfPage;

    public void oderProcess() {
        Actions actions = new Actions(driver);
        actions.moveToElement(placeOrderBtn).click().perform();

    }

    public boolean isAtPaymentPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleOfPage));
        return titleOfPage.isDisplayed();
    }

    public void waitUntilPlaceOrderClickable() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='checkout-billing-address']")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='checkout-billing-address']")));

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
    }

    public void waitToBeClickAble(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}

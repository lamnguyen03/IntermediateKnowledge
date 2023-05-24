package lamnguyenthanh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingPage {
    private WebDriver driver;

    public ShippingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@class='checkout-shipping-method']//input[@type='radio'and @name='ko_unique_1']")
    private WebElement shippingMethofRadio;
    @FindBy(xpath = "//div[@class='primary']/button[@class='button action continue primary']")
    private WebElement nextToPayBtn;
    @FindBy(xpath = "//li[@class='checkout-shipping-address']/div[@Class='step-title']")
    private WebElement titleOfPage;
    public void shipping(){
        shippingMethofRadio.click();
        nextToPayBtn.click();

    }
    public boolean isAtShippingPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleOfPage));
        return titleOfPage.isDisplayed();
    }
}

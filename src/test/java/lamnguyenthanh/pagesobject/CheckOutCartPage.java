package lamnguyenthanh.pagesobject;

import lamnguyenthanh.commons.BaseSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutCartPage extends BaseSetUp {
    private WebDriver driver;

    public CheckOutCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//main[@id='maincontent']//strong[@class='product-item-name']/a")
    private WebElement productName;

    @FindBy(xpath = "//td[@class='col price']")
    private WebElement productPrice;

    @FindBy(xpath = "//span[@class='base']")
    private WebElement titleOfCheckOutPage;

    @FindBy(xpath = "//li[@class='item']/button")
    private WebElement processToCheckoutBtn;
    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public boolean isAtCheckOutCartPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleOfCheckOutPage));
        return titleOfCheckOutPage.isDisplayed();
    }
    public void checkOutAfterVerify(){
        processToCheckoutBtn.click();
    }

}

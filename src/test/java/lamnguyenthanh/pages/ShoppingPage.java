package lamnguyenthanh.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ShoppingPage {
    private WebDriver driver;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='product-item'][3]/div/a/span")
    private WebElement item;
    @FindBy(xpath = "//div[@class='minicart-items-wrapper']//li[@class='item product product-item odd last']")
    private WebElement itemSelected;
    @FindBy(xpath = "//div[@class='swatch-option text' and @id='option-label-size-143-item-169']")
    private WebElement itemSize;
    @FindBy(xpath = "//div[@class='swatch-option color' and @id='option-label-color-93-item-52']")
    private WebElement itemColor;
    @FindBy(id = "product-addtocart-button")
    private WebElement addToCartBtn;
    @FindBy(xpath = "//div[@class='minicart-wrapper']//a[@class='action showcart']")
    private WebElement shoppingCartIcon;
    @FindBy(id = "top-cart-btn-checkout")
    private WebElement checkOutBtn;
    @FindBy(xpath = "//div[@class='checkout-shipping-method']//input[@type='radio'and @name='ko_unique_1']")
    private WebElement radioBtn;
    @FindBy(xpath = "//div[@class='primary']/button[@class='button action continue primary']")
    private WebElement NextToPayBtn;
    @FindBy(xpath = "//div[@class='opc-wrapper']//button[@class='action primary checkout']")
    private WebElement placeOrderBtn;
    @FindBy(xpath = "//div[@class='checkout-success']//a[@class='action primary continue']/span")
    private WebElement continueShoppingBtn;
    @FindBy(css = "#minicart-content-wrapper > div.block-content > div.subtotal > span > span")
    private WebElement cartTitle;
    @FindBy(xpath = "//div[@class= 'loading-mask']")
    private WebElement loadingImg;
    String statusOfClass = "block";

    public void selectItem() {
        scrollToSelectItem();
        selectspecifications();
        paymentProcess();
    }

    public void scrollToSelectItem() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", item);
        item.click();
    }

    public void selectspecifications() {
        itemSize.click();
        itemColor.click();
        addToCartBtn.click();
    }

    public void paymentProcess() {
        shoppingCartIcon.click();
        waitToBeClickAble(addToCartBtn);
        checkItemInCart();
        checkOutBtn.click();
        radioBtn.click();
        NextToPayBtn.click();
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
//        waitUntilClassChangesStatus(loadingImg, statusOfClass);
//        waitToBeClickAble(placeOrderBtn);
        waitUntilPlaceOrderClickable();
        placeOrderBtn.click();
        continueShoppingBtn.click();
    }

    public void checkItemInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(itemSelected));
    }

    public void waitToBeClickAble(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilClassChangesStatus(WebElement loadingImg, String statusOfClass) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(loadingImg, "style", statusOfClass)));
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


}

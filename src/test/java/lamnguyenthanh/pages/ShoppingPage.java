package lamnguyenthanh.pages;

import lamnguyenthanh.commons.BaseSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class ShoppingPage extends BaseSetUp {
    private WebDriver driver;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='product-item'][3]/div/a/span")
    private WebElement item;
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
    private static WebElement cartTitle;

    public void selectItem() throws InterruptedException {
        scrollToSelectItem();
        selectspecifications();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

    public void paymentProcess() throws InterruptedException {
        shoppingCartIcon.click();
        checkItemInCart();
        checkOutBtn.click();
        radioBtn.click();
        NextToPayBtn.click();
        Thread.sleep(5000);
        placeOrderBtn.click();
        continueShoppingBtn.click();
    }

    public void checkItemInCart(){
        Assert.assertTrue(cartTitle.isDisplayed(),"Cart is empty");
    }


}

package lamnguyenthanh.pagesobject;

import lamnguyenthanh.commons.BaseSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailProductPage extends BaseSetUp {
    private WebDriver driver;

    public DetailProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='base']")
    private WebElement titleOfPage;
    @FindBy(xpath = "//div[@class='columns']//h1[@class='page-title']")
    private WebElement productName;
    @FindBy(xpath = "//div[@class='product-info-main']//span[@class='price']")
    private WebElement priceProduct;
    @FindBy(xpath = "//div[@class='swatch-option text'][position()=4]")
    private WebElement sizeProduct;
    @FindBy(xpath = "//div[@class='swatch-option color' and @id='option-label-color-93-item-52']")
    private WebElement colorProduct;
    @FindBy(xpath = "//div[@class='actions']/button[@class='action primary tocart']")
    private WebElement addToCartBtn;


    public String getProductName() {
        return productName.getText();
    }

    public String getPriceProduct() {
        return priceProduct.getText();
    }
    public String getSizeProduct() {
        return sizeProduct.getText();
    }
    public WebElement getColorProduct() {
        return colorProduct;
    }
    public boolean isAtDetailproductPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleOfPage));
        return titleOfPage.isDisplayed();
    }
    public void selectProductInfo(){
        sizeProduct.click();
        colorProduct.click();
        addToCartBtn.click();
    }
}

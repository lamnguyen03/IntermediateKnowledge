package lamnguyenthanh.pagesobject;

import lamnguyenthanh.commons.BaseSetUp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BaseSetUp {
    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//li[@class='product-item'][3]/div/a/span")
    private WebElement item;
    @FindBy(xpath = "//div[@class='columns']//h2[@class='title']")
    private WebElement titleHomePage;//a[@href='https://magento.softwaretestingboard.com/' and @title='Go to Home Page']


    public boolean isAtHomePage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(titleHomePage));
        return titleHomePage.isDisplayed();
    }
    public void scrollToSelectItem() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", item);
        item.click();
    }

}

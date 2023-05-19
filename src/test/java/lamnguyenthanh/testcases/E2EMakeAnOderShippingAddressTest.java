package lamnguyenthanh.testcases;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pages.LoginPage;
import lamnguyenthanh.pages.ShoppingPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class E2EMakeAnOderShippingAddressTest extends BaseSetUp {

    private WebDriver driver;
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
    }

    @Test
    public void shopping() throws InterruptedException {
        loginPage.LogIn("lamlam112.nguyenthanh@gmail.com", "Thanhlam26@");
        // Verify successful login
        Assert.assertTrue(loginPage.isLogInSuccess());

        shoppingPage.selectItem();


    }

}

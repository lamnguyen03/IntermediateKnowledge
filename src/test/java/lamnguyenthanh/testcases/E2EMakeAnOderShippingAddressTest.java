package lamnguyenthanh.testcases;

import io.cucumber.java.hu.De;
import io.cucumber.java.ro.Si;
import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class E2EMakeAnOderShippingAddressTest extends BaseSetUp {

    private WebDriver driver;
    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private HomePage homePage;
    private DetailProductPage detailProductPage;
    private CheckOutCartPage checkOutCartPage;
    private MiniCartPage miniCartPage;
    private ShippingPage shippingPage;
    private PaymentPage paymentPage;

    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        shoppingPage = new ShoppingPage(driver);
        homePage = new HomePage(driver);
        detailProductPage = new DetailProductPage(driver);
        checkOutCartPage = new CheckOutCartPage(driver);
        miniCartPage = new MiniCartPage(driver);
        shippingPage = new ShippingPage(driver);
        paymentPage = new PaymentPage(driver);


    }

    @Test
    public void shopping() {
        loginPage.LogIn("lamlam112.nguyenthanh@gmail.com", "Thanhlam26@");
        Assert.assertTrue(loginPage.isLogInSuccess());

        Assert.assertTrue(homePage.isAtHomePage());
        homePage.scrollToSelectItem();
        detailProductPage.selectProductInfo();
        miniCartPage.navigateToCheckOutCart();
        Assert.assertTrue(checkOutCartPage.isAtCheckOutCartPage());
        Assert.assertEquals(checkOutCartPage.getProductName(),"Argus All-Weather Tank");
        Assert.assertEquals(checkOutCartPage.getProductPrice(),"$22.00");
        checkOutCartPage.checkOutAfterVerify();
        Assert.assertTrue(shippingPage.isAtShippingPage());
        shippingPage.shipping();
        Assert.assertTrue(paymentPage.isAtPaymentPage());
        paymentPage.oderProcess();




    }

}

package lamnguyenthanh.scenario;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pagesobject.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class E2EMakeAnOderShippingAddressTest extends BaseSetUp {

    private WebDriver driver;
    private LoginPage loginPage;
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
        homePage = new HomePage(driver);
        detailProductPage = new DetailProductPage(driver);
        checkOutCartPage = new CheckOutCartPage(driver);
        miniCartPage = new MiniCartPage(driver);
        shippingPage = new ShippingPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @DataProvider(name = "orderData")
    public Object[][] getOrderData() {
        return new Object[][]{
                {"lamlam112.nguyenthanh@gmail.com", "Thanhlam26@", "Argus All-Weather Tank", "$22.00"},

        };
    }
    @Test(dataProvider = "orderData")
    public void shopping(String email, String password, String productName, String productPrice) {
        loginPage.LogIn(email, password);
        Assert.assertTrue(loginPage.isLogInSuccess());

        Assert.assertTrue(homePage.isAtHomePage());
        homePage.scrollToSelectItem();
        detailProductPage.selectProductInfo();
        miniCartPage.navigateToCheckOutCart();
        Assert.assertTrue(checkOutCartPage.isAtCheckOutCartPage());
        Assert.assertEquals(checkOutCartPage.getProductName(), productName);
        Assert.assertEquals(checkOutCartPage.getProductPrice(), productPrice);
        checkOutCartPage.checkOutAfterVerify();
        Assert.assertTrue(shippingPage.isAtShippingPage());
        shippingPage.shipping();
        Assert.assertTrue(paymentPage.isAtPaymentPage());
        paymentPage.oderProcess();




    }

}

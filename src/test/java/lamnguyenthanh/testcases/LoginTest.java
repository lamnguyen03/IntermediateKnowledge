package lamnguyenthanh.testcases;

import io.cucumber.java.hu.De;
import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pages.DetailProductPage;
import lamnguyenthanh.pages.LoginPage;
import lamnguyenthanh.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertTrue;

public class LoginTest extends BaseSetUp {
    private WebDriver driver;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private DetailProductPage detailProductPage;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        detailProductPage = new DetailProductPage(driver);
    }

    @Test
    public void testValidLogIn() {
        loginPage.LogIn("lamlam112.nguyenthanh@gmail.com","Thanhlam26@");
        //Verify success registration
        assertTrue(loginPage.isLogInSuccess());
//        signUpPage.logOut();
    }

    @Test
    public void testInValidLogIn() {
        loginPage.LogIn("lam123.nguyenthanhgmail.com","Thanhlam26@");
        //Verify success registration
        assertTrue(loginPage.isLogInFail());
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}

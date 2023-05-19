package lamnguyenthanh.testcases;

import lamnguyenthanh.commons.BaseSetUp;
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


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test(priority = 1)
    public void testValidLogIn() {
        loginPage.LogIn("lamlam112.nguyenthanh@gmail.com","Thanhlam26@");
        waitForElementVisible(loginPage.getsuccessLocator(), Duration.ofSeconds(10));
        //Verify success registration
        assertTrue(loginPage.isLogInSuccess());
        signUpPage.logOut();
    }

    @Test(priority = 2)
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

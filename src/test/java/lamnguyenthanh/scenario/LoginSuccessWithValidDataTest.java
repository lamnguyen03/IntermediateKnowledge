package lamnguyenthanh.scenario;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pagesobject.DetailProductPage;
import lamnguyenthanh.pagesobject.LoginPage;
import lamnguyenthanh.pagesobject.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class LoginSuccessWithValidDataTest extends BaseSetUp {
    private WebDriver driver;
    private LoginPage loginPage;
    private SignUpPage signUpPage;



    @BeforeClass
    public void setUp() {
        driver = getDriver();
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);

    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
                {"lamlam112.nguyenthanh@gmail.com", "Thanhlam26@"}

        };
    }
    @Test(dataProvider = "loginData")
    public void testValidLogIn(String email, String password) {
        loginPage.LogIn(email, password);
        //Verify success registration
        assertTrue(loginPage.isLogInSuccess());
    }



    @AfterTest
    public void close() {
        driver.quit();
    }
}

package lamnguyenthanh.testcases;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class SignUpTest extends BaseSetUp {
    private WebDriver driver;
    private SignUpPage signUpPage;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        signUpPage = new SignUpPage(driver);

    }

    @DataProvider(name = "registrationData")
    public Object[][] getRegistrationData() {
        return new Object[][]{
                {"pqoew1.nguyenthcanh@gmail.com", "Password123"},
                {"nskfdd1.doe@examcple.com", "Password123"},
                {"fverfcnj1.doe@examcple.com", "Password123"}
        };
    }

    @Test(dataProvider = "registrationData")
    public void testValidRegistration(String email, String password){
        signUpPage.clickCreateAccountLink();
        signUpPage.setFirstName("Lam");
        signUpPage.setLastName("Nguyen");
        signUpPage.setEmail(email);
        signUpPage.setPassword(password);
        signUpPage.setConfirmPassword(password);
        signUpPage.clickCreateButton();

        // Verify success registration
        signUpPage.assertRegistrationSuccess();
        signUpPage.logOut();

    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}

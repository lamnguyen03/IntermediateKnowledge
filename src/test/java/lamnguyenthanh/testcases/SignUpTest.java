package lamnguyenthanh.testcases;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;


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
        String randomEmail1 = generateRandomEmail();
        String randomEmail2 = generateRandomEmail();

        return new Object[][]{
                {randomEmail1, "Password123"},
                {randomEmail2, "Password123"}
        };
    }

    private String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return uuid + "@example.com";
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
        assertTrue(signUpPage.assertRegistrationSuccess());
        signUpPage.logOut();

    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}

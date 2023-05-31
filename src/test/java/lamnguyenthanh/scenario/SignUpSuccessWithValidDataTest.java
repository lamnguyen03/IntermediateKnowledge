package lamnguyenthanh.scenario;

import lamnguyenthanh.commons.BaseSetUp;
import lamnguyenthanh.pagesobject.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.util.UUID;

import static org.testng.AssertJUnit.assertTrue;


public class SignUpSuccessWithValidDataTest extends BaseSetUp {
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
                {generateRandomEmail(), "Password123", generateRandomName(), generateRandomName()},
                {generateRandomEmail(), "Password123", generateRandomName(), generateRandomName()}
        };
    }
    private String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return uuid + "@example.com";
    }
    private String generateRandomName() {
        String uuid = UUID.randomUUID().toString();
        String name = "Name_" + uuid.substring(0, 8);
        return name;
    }

    @Test(dataProvider = "registrationData")
    public void testValidRegistration(String email, String password, String firstName, String lastName){
        signUpPage.clickCreateAccountLink();
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName(lastName);
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

package lamnguyenthanh.commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseSetUp {
    private WebDriverWait wait;

    private WebDriver driver;
    public static final String USERNAME = "lmthanhnguyn_6pzSPB";
    public static final String AUTOMATE_KEY = "YLazRe2CDJNax6vc5NKZ";
    public static final String url = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public WebDriver getDriver() {
        return driver;

    }

    //Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
    private void setDriver(String browserType, String URL) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver(URL);
                break;
            case "firefox":
                driver = initFirefoxDriver(URL);
                break;
            case "browserstack":
                driver = initBrowserStack(URL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver(URL);
        }
    }


    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case

    private static WebDriver initChromeDriver(String URL) {
        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return driver;
    }

    private static WebDriver initFirefoxDriver(String URL) {
        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(URL);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return driver;
    }
    private static WebDriver initBrowserStack(String URL) {
        System.out.println("Launching BrowserStack...");
        DesiredCapabilities caps = new DesiredCapabilities();


        // trình duyệt Chrome, phiên bản trình duyệt (latest), hệ điều hành (Mac), phiên bản hệ điều hành (10),

        // trình duyệt Chrome, phiên bản trình duyệt (latest), hệ điều hành (Linux), phiên bản hệ điều hành (Ubuntu),
//        caps.setCapability("browser", "Chrome");
//        caps.setCapability("browser_version", "latest");
//        caps.setCapability("os", "Linux");
//        caps.setCapability("os_version", "Ubuntu");

        // trình duyệt Chrome, phiên bản trình duyệt (latest), hệ điều hành (Windows), phiên bản hệ điều hành (10),
//        caps.setCapability("browser", "Chrome");
//        caps.setCapability("browser_version", "latest");
//        caps.setCapability("os", "Windows");
//        caps.setCapability("os_version", "10");

        caps.setCapability("name", "BrowserStack Test");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("browserstack.networkLogs", "true");
        caps.setCapability("browserstack.video", "true");

        WebDriver driver = null;

        try {
            driver = new RemoteWebDriver(new URL(url), caps);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            driver.navigate().to(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
    @Parameters({"browserType", "webURL"})
    @BeforeClass
    public void initializeTestBaseSetup(@Optional("chrome") String browserType, @Optional("https://magento.softwaretestingboard.com/") String webURL) {
        try {
            // Khởi tạo driver và browser
            setDriver(browserType, webURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
        //đợi cho đến khi tất cả các phần tử được tải xuống và sẵn sàng để thao tác.
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(1000);
        driver.quit();
    }
    public void waitForElementVisible(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}

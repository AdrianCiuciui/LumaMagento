package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest{

    public WebDriver driver;
    private static final Properties PROPERTIES = new Properties();
    @FindBy
    private By welcomeToSTBBannerConsentButton = By.xpath("//*[@class='fc-button fc-cta-consent fc-primary-button']//p[@class='fc-button-label']");




    @BeforeSuite
    public void readPropertiesFile() {
        try {
            FileInputStream input = new FileInputStream("src/test/java/base/constants.properties");
            PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @BeforeMethod
    public void setup()  {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--incognito"); // Runs a new, clean instance of the browser
        options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems in Docker
//        options.addArguments("--headless"); // The browser is not displayed
        options.addArguments("--disable-gpu"); // Disables the GPU, used when running headless
        options.addArguments("--start-maximized"); // Runs on the entire screen
        options.addArguments("--disable-features=PasswordCheck"); // No longer displays a pop-up mentioning that the "passwords are exposed in a data breach"
        driver = new ChromeDriver(options);
        driver.navigate().to(PROPERTIES.getProperty("url.base"));
        BasePageObject.setDriver(driver);
        explicitWait(1500);

        driver.findElement(welcomeToSTBBannerConsentButton).click();
        explicitWait(200);

    }


    //  Getters
    public static String getURLBase(){
        return PROPERTIES.getProperty("url.base");
    }

    public static String getURLCart(){
        return (getURLBase() + PROPERTIES.getProperty("url.cart"));
    }

    public static String getURLCheckoutStepOne(){
        return (getURLBase() + PROPERTIES.getProperty("url.checkout.one"));
    }

    public static String getURLCheckoutStepTwo(){
        return (getURLBase() + PROPERTIES.getProperty("url.checkout.two"));
    }

    public static String getURLCheckoutSuccess(){
        return (getURLBase() + PROPERTIES.getProperty("url.checkout.complete"));
    }

    public static String getHeroHoodieProductPage(){
        return (getURLBase() + PROPERTIES.getProperty("url.product.heroHoodie"));
    }

    public static String getRadiantTeeProductPage(){
        return (getURLBase() + PROPERTIES.getProperty("url.product.radiantTee"));
    }

    public static String getUsername(){
        return PROPERTIES.getProperty("username");
    }

    public static String getPassword(){
        return PROPERTIES.getProperty("password");
    }

    public void hoverOverElement(By by){
        WebElement target = this.driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(target).perform();
        explicitWait(300);
    }



    public void explicitWait(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}

package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePageObject {

    public static WebDriver driver;
    public static WebDriverWait driverWait;


    public static void setDriver(WebDriver driver){
        BasePageObject.driver = driver;
        Duration timeoutDuration = Duration.ofSeconds(12);
        driverWait = new WebDriverWait(driver, timeoutDuration);
    }



    public boolean isElementDisplayed(By locator){
        return !driver.findElements(locator).isEmpty();
    }

    public void inputValues(By element, String input){
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(input);
    }

    public void clickOnButton(By element){
        driver.findElement(element).click();
    }

    public String getTextFromField(By element){
        return driver.findElement(element).getText();
    }

}

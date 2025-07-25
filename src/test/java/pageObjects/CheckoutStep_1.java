package pageObjects;

import base.BasePageObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.By.xpath;

public class CheckoutStep_1 extends BasePageObject {

    private final By emailAddress = xpath("//div[@class=\"field required\"]//input[@id='customer-email']");
    private By firstName = xpath("//div[@class=\"control\"]//input[@name=\"firstname\"]");
    private By lastName = xpath("//div[@class=\"control\"]//input[@name=\"lastname\"]");
    private By streetAddress = xpath("//div[@class=\"control\"]//input[@name=\"street[0]\"]");
    private By city = xpath("//div[@class=\"control\"]//input[@name=\"city\"]");
    private By stateProvinceDropDown = xpath("//select[@name=\"region_id\"]");
    private By zipPostalCode = xpath("//div[@class=\"control\"]//input[@name=\"postcode\"]");
    private By phoneNumber = xpath("//div[@class=\"control _with-tooltip\"]//input[@name=\"telephone\"]");
    private By shippingMethodAllRadioButton = xpath("//input[@type=\"radio\"]");
    private By stateProvinceAllList = xpath("//option[@data-title]");
    private By nextStepButton = xpath("//*[@class=\"button action continue primary\"]");



    public void inputMandatoryValues(boolean isUserLoggedIn){
        String randomString = RandomStringUtils.randomAlphanumeric(10);
        int randomNumber = (int)(Math.random() * 10) + 2;

        if (!isUserLoggedIn) {
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress));
            driver.findElement(emailAddress).sendKeys(randomString + "@tralala.com");
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
            inputValues(firstName, randomString);
            inputValues(lastName, randomString);
            inputValues(streetAddress, randomString);
            inputValues(city, randomString);
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(zipPostalCode));
            inputValues(zipPostalCode, randomString);
            inputValues(phoneNumber, randomString);
            WebElement dropdown = driver.findElement(stateProvinceDropDown);
            Select select = new Select(dropdown);
            select.selectByIndex(randomNumber);
        }

        driverWait.until(ExpectedConditions.visibilityOfElementLocated(shippingMethodAllRadioButton));
        driver.findElements(shippingMethodAllRadioButton).get(0).click();
    }


    public WebElement getNextStepButton() {
        return driver.findElement(nextStepButton);
    }

}

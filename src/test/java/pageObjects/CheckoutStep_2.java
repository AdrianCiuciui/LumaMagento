package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.openqa.selenium.By.xpath;

public class CheckoutStep_2 extends BasePageObject {

    private By placeOrderActiveButton = xpath("//div[@class=\"primary\"]//button[@class=\"action primary checkout\"]");
    private By placeOrderDeactivatedButton = xpath("//button[@class=\"action primary checkout disabled\"]");
    private By paymentMethodTitle = xpath("//*[@class=\"items payment-methods\"]//div[@class='step-title' and @data-role='title']");
    private By editShipToButton = xpath("//div[@class=\"shipping-information-title\"]//button[@class=\"action action-edit\"]");
    private By sameAddressCheckboxButton = xpath("//input[@name=\"billing-address-same-as-shipping\"]");

    private By alternativeAddressCity = xpath("//*[@name=\"billingAddresscheckmo.city\"]//input[@name=\"city\"]");


    public void clickOnPlaceOrderButton(){
        driverWait.until(ExpectedConditions.elementToBeClickable(placeOrderActiveButton));
        driver.findElement(placeOrderActiveButton).click();
    }

    public boolean isPageTitleDisplayed(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodTitle));
        return isElementDisplayed(paymentMethodTitle);
    }

    public WebElement getEditShipToButton() {
        return driver.findElement(editShipToButton);
    }

    public WebElement getSameAddressCheckoutButton(){
        return driver.findElement(sameAddressCheckboxButton);
    }

    public WebElement getPlaceOrderActiveButton(){
        return driver.findElement(placeOrderActiveButton);
    }

    public WebElement getPlaceOrderDeactivatedButton(){
        return driver.findElement(placeOrderDeactivatedButton);
    }

    public WebElement getAACity(){
        return driver.findElement(alternativeAddressCity);
    }

}

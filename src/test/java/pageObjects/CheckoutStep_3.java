package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.By.xpath;

public class CheckoutStep_3 extends BasePageObject {

    private final By pageTitle = xpath("//*[@data-ui-id=\"page-title-wrapper\"]");
    private final By continueShoppingButton = xpath("//*[@class=\"action primary continue\"]");
    private final By checkoutSuccessSection = xpath("//div[@class='checkout-success']");





    public boolean checkIfPageTitleIsDisplayed(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        return isElementDisplayed(pageTitle);
    }

    public boolean checkIfContinueShoppingButtonIsDisplayed(){
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButton));
        return isElementDisplayed(continueShoppingButton);
    }

    public boolean isCheckoutSuccessSectionDisplayed(){
        return isElementDisplayed(checkoutSuccessSection);
    }

}

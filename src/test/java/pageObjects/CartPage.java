package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;


import static org.openqa.selenium.By.xpath;


public class CartPage extends BasePageObject {

    private final By pageTitle = xpath("//span[@data-ui-id='page-title-wrapper']");
    private final By emptyCartSection = xpath("//div[@class='cart-empty']");





    public boolean isPageTitleDisplayed(){
        return isElementDisplayed(pageTitle);
    }

    public boolean isTheEmptyCartSectionDisplayed(){
        return isElementDisplayed(emptyCartSection);
    }

}

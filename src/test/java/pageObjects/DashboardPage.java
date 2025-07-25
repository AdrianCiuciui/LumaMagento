package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.xpath;

public class DashboardPage extends BasePageObject {

    private By allProductDetails = xpath("//*[@class=\"product-item-details\"]");
    private By signInButton = xpath("//*[@class=\"panel header\"]//li[@class=\"authorization-link\"]");




    /**
     * @param position  Position must be between 1 and 6
     * @return default value is 4 in case the inputted value is not in range
     */
    public WebElement getProductDetails(int position){
        if (position < 1 || position > 6) {
            System.out.println("Position's value must be between 1 and 6. Will return default value 4");
            return driver.findElements(allProductDetails).get(4);
        }
        return driver.findElements(allProductDetails).get(position);
    }

    public WebElement getSignInButton(){
        return driver.findElement(signInButton);
    }

}

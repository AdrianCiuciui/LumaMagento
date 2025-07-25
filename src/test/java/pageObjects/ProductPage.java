package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class ProductPage extends BasePageObject {


    private By sizeMButton = id("option-label-size-143-item-168");
    private By colorBlackButton = id("option-label-color-93-item-49");
    private By colorOrangeButton = id("id=\"option-label-color-93-item-56\"");
    private By addToCartButton = xpath("//div[@class=\"actions\"]//*[@class=\"action primary tocart\"]");




    public void clickOnMSizeButton(){
        driver.findElement(sizeMButton).click();
    }

    public void clickOnColorBlack(){
        driver.findElement(colorBlackButton).click();
    }

    public void clickOnColorOrange(){
        driver.findElement(colorOrangeButton).click();
    }

    public void clickOnAddToCart(){
        driver.findElement(addToCartButton).click();
    }

}

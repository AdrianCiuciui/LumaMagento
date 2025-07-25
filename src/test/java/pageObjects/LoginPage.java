package pageObjects;

import base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class LoginPage extends BasePageObject {

    private By emailInputField = xpath("//*[@title=\"Email\"]");
    private By passwordInputField = xpath("//*[@name=\"login[password]\"]");



    public WebElement getEmailInputField(){
        return driver.findElement(emailInputField);
    }

    public WebElement getPasswordInputField(){
        return driver.findElement(passwordInputField);
    }

}

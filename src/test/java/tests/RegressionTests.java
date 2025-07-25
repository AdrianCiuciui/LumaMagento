package tests;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;

import pageObjects.*;


public class RegressionTests extends BaseTest {

    private CartPage cartPage;
    private ProductPage productPage;
    private CheckoutStep_1 checkoutStep1;
    private CheckoutStep_2 checkoutStep2;
    private CheckoutStep_3 checkoutStep3;
    private DashboardPage dashboardPage;
    private LoginPage loginPage;



    @Override
    @BeforeMethod
    public void setup(){
        super.setup();
        cartPage = new CartPage();
        productPage = new ProductPage();
        checkoutStep1 = new CheckoutStep_1();
        checkoutStep2 = new CheckoutStep_2();
        checkoutStep3 = new CheckoutStep_3();
        dashboardPage = new DashboardPage();
        loginPage = new LoginPage();
    }





    @Test(description = "Checks that the empty cart section is displayed when there are no products in the cart")
    public void checkEmptyCartPage_NotBeingLoggedIn(){
        driver.navigate().to(getURLCart());
        assertThat("the title is displayed", cartPage.isPageTitleDisplayed());
        assertThat("the title is displayed", cartPage.isTheEmptyCartSectionDisplayed());
    }


    @Test(description = "This is a happy flow with one product.",
            dataProvider = "isLoggedIn")
    public void performE2EFlow(boolean isUserLoggedIn){
        //  Prerequisites
        shouldTheUserLogIn(isUserLoggedIn);
        addProductToCartFromProductPage(4);

        //  Checkout - 1
        driver.navigate().to(getURLCheckoutStepOne());
        fillInValuesOnTheCheckoutStep1(isUserLoggedIn);
        checkoutStep1.getNextStepButton().click();  //todo  one way of doing things
        explicitWait(1000);

        //  Checkout - 2
        explicitWait(3000);
        assertTrue(checkoutStep2.isPageTitleDisplayed());
        checkoutStep2.clickOnPlaceOrderButton();    //todo  another way of doing things

        //  Checkout - 3 final
        explicitWait(2000);
        assertTrue(checkoutStep3.checkIfPageTitleIsDisplayed());
        assertTrue(checkoutStep3.checkIfContinueShoppingButtonIsDisplayed());
        assertTrue(checkoutStep3.isCheckoutSuccessSectionDisplayed(), "Checkout success section is not displayed");

        explicitWait(200);

        //  Hit Back to check that the order is no longer available to the user
        driver.navigate().back();
        String currentURL = driver.getCurrentUrl();
        assertThat(currentURL, containsString(getURLCart()));
        assertThat(cartPage.isTheEmptyCartSectionDisplayed(), is(true));
    }


    @Test(description = "Test some flows in the payments page",
            dataProvider = "isLoggedIn")
    public void checkoutPage2PaymentsTest_NotBeingLoggedIn(boolean isUserLoggedIn){
        //  Prerequisites
        shouldTheUserLogIn(isUserLoggedIn);
        addProductToCartFromProductPage(1);

        //  Checkout - 1
        driver.navigate().to(getURLCheckoutStepOne());
        fillInValuesOnTheCheckoutStep1(isUserLoggedIn);
        checkoutStep1.getNextStepButton().click();
        explicitWait(5000);

        //  Testing the redirect
        checkoutStep2.getEditShipToButton().click();
        assertThat(driver.getCurrentUrl(), is(getURLCheckoutStepOne()));

        //  Going back to Step 2
        checkoutStep1.getNextStepButton().click();
        explicitWait(1000);

        //  Checking different shipping address
        assertThat(checkoutStep2.getPlaceOrderActiveButton().isEnabled(), is(true));
        assertThat(checkoutStep2.getAACity().isDisplayed(), is(false));
        explicitWait(1000);
        checkoutStep2.getSameAddressCheckoutButton().click();
        assertThat(checkoutStep2.getPlaceOrderDeactivatedButton().isDisplayed(), is(true));

        if (!isUserLoggedIn) {
            assertThat(checkoutStep2.getAACity().isDisplayed(), is(true));
        }
        explicitWait(1000);
        checkoutStep2.getSameAddressCheckoutButton().click();
        assertThat(checkoutStep2.getPlaceOrderActiveButton().isEnabled(), is(true));
        assertThat(checkoutStep2.getAACity().isDisplayed(), is(false));
    }




    // Actions methods

    private void addProductToCartFromProductPage(int position){
        String url;
        switch (position){
            case 1: url = getRadiantTeeProductPage();
            case 2: url = getHeroHoodieProductPage();
            default: url = getHeroHoodieProductPage();
        }
        driver.navigate().to(url);

        productPage.clickOnMSizeButton();
        productPage.clickOnColorBlack();
        explicitWait(3000);
        productPage.clickOnAddToCart();
        explicitWait(3000);
    }

    private void fillInValuesOnTheCheckoutStep1(boolean isUserLoggedIn){
        explicitWait(300);
        checkoutStep1.inputMandatoryValues(isUserLoggedIn);
        explicitWait(1000);
    }

    private void shouldTheUserLogIn(boolean bool){
        if (bool) {
            dashboardPage.getSignInButton().click();
            explicitWait(3000);
            loginPage.getEmailInputField().sendKeys(getUsername());
            loginPage.getPasswordInputField().sendKeys(getPassword());
            loginPage.getPasswordInputField().sendKeys(Keys.ENTER);
        }
    }



    @DataProvider(name = "isLoggedIn")
    public Object[][] isLoggedIn(){
        return new Object[][]{
                {true},
                {false}
        };
    }



}

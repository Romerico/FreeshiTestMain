package com.testCases;

import com.base.BaseClass;
import com.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_020 extends BaseClass {

//Purpose: Verify pick up options: ASAP

//****************Test Steps****************
//1. Enter the Email id
//2.Enter password
//3.Click login
//4.Click on Order Now.
//5. Accept the Geo location permissions.
//6.Check if the default option pickup is activated in the location page.
//7.Click on pick up here button below the pickup location.
//8. Select the required items form menu options.
//9. a.Customize
//   b. Add to bag
//10. Click on addmore / checkout.
//11. Click on later option in the My Order page.
//12. Select the time from the available time slots for tomorrow from the drop down menu.
//13.Click on Ok.
//14. Click on place order.
//15. Click on Credit card options
//16. Click on Done in the order receipt.


//****************Expected Results****************

//1. User should be able to enter the email Id.
//2. User should be able to enter the Password.
//3. Upon clicking on login, user should be ale to navigate to Welcome Freshii page.
//4.Order Now button should be clickable.
//5. User is expected to navigate to Geo location page.
//6. Clicking on accept, user should be able to see the closest locations.
//7. Upon clicking on pickup here, user should be able to navigate to menu screen.
//8. After selecting the items menu, the screen should navigate to customize / add to bag page.
//9. a. Ingredients should be clickable when user wants to add / remove items.
//   b. User should be able to add the menu item to the cart.
//10. a. When clicking on add more, the user should be able to go to menu page.
//    b.When click on Checkout, the user should be able to go to order summary page.
//11.User should be able to select the later option in the My order page.
//12. A new pop-up window displaying time slots for today and tomorrow should appear.
//13. User should be able to select the time slot for tomorrow from the available time slots.
//14. When click on place order, user should be able to navigate to payment page.
//15. When clicking
//    a. Pay by credit card, user should be able to add , change or delete the exisiting card.
//    b. Pay by gift card, user should be able to make payment using the entered gift card.
//16.On clicking Done, the page navigates back to My Order page.
//17. When click on place order, user should be able to view the order receipt.
//18. a. When click on view receipt, user should be able to view the receipt.
//    b. When click on Help, user should be able to navigate to Support page.
//    c. When click on re-order the user should be able to re-order the menu.

    @Test
    public void FWA_020() throws IOException, InterruptedException {

        logger.info("Started FWA_020");

//	init pages and methods for further testing
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        OrderPage op = new OrderPage(driver);
        PickupPage pp = new PickupPage(driver);
        SoftAssert softassert = new SoftAssert();

//submit random zip as it's mandatory and shouldn't be hardcoded

        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");



//clicking profile image in order to get popup with options to proceed with signUp/signIn

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

//1. User should be able to enter the email Id
//2. User should be able to enter the Password
        logp.loginToFreshii();


//Validating whether home page is displayed after login

        Boolean verify = logp.validateHomePage();
        if (verify) {
            softassert.assertTrue(true);
            logger.info("Login is Successful");
        } else {
            softassert.assertTrue(false);
            logger.info("Login is not Successful");
        }

//4.Click on Order Now.
        op.clickOrderButton();

//Confirming store
        op.confirmStore();

//Checking if PickUp option is selected by default
        softassert.assertTrue(ul.checkEnabledColorForPickUpButton().contains("#249e6b"), "pickup option is not selected by default");
        logger.info("pickup button color : " + ul.checkEnabledColorForPickUpButton() + " hence it is selected by default");

//Select the food item of choice

        op.selectSoup();

//7. After selecting the items menu, the screen should navigate to customize / add to bag page.
//8. a. Ingredients should be clickable when user wants to add / remove items.
//    b. User should be able to add the menu item to the cart.

        op.selectAddIngredients();//Moving to 'Add ingredient tab
        op.addChicken();//selecting chicken as an ingredient
        op.clickAddToCart();//clicking add to card
        op.addUpsellItem();//adding 1 upsell item


        op.clickConfirmAddToCart();

        logger.info("Order added to cart, with system generated message: "+ op.getProductSuccessfullyAddedText());

//Go to checkout page
        op.clickCheckOutButton();
        op.refuseAdditionals();


//Checking if PickUp option is selected by default
        softassert.assertTrue(pp.checkEnabledColorForPickUpButton().contains("#249e6b"), "pickup option is not selected by default");
        logger.info("pickup option is selected by default");

//Schedule Order for tomorrow
        pp.selectTomorrow();


        softassert.assertTrue(pp.getPickUptime().contains("Tomorrow"),
                "Scheduled time is not tomorrow : " + pp.getPickUptime());

        logger.info("Pickup has been scheduled for: " + pp.getPickUptime());

        softassert.assertAll();

        logger.info("Scheduled tomorrow : " + pp.getPickUptime());

        logger.info("completed FWA_020");


    }
}

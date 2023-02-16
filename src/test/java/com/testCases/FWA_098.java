package com.testCases;
//"1. Click on continue with google.
//2. Enter Email ID
//3. Click on Order now in the welcome page
//4. Accept the Geo location permissions
//5. Click on Pick up here from the location page
//6. Select the required items from the menu
//7. a.Customize
//    b. Add to bag
//8.   Select Protein Options
//9. Click on addmore / checkout
//10. Select the menu item required from the upsell menu item.
//11. Click on Add to order.


//"1. Continue with google option should be clickable
//2. User should be able to enter the email Id
//3. User should be able to enter the Password
//4. Upon clicking on login, user should be ale to navigate to Welcome Freshii page
//5. User is expected to navigate to Geo location page
//6. Clicking on accept, user should be able to see the closest locations
//7. Upon clicking on pickup here, user should be able to navigate to menu screen
//8. After selecting the salad, the screen navigates to customize / add to bag page.
//9. a. Ingredients should be clickable when user wants to add / remove.
//     b. User should be able to add the salad to the cart.
//10. a. When click on the ingredient to add protein, it should add to the salad.
//       b. Upon clicking on No Thanks, user should be able to navigate to the nutrition page.
//11. a. When clicking on add more, the user should be able to go to menu page.
//       b.When click on Checkout, the user should be able to view My order summary page.
//12. Upsell window opens and the user should be able to select or skip that page.
//13. Clicking on Add to order should add the upsell item to order.
//14. User should be able to change the quantity of the upsell item and should be able to customize the selected menu item.


import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.OrderPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_098 extends BaseClass {

    @Test
    public void FWA_098() throws InterruptedException, IOException {

        logger.info("Started FWA_098");

        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        OrderPage o = new OrderPage(driver);
        SoftAssert softassert = new SoftAssert();

        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");

//click profile image in order to get popup with options to proceed with signup

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

//"1. Continue with google option should be clickable.

        logp.gButtonClick();

//clicking the account in a popup window

        logp.authorizeWithGoogle();
         logger.info("authorized the google account, URL is : " + driver.getCurrentUrl());
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google, URL is : " + driver.getCurrentUrl());

//3. Click on Order now in the welcome page
        o.clickOrder();
        o.confirmShop();

//6. Select the required items from the menu
        o.chooseSoup();

//8.Select Protein Options
        o.setAddIngresients();
        o.addChicken();

//9. Click on addmore / checkout
        o.setAddToCart();

//12. Upsell window opens and the user should be able to select or skip that page.
//13. Clicking on Add to order should add the upsell item to order.
//14. User should be able to change the quantity of the upsell item and should be able to customize the selected menu item.

//10. Select the menu item required from the upsell menu item.
        o.addUpsellItem();

        softassert.assertFalse(o.additionalList().equalsIgnoreCase("\"1 x Chicken\""),
                "There only protein that was picked before in the order");

        o.confirmAddToCart();

        softassert.assertTrue(o.noticeText().equalsIgnoreCase("Product succesfully added!"),
                "product either was not added or user gets different message: " + o.noticeText());
        logger.info("Order added to cart");




        softassert.assertAll();

//        o.goToCartAndDeleteStuffForFutureTests();

        logger.info("The upsell item is in the order list on a confirmation stage");
        logger.info("Completed FWA_098");

    }
}

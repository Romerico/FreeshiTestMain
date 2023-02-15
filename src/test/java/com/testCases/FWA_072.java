package com.testCases;

import com.base.BaseClass;
import com.pageObjects.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.text.ParseException;
//"1.Click on Continue with Google.
//2. Enter the Email id
//3.Enter password
//4.Click on Order Now.
//5. Accept the Geo location permissions.
//6.Check if the default option pickup is activated in the location page.
//7.Click on pick up here button below the pickup location.
//8. Select the required items form menu options.
//9. a.Customize
//    b. Add to bag
//10. Click on addmore / checkout.
//11. Click on later option in the My Order page.
//12. Select the time from the available time slots for tomorrow from the drop down menu.
//13.Click on Ok.


//"1. User should be able to enter the email Id.
//2. User should be able to enter the Password.
//3. User should be ale to navigate to Welcome Freshii page.
//4.Order Now button should be clickable.
//5. User is expected to navigate to Geo location page.
//6. Clicking on accept, user should be able to see the closest locations.
//7. Upon clicking on pickup here, user should be able to navigate to menu screen.
//8. After selecting the items menu, the screen should navigate to customize / add to bag page.
//9. a. Ingredients should be clickable when user wants to add / remove items.
//    b. User should be able to add the menu item to the cart.
//10. a. When clicking on add more, the user should be able to go to menu page.
//      b.When click on Checkout, the user should be able to go to order summary page.
//11.User should be able to select the later option in the My order page.
//12. A new pop-up window displaying time slots for today and tomorrow should appear.
//13. User should be able to select the time slot for tomorrow from the available time slots. 

public class FWA_072 extends BaseClass {
    @Test
    public void FWA_071() throws IOException, InterruptedException, ParseException {
        logger.info("Started FWA_072");
//		init pages and methods for further use
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        OrderPage o = new OrderPage(driver);
        PickupPage p = new PickupPage(driver);
        SoftAssert softassert = new SoftAssert();

        if (browser.equalsIgnoreCase("mobile-web")) {
            lp.setContinueInBrowser();
        }

//submit random zip as it's obligatory and shouldn't be hardcoded
//"1. User should be able to enter the email Id
//2. User should be able to enter the Password

        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");

//click profile image in order to get popup with options to proceed with signup

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

//continue with google

        logp.gButtonClick();

//clicking the account in a popup window

        logp.authorizeWithGoogle();
        logger.info("authorized the google account, URL is : " + driver.getCurrentUrl());
        softassert.assertTrue(driver.getCurrentUrl().contains("home"),
                "User is not on welcome page after login through Google, URL is : " + driver.getCurrentUrl());


//4.Click on Order Now.
        o.clickOrder();
        o.confirmShop();

//6.Check if the default option pickup is activated in the location page.

        softassert.assertTrue(ul.pickupBtnActiveColor().contains("#249e6b"), "pickup button is not active by default");
        logger.info("pickup button color : " + ul.pickupBtnActiveColor());

//6. Select the required items from the menu
        o.chooseSoup();

//7. After selecting the items menu, the screen should navigate to customize / add to bag page.
//8. a. Ingredients should be clickable when user wants to add / remove items.
//    b. User should be able to add the menu item to the cart.

////9. a.Customize
////    b. Add to bag

        o.setAddIngresients();
        o.addChicken();
        o.setAddToCart();


//10. Select the menu item required from the upsell menu item.
        o.addUpsellItem();

        softassert.assertFalse(o.additionalList().equalsIgnoreCase("\"1 x Chicken\""),
                "There only protein that was picked before in the order");

        o.confirmAddToCart();

        softassert.assertTrue(o.noticeText().equalsIgnoreCase("Product succesfully added!"),
                "product either was not added or user gets different message: " + o.noticeText());
        logger.info("Order added to cart");


//10.The default option should be ASAP in the My order page for the following criterias:
//      a: When the selected store is opened.
//      b.Pick up time should be 15 minutes after store opening or 15 minutes before store closing time.
//       c. Other time, ASAP option should be greyed out.

        o.goToCart();
        o.refuseAdditionals();

        softassert.assertTrue(p.pickupBtnClr().contains("#249e6b"), "pickup button is not active by default");
        logger.info("pickup button color : " + p.pickupBtnClr());

//11. Click on later option in the My Order page.
//12. Select the time from the available time slots for today from the drop down menu.
//13.Click on Ok.

        p.setTomorrow();
//13. User should be able to select the time slot for today from the available time slots.


        softassert.assertTrue(p.getPickUptime().contains("Tomorrow"),
                "Scheduled time is not tomorrow : " + p.getPickUptime());


        softassert.assertAll();

        logger.info("Scheduled tomorrow : " + p.getPickUptime());

        logger.info("completed FWA_072");


    }
}
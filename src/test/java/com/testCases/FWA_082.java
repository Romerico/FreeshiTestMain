package com.testCases;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.OrderPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
//"1. Click on continue with Google.
//2.Enter gmail id.
//3.Enter password
//4.Click on order now in the welcome page.
//5.Accept the geolocation permission.
//6.Select the pick up location.
//7.Click on custom bowl in the bowl menu.
//8. Select the protein option.
//9. Select the base options.
//10. Select premium toppings.
//11. Select regular toppings.
//12. Select Add dressing.
//13. Click on Add to Bag.

//"1. User should be able to enter the email Id.
//2. User should be able to enter the Password.
//3. Upon clicking on login, user should be ale to navigate to Welcome Freshii page.
//4. Order Now button should be clickable.
//5. User is expected to navigate to Geo location page.
//6. Clicking on accept, user should be able to see the closest locations.
//7. Upon clicking on pickup here, user should be able to navigate to menu screen.
//8. After selecting the custom bowl, the screen navigates to customize / add to bag page.
//9.  Protein options should be clickable when user wants to add / remove protein options .
//10. Base options should be clickable and user should be able to add  or remove one or more base ingredients.
//11. Premium toppings should be clickable and user should be able to add or remove the toppings.
//12. Regular toppings should be clickable and user should be able to add or remove one or more toppings.
//13. Dressing options should be clickable and user should be able to add one or more dressing options.
//14. Clicking on Add to Bag navigates to upsell window.

public class FWA_082 extends BaseClass {
    @Test
    public void FWA_082() throws IOException, InterruptedException {
        logger.info("Started FWA_082");

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
        logger.info("User is in the menu page");

//6. Select the required items from the menu
        o.chooseBowl();

        o.clickGotIt();
        logger.info("User is in the product edit page");

//8.Select Protein Options
//        o.setAddIngresients();
//        o.addChicken();

//10. Select premium toppings.
//11. Select regular toppings.
//12. Select Add dressing.
        o.addItem();
        o.addItem();

        o.setIncludedDressing();
        o.addItem();

        o.setIncludedtoppings();
        o.addItem();

        logger.info("Edited order");

//13. Click on Add to Bag.
        o.setAddToCart();

        logger.info("clicked add to cart");


//Select Protein Options **in a popup
        o.addPopupChicken();
        logger.info("Added protein through the popup");


        softassert.assertFalse(o.additionalList().equalsIgnoreCase("\"1 x Chicken\""),
                "There only protein that was picked before in the order");
        logger.info("all items are displayed in order details: " + o.additionalList());
        
        o.confirmAddToCart();
        logger.info("clicked add to cart from protein popup");

        softassert.assertTrue(o.noticeText().equalsIgnoreCase("Product succesfully added!"),
                "product either was not added or user gets different message: " + o.noticeText());
        logger.info(o.noticeText());
        logger.info("Order added to cart");




        softassert.assertAll();

//        o.goToCartAndDeleteStuffForFutureTests();

        logger.info("Completed FWA_082");


    }

}

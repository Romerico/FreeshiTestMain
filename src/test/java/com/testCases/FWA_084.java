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
//7.Click on custom smoothie in the smoothie menu.
//8. Select the smoothie Base (min 1, max 4).
//9. Select the smoothie protein (optional).
//10. Select smoothie topping (min1, max 3).
//11. Click on Add to Bag.


//"1. User should be able to enter the email Id.
//2. User should be able to enter the Password.
//3. Upon clicking on login, user should be ale to navigate to Welcome Freshii page.
//4. Order Now button should be clickable.
//5. User is expected to navigate to Geo location page.
//6. Clicking on accept, user should be able to see the closest locations.
//7. Upon clicking on pickup here, user should be able to navigate to menu screen.
//8. After selecting the custom smoothie, the screen navigates to customize / add to bag page.
//9. Smoothie base options should be clickable when user wants to add / remove base ingredients.
//10. Smoothie protein should be clickable and user should be able to add protein options.
//11. Smoothie topping should be clickable and user should be able to add or remove the toppings.
//12. Clicking on Add to Bag navigates to upsell window.
//13. User should be able to type special instructions if needs to be notified to the seller.
//14. Done button should be clickable.
//15. When click on Checkout, the user should be able to view My order summary page.


public class FWA_084 extends BaseClass {

    @Test
    public void FWA_084() throws IOException, InterruptedException {

        logger.info("Started FWA_084");

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
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google," +
                " URL is : " + driver.getCurrentUrl());

//3. Click on Order now in the welcome page
        o.clickOrder();
        o.confirmShop();
        softassert.assertTrue(driver.getCurrentUrl().contains("stores"), "User is not on menu page after clicking order button," +
                " URL is : " + driver.getCurrentUrl());

//7.Click on custom soup in the soup menu.
//no custom smoothie. go with random available prescribed

//10. Smoothie protein should be clickable and user should be able to add protein options.
//11. Smoothie topping should be clickable and user should be able to add or remove the toppings.
        o.chooseSmoothie();

        o.addItem();

        o.setAddToCart();
        softassert.assertTrue(o.additionalList().contains("Protein"),
                "No additional protein in smoothie");
        logger.info("all items are displayed in order details: " + o.additionalList());

        o.confirmAddToCart();

        softassert.assertTrue(o.noticeText().equalsIgnoreCase("Product succesfully added!"),
                "product either was not added or user gets different message: " + o.noticeText());
        logger.info(o.noticeText());
        logger.info("Order added to cart");


        softassert.assertAll();

        o.goToCartAndDeleteStuffForFutureTests();

        logger.info("Completed FWA_084");


    }
}

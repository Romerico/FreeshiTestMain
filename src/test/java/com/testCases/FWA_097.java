package com.testCases;

//"1.Click on continue with google.
//2. Enter Email ID
//3. Click on Order now in the welcome page
//4. Accept the Geo location permissions
//5. Click on Pick up here from the location page
//6. Click on the dieteray preference option in the menu page.
//7. Choose the items you would like to exclude from your diet.
//8. Click on Done."

//"1. Continue with google option should be clickable.
//2. User should be able to enter the email Id.
//3. Upon clicking on login, user should be ale to navigate to Welcome Freshii page.
//4. User is expected to navigate to Geo location page.
//5. Clicking on accept, user should be able to see the closest locations.
//6. Upon clicking on pickup here, user should be able to navigate to menu screen.
//7. Dietary preferences option should be clickable.
//8. User should be able to select more than one item that needs to be excluded from his/her diet.
//9. Clicking on Done, the excluded items gets colour coded in all the menu items."

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.OrderPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_097 extends BaseClass {

    @Test
    public void FWA_097() throws IOException, InterruptedException {

        logger.info("Started FWA_097");

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

        o.clickOrder();
//6. Upon clicking on pickup here, user should be able to navigate to menu screen.
        o.confirmShop();

//7. Dietary preferences option should be clickable.
        o.allergensClick();
        softassert.assertTrue(o.allergenHeader(), "Dietary preferences dialogues is not displayed");
        logger.info("Clicked dietary preferences");

//8. User should be able to select more than one item that needs to be excluded from his/her diet.
        o.checkAllergen();
        o.setSubmitAllergens();

        softassert.assertEquals(o.allergenQuantity(), "2");
        logger.info("Selected over 1 allergens");

//9. Clicking on Done, the excluded items gets colour coded in all the menu items."

        softassert.assertTrue(o.redTagVisible(), "no red tags displayed");
        logger.info("certain ingredients tagged red");

        o.allergensClick();
        o.checkAllergen();
        o.setSubmitAllergens();



        softassert.assertAll();
        logger.info("Completed FWA_097");


    }
}

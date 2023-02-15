package com.testCases;

//"1. Click on continue with google.
//2. Enter the Email id
//3.Click on Order Now.
//4. Accept the Geo location permissions.
//5.Check if the default option pickup is activated in the location page.
//6. Select the info option for any displayed location"

//"1. Continue with google option should be clickable.
//2. User should be able to enter the email Id.
//3. user should be ale to navigate to Welcome Freshii page.
//4. User is expected to navigate to Geo location page.
//5. Clicking on accept, user should be able to see the closest locations.
//6. Clicking on to the info option of the particular location displays the following:
//              a. Location picture
//              b. Address
//              c. Weekly schedule
//              d. amenities
//              e. Phone number
//              f. directions/pick up button"

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.OrderPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_099 extends BaseClass {

    @Test
    public void FWA_099() throws IOException, InterruptedException {

        logger.info("Started FWA_099");

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

//3. user should be ale to navigate to Welcome Freshii page.

        logp.authorizeWithGoogle();
         logger.info("authorized the google account, URL is : " + driver.getCurrentUrl());
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google, URL is : " + driver.getCurrentUrl());
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google");

//3.Click on Order Now.
//4. Accept the Geo location permissions.
//geolocation is requested at locations page, so user goes there

        ul.clickStoresLink();


        logger.info("clicked shops link and accepted geolocation");

//5.Check if the default option pickup is activated in the location page.

        softassert.assertTrue(ul.pickupBtnActiveColor().contains("#249e6b"), "pickup button is not active by default");
        logger.info("pickup button color : " + ul.pickupBtnActiveColor());



//5. Clicking on accept, user should be able to see the closest locations.

        softassert.assertTrue(ul.closestLocationDisplayed(), "first location displayed is not the closest");
        logger.info("user is shown the closes locations first, which is : " + ul.closestLocation());



//6. Select the info option for any displayed location"
//6. Clicking on to the info option of the particular location displays the following:
//              a. Location picture
//              b. Address
//              c. Weekly schedule
//              d. amenities
//              e. Phone number
//              f. directions/pick up button"

        ul.infoClick();
        logger.info("clicked Info");

        softassert.assertTrue(ul.locationCardDisplayed(), "Location card is not displayed");

        softassert.assertTrue(ul.addressDisplayed(), "address is not displayed on a card");

        softassert.assertEquals(ul.scheduleDaysAmount(), 7);

        softassert.assertTrue(ul.shopPhone(), "phone is not displayed on a card");

        logger.info("all testable features are visible");




        softassert.assertAll();
        logger.info("Completed FWA_099");
    }
}

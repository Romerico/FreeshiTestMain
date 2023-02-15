package com.testCases;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_018 extends BaseClass {

//Purpose: Verify User -Profile options : My History

//****************Test Steps****************
//1. Enter Email ID
//2. Enter Password
//3. Click Login
//4. Click User Pofile
//5.Click on My History.

//****************Expected Results****************

//1. User should be able to enter the email Id
//2. User should be able to enter the Password
//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page
//4. User should able to view the options in the user profile
//5. Upon clicking on My History the user should be able to navigate to the Order History page.

    @Test
    public void FWA_018() throws IOException, InterruptedException {

        logger.info("Started FWA_018");

//	init pages and methods for further testing
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        SoftAssert softassert = new SoftAssert();

//submit random zip as it's mandatory and shouldn't be hardcoded

        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");

//1. User should be able to enter the email Id
//2. User should be able to enter the Password

//clicking profile image in order to get popup with options to proceed with signUp/signIn

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

        logp.loginToFreshii();


//Validate whether home page is displayed after login
//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page
        Boolean verify = logp.validateHomePage();
        if (verify) {
            softassert.assertTrue(true);
            logger.info("Login is Successful");
        } else {
            softassert.assertTrue(false);
            logger.info("Login is not Successful");
        }

//4. User should be able to view the options in the user profile
        logp.clickLoggedInUserName();

        softassert.assertTrue(ul.optionsList() > 1);
        logger.info("there are options in the list");

//5. Upon clicking on My Account the user should be able to view the 'Order History' page
        ul.clickOrderHistory();

        softassert.assertTrue(ul.titleText().contains("Order History"),
                "User landed to the page other then 'Order History', title is : " + ul.titleText());

        softassert.assertAll();
        logger.info("Upon clicking on the 'Order History' the user landed on 'Order History' page");
        softassert.assertAll();
        logger.info("Completed FWA_018");
    }
}
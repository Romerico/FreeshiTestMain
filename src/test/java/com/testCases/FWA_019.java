package com.testCases;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_019 extends BaseClass {

//Purpose: Verify User -Profile options : SignOut

//****************Test Steps****************
//1. Enter Email ID
//2. Enter Password
//3. Click Login
//4. Click User Pofile
//5. Click signout

//****************Expected Results****************
//1. User should be able to enter the email Id
//2. User should be able to enter the Password
//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page.
//4. User should be able to view the options in the user profile.
//5. Upon clicking on Sign out the user should be able to sign out from the   account.

    @Test
    public void FWA_019() throws IOException, InterruptedException {

        logger.info("Started FWA_019");

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
            logger.info("Login is Successfull");
        } else {
            softassert.assertTrue(false);
            logger.info("Login is not Successfull");
        }

//4. User should be able to view the options in the user profile
        logp.clickLoggedInUserName();

        softassert.assertTrue(ul.optionsList() > 1);
        logger.info("there are options in the list");


//6. Click signout

        ul.logoutClick();
        logger.info("click logout");

//Assertion to confirm successful Logout

        softassert.assertTrue(lp.confirmLogout().equalsIgnoreCase("Find your freshii"));

        softassert.assertAll();
        logger.info("User is logged out, page title is : " + lp.confirmLogout());

        logger.info("Completed FWA_019");
    }
}

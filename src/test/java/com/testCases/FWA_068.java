package com.testCases;

//"1.Click on Continue with Google.
//2. Enter Email ID
//3. Enter Password
//4. Click Login
//5. Click User Pofile
//6.Click on My History.

//"1. User should be able to enter the email Id
//2. User should be able to enter the Password
//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page
//4. User should able to view the options in the user profile
//5. Upon clicking on My History the user should be able to navigate to the Order History page.

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_068 extends BaseClass {

    @Test
    public void FWA_068() throws IOException, InterruptedException {

        logger.info("Started FWA_068");
//		init pages and methods for further use
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        SoftAssert softassert = new SoftAssert();

//submit random zip as it's obligatory and shouldn't be hardcoded
//"1. User should be able to enter the email Id
//2. User should be able to enter the Password

        if(browser.equalsIgnoreCase("mobile-web")){
            lp.setContinueInBrowser();
        }

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
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google, URL is : " + driver.getCurrentUrl());


//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page


//4. User should able to view the options in the user profile

        ul.profileImgClick();
        softassert.assertTrue(ul.optionsList() > 1);
        logger.info("there are options in the list");

////5. Upon clicking on My History the user should be able to navigate to the Order History page.
        ul.clickOrderHistory();

        softassert.assertTrue(driver.getCurrentUrl().contains("order"), "User wasn't redirected to Order history");
        logger.info("URL is : " + driver.getCurrentUrl());


        softassert.assertAll();
        logger.info("User is redirected to Order History");

        logger.info("Completed FWA_068");
    }
}

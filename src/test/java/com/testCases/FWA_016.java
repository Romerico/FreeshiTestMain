package com.testCases;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_016 extends BaseClass {
//Purpose: Verify User -Profile options : My Account

//****************Test Steps****************
//1. Enter Email ID
//2. Enter Password
//3. Click Login
//4. Click User Pofile
//5. Click My Account from the menu options.
//6. Verify whether Firstname, Last name, EmaiId, phone number (optional) is autofilled.
//7. Save should be clickable.


//****************Expected Results****************

//1. User should be able to enter the email Id
//2. User should be able to enter the Password
//3. Upon clicking on login, user should be able to navigate to Welcome Freshii page
//4. User should be able to view the options in the user profile
//5. Upon clicking on My Account the user should be able to view the My account page
//6. The user details should be seen autofilled / Edit / save
//7. Upon clicking save, profile should be updated.email id is typed.

    @Test
    public void FWA_016() throws IOException, InterruptedException {

        logger.info("Started FWA_016");

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

//5. Upon clicking on My Account the user should be able to view the My account page
        ul.getInAccount();

        softassert.assertTrue(ul.titleText().contains("Account Details"),
                "User landed to the page other then profile, title is : " + ul.titleText());

        softassert.assertAll();
        logger.info("Upon clicking on My Account the user landed on My account page");

//6. Verify whether Firstname, Last name, EmaiId, phone number (optional) is autofilled.

        softassert.assertTrue(ul.checkName(), "First name autofilled wrong, name is : " + ul.username());
        softassert.assertTrue(ul.checkLastName(), "Last name autofilled wrong, lastname is : " + ul.userLastname());
        softassert.assertTrue(ul.checkEmail(), "Email autofilled wrong, email is : " + ul.userEmail());
        softassert.assertFalse(ul.checkPhone(), "Phone is not autofilled");

        softassert.assertAll();

//7. Upon clicking save, profile should be updated.
        String lastname = ul.userLastname();
        logger.info("Lastname was : " + lastname);

//editing lastname
        ul.editBtnClick();
        ul.editLastName();
        ul.saveBtnClick();

        logger.info("edited personal info");

        softassert.assertNotEquals(lastname, ul.userLastname(), "Lastname wasn't edited");
        logger.info("Edited lastname is : " + ul.userLastname());


        softassert.assertAll();

        logger.info("Completed FWA_016");
    }
}

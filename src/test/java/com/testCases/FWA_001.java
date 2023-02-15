package com.testCases;

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.SignupPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_001 extends BaseClass {

//Purpose: Verify end-end workflow using Freshii User : Sign up

//****************Test Steps****************
//1. Enter First name
//2. Enter Last Name
//3. Enter Email id.
//4. Enter phone(optional)
//5. Enter birthdate(MM/YY)
//6. Enter zip/postal code
//7. Enter password.
//8.Enter repeat password.
//9. Check/uncheck subscribe to our news.
//10. Click on sign up button.

//****************Expected Results****************

//1. User should be able to enter First name.
//2. User should be able to enter last name.
//3. User should see be able to enter valid email id.
//4. User can either add or skip entering phone number.
//5. User should be able to select the birthdate from the pop up window.
//6. User should be able to enter the zip/postal code.
//7. User should be able to enter the password as per acceptable conditions.
//8. (NA)User should be able to enter the same password and the strength of the password is displayed.( weak, acceptable , strong).
//9. User should be able to select or unselect the subscribe check box.
//10. (NA)Sign up button should be clickable and user should receive an email notification.

    @Test
    public void FWA_001() throws IOException, InterruptedException {

        logger.info("Started FWA_001");
//		init pages and methods for further testing
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        SignupPage sp = new SignupPage(driver);
        SoftAssert softassert = new SoftAssert();

//submit random zip as it's obligatory and shouldn't be hardcoded

        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");

//click profile image in order to get popup with options to proceed with signup

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

//Navigate to Sign Up Page

        lp.goToSignUpPage();
        logger.info("landed on SignUp page");

        sp.enterSignUpDetails();

        Boolean checkNextButtonEnabled = sp.isNextOnSignUpPgButtonEnable();
        if (checkNextButtonEnabled) {
            softassert.assertTrue(true);
            logger.info("The entered sign up details(First name, Last name, Email, Password) are accepted");
        } else {
            softassert.assertTrue(false);
            logger.info("Check the entered signup details and try again!");
            softassert.assertAll();
        }


        sp.clickNextOnSignUpPg();

        sp.clickCreateAccount();
        logger.info("Clicked Create Account");

// Upon clicking Create Account, user should be able to navigate to Welcome Freshii page
        Boolean verify = logp.validateHomePage();
        if (verify) {
            softassert.assertTrue(true);
            logger.info("SignUp/New User creation is Successful ");
        } else {
            softassert.assertTrue(false);
            logger.info("SignUp/New User creation is not Successful");
        }

        softassert.assertAll();
        logger.info("Completed FWA_001");
    }

}

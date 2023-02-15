package com.testCases;
//    1. Select the language preference to French in the Home page.
//2. Check if all the fields are translated in French language in the Home page.
//3. Once logged in, check if the Order now button is displayed in French.
//4. Check if all the field name under user profile is translated to French.
//5. Check if "Back to main" option is translated to French.
//6. Check if the Rewards & Offers are translated to French.

//"1. Language preference option should be changeable.
//2. The following fields should be translated to French
//         a. Email - Addresse Courriel
//         b. Password - Mot de passe
//         c. Forgot password - Mot de passe oublié
//         d. Remember me -  se souvienr de moi
//         e. Login - Me connecter
//         f. Sign up - s'inscrire
//         g. close - fermer
//3. Order now button should be translated to French( Commander Maintenant) and clickable.
//4. The options under user profile should be translated to French.
//        a. My Account - Mon compte
//        b. Payment Method - moyen de paiement
//        c. My History  - Mon histoire
//        d. Sign out - me déconnecter
//5. Back to Main option should be clickable and translated to French (écran d'accueil).
//6. Rewards & offers description should be translated in French"

import com.base.BaseClass;
import com.pageObjects.LandingPage;
import com.pageObjects.LoginPage;
import com.pageObjects.OrderPage;
import com.pageObjects.UserLanding;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class FWA_093disabled extends BaseClass {


    @Test(enabled = false)
    public void FWA_093_1() throws IOException, InterruptedException {

        logger.info("Started FWA_093_1");
//		init pages and methods for further use
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        SoftAssert softassert = new SoftAssert();


        lp.enterZipcode();
        lp.submitZipCode();
        logger.info("zipcode is accepted");



//1. Select the language preference to French in the Home page


        logger.info("choose French from a dropdown");
        lp.setLanguage();


        softassert.assertTrue(lp.actualLanguage().contains("FR"), "Language was not changed to French");
        logger.info("Switched to : " + lp.actualLanguage());

        lp.clickProfileButton();
        lp.clickSignupButton();
        logger.info("landed on login page");

        //2. Check if all the fields are translated in French language in the Home page.
//        2. The following fields should be translated to French
//         a. Email - Addresse Courriel
//         b. Password - Mot de passe
//         c. Forgot password - Mot de passe oublié
//         d. Remember me -  se souvienr de moi
//         e. Login - Me connecter
//         f. Sign up - s'inscrire
//         g. close - fermer

        softassert.assertTrue(logp.mailText().equalsIgnoreCase("Addresse Courriel"),
                "Email textbox is not translated as required");
        logger.info("Email textbox contains text : " + logp.mailText());

        softassert.assertTrue(logp.passText().equalsIgnoreCase("Mot de passe"),
                "Pass textbox is not translated as required");
        logger.info("Pass textbox contains text : " + logp.passText());

        softassert.assertTrue(logp.forgotPassText().equalsIgnoreCase("Mot de passe oublié"),
                "'Forgot password' is not translated as required");
        logger.info("'Forgot password' contains text : " + logp.forgotPassText());

        softassert.assertTrue(logp.logBtnText().equalsIgnoreCase("Me connecter"),
                "Login button is not translated as required");
        logger.info("Login button contains text : " + logp.logBtnText());

        softassert.assertTrue(logp.signUpText().equalsIgnoreCase("s'inscrire"),
                "Sign up link is not translated as required");
        logger.info("Sign up link contains text : " + logp.signUpText());




        softassert.assertAll();
        logger.info("Completed FWA_093_1");
    }


    @Test(priority = 7)
    public void FWA_093_2() throws IOException, InterruptedException {
//3. Once logged in, check if the Order now button is displayed in French.


        logger.info("Started FWA_093_2");
//init pages and methods for further use
        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        OrderPage o = new OrderPage(driver);
        SoftAssert softassert = new SoftAssert();


        logp.gButtonClick();

//clicking the account in a popup window

        logp.authorizeWithGoogle();
         logger.info("authorized the google account, URL is : " + driver.getCurrentUrl());
        softassert.assertTrue(driver.getCurrentUrl().contains("home"), "User is not on welcome page after login through Google, URL is : " + driver.getCurrentUrl());


//3. Once logged in, check if the Order now button is displayed in French.


        softassert.assertTrue(lp.actualLanguage().contains("FR"), "Language was not changed to French");
        logger.info("Switched to : " + lp.actualLanguage());

        softassert.assertTrue(o.getOrderButtonText().equalsIgnoreCase("Commander Maintenant"),
                "Order now button is not translated as required");
        logger.info("Order now button contains text : " + o.getOrderButtonText());



        softassert.assertAll();
        logger.info("Completed FWA_093_2");

    }



    @Test(priority = 8)
    public void FWA_093_3() throws IOException, InterruptedException {

        logger.info("Started FWA_093_3");

        LandingPage lp = new LandingPage(driver);
        LoginPage logp = new LoginPage(driver);
        UserLanding ul = new UserLanding(driver);
        OrderPage o = new OrderPage(driver);
        SoftAssert softassert = new SoftAssert();



        softassert.assertTrue(lp.actualLanguage().contains("FR"), "Language was not changed to French");
        logger.info("Switched to : " + lp.actualLanguage());

        ul.profileImgClick();
//5. Upon clicking on My Account the user should be able to view the My account page

//4. Check if all the field name under user profile is translated to French.
//5. Check if "Back to main" option is translated to French.
//6. Check if the Rewards & Offers are translated to French.

//        4. The options under user profile should be translated to French.
//        a. My Account - Mon compte
//        b. Payment Method - moyen de paiement
//        c. My History  - Mon histoire
//        d. Sign out - me déconnecter

        softassert.assertTrue(ul.accountText().equalsIgnoreCase("Mon compte"),
                "My Account is not translated as required");
        logger.info("My Account contains text : " + ul.accountText());

        softassert.assertTrue(ul.orderText().equalsIgnoreCase("Mon histoire"),
                "My History is not translated as required");
        logger.info("My History contains text : " + ul.orderText());

        softassert.assertTrue(ul.optionsText().equalsIgnoreCase("moyen de paiement"),
                "Payment Method is not translated as required");
        logger.info("Payment Method contains text : " + ul.optionsText());

        softassert.assertTrue(ul.logoutText().equalsIgnoreCase("me déconnecter"),
                "Sign out is not translated as required");
        logger.info("Sign out contains text : " + ul.logoutText());



        softassert.assertAll();

        logger.info("Completed FWA_093_3");
    }
}

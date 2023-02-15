package com.pageObjects;

import com.base.BaseClass;
import com.utilities.XLUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LandingPage extends BaseClass {
	public WebDriver ldriver;
	SoftAssert softassert = new SoftAssert();

	public LandingPage(WebDriver driver) throws IOException {
		ldriver = driver;
		PageFactory.initElements(driver, this);
	}
	Wait<WebDriver> wait = new FluentWait<>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofMillis(500))
			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

//Generating random number for selecting index for postal codes from the zipcode.xlxs file
	public static int randomNum(){
		int min = 1;
		int max = 102;
		int random = (int) (Math.random()*(max-min+1));
		return random;
	}

//Extracting a random postal code from the excel file
	String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\inputs\\zipcode.xlsx";

	String randomZip =
			XLUtils.getCellData
					(path, "zipcode", randomNum(), 0 );

//locating the zipcode field and submit button

	@FindBy(xpath = "//input[@name=\"postalCode\"]")
	@CacheLookup
	WebElement ziprow;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	@CacheLookup
	WebElement findStorebutton;

//Method to enter zipcode
	public void enterZipcode(){
		wait.until(ExpectedConditions.visibilityOf(ziprow));
		ziprow.sendKeys(randomZip);
	}

//Method to submit zipcode
	public void submitZipCode() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		findStorebutton.click();
		Thread.sleep(4000);
	}

//locating webElement present on logout page asking for postal code
	@FindBy(xpath = "//h3[@class=\"FreshiiTitle_freshiiTitle___Fohf\"]")
	@CacheLookup
	WebElement landingTitle;

//method to validate logout page using the earlier located WebElement named 'landing title'
	public String confirmLogout() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOf(landingTitle));

		return landingTitle.getText();
	}


//Looking for SignIn WebElement
	@FindBy(xpath = "//button[@qaattr=\"userProfile\"]")
	@CacheLookup
	WebElement profileButton;


	@FindBy(xpath = "//button[@qaattr=\"signIn\"]")
	@CacheLookup
	WebElement signupButton;

	public void clickSignupButton(){

		wait.until(ExpectedConditions.elementToBeClickable(signupButton));
		signupButton.click();
	}

	//Performing click operations for SignUp WebElement
	public void clickProfileButton() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(profileButton));
		profileButton.click();

	}

	@FindBy(xpath = "//button[@qaattr='signUp']")
	@CacheLookup
	WebElement signUpPageButton;

	public void goToSignUpPage(){
		wait.until(ExpectedConditions.elementToBeClickable(signUpPageButton));
		signUpPageButton.click();
	}



}
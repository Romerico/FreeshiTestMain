package com.pageObjects;

import com.base.BaseClass;
import com.utilities.XLUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class LoginPage extends BaseClass {

	public WebDriver ldriver;

	// Constructor

	public LoginPage(WebDriver rdriver) throws IOException {
		ldriver = rdriver;
		PageFactory.initElements(ldriver, this);
	}
	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			.withTimeout(Duration.ofSeconds(30))
			.pollingEvery(Duration.ofMillis(500))
			.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);



	String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\inputs\\logpass.xlsx";
	String email =
			XLUtils.getCellData
					(path, "logpass", 1, 0 );
	String pass = XLUtils.getCellData
			(path, "logpass", 1, 1 );




	@FindBy(xpath = "//input[@name=\"email\"]")
	@CacheLookup
	WebElement siteMailRow;

	@FindBy(xpath = "//input[@name=\"password\"]")
	@CacheLookup
	WebElement sitePassRow;

	@FindBy(xpath = "//a[@qaattr=\"forgotPass\"]")
	@CacheLookup
	WebElement forgotPass;

	@FindBy(xpath = "//button[@qaattr=\"commonButtonsSignIn\"]")
	@CacheLookup
	WebElement loginBtn;

	@FindBy(xpath = "//button[@qaattr=\"signUp\"]")
	@CacheLookup
	WebElement signUpLink;

	public String mailText(){
		wait.until(ExpectedConditions.visibilityOf(siteMailRow));
		return siteMailRow.getAttribute("placeholder");
	}

	public String passText(){
		wait.until(ExpectedConditions.visibilityOf(sitePassRow));

		return sitePassRow.getAttribute("placeholder");
	}

	public String forgotPassText(){
		wait.until(ExpectedConditions.visibilityOf(forgotPass));

		return forgotPass.getText();
	}

	public String logBtnText(){
		wait.until(ExpectedConditions.visibilityOf(loginBtn));

		return loginBtn.getText();
	}

	public String signUpText(){
		wait.until(ExpectedConditions.visibilityOf(signUpLink));

		return signUpLink.getText();
	}

	public void signUpLinkClick(){
		wait.until(ExpectedConditions.elementToBeClickable(signUpLink));

		signUpLink.click();
	}


	public void loginToFreshii() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOf(siteMailRow));
		siteMailRow.sendKeys(email);
		sitePassRow.sendKeys(pass);
		wait.until(ExpectedConditions.visibilityOf(loginBtn));
		wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
		js.executeScript("window.scrollBy(0,500)");
		js.executeScript("arguments[0].click();", loginBtn);

	}
	// Locating loggedIn user name after Login
	@FindBy(xpath = "//button[@qaattr='userProfile']/div[@class='Header_threeDots__QZzHQ Header_hideOnMobile___1v7f']")
	WebElement loginCustomerName;

	//Method to click the name of loggedIn user to access account options
	public void clickLoggedInUserName() {
		wait.until(ExpectedConditions.visibilityOf(loginCustomerName));
		loginCustomerName.click();

	}

	//Method to check the loggedIn User name is visible on the home screen
	public Boolean validateHomePage() {
		wait.until(ExpectedConditions.visibilityOf(loginCustomerName));
		return loginCustomerName.isDisplayed();
	}
}







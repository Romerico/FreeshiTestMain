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
import org.openqa.selenium.support.ui.WebDriverWait;
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
	Wait wait = new FluentWait<>(driver).
			withTimeout(Duration.ofSeconds(30)).
			pollingEvery(Duration.ofSeconds(1)).
			ignoring(StaleElementReferenceException.class, NoSuchElementException.class).ignoring(ElementClickInterceptedException.class);


	public static int randomNum(){
		int min = 1;
		int max = 102;
		int random = (int) (Math.random()*(max-min+1));
		return random;
	}
	String path = System.getProperty("user.dir")+ "\\src\\test\\java\\com\\inputs\\zipcode.xlsx";
	String randomZip =
		XLUtils.getCellData
		(path, "zipcode", randomNum(), 0 );
	@FindBy(xpath = "//button[text()='CONTINUE IN BROWSER']")
	@CacheLookup
	WebElement continueInBrowser;

	public void setContinueInBrowser(){
		wait.until(ExpectedConditions.elementToBeClickable(continueInBrowser));

		continueInBrowser.click();
	}

	//locate the zipcode row and submit button

	@FindBy(xpath = "//input[@name=\"postalCode\"]")
	@CacheLookup
	WebElement ziprow;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	@CacheLookup
	WebElement findStorebutton;

	public void enterZipcode(){
		wait.until(ExpectedConditions.visibilityOf(ziprow));


		ziprow.sendKeys(randomZip);
	}

	public void submitZipCode() {

		wait.until(ExpectedConditions.elementToBeClickable(findStorebutton));
		findStorebutton.click();

	}

	@FindBy(xpath = "//h3[@class=\"FreshiiTitle_freshiiTitle___Fohf\"]")
	@CacheLookup
	WebElement landingTitle;

	public String confirmLogout() throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOf(landingTitle));

		return landingTitle.getText();
	}



	//Looking for address WebElement
	@FindBy(xpath = "//div[@class=\"ant-row ant-row-no-wrap ant-row-space-between ant-row-middle Header_headerContainer__E8jjb\"]" +
			"//div[@class=\"Header_threeDots__QZzHQ\"]")
	@CacheLookup
	WebElement address;

	public String address() {
		return address.getText();
	}

	public boolean addressDefaulted() {

		wait.until(ExpectedConditions.visibilityOf(address));
		return address().contains("Rosedale");
	}



	//Looking for SignUp WebElement
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
	public void clickProfileButton() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		wait.until(ExpectedConditions.elementToBeClickable(profileButton));
		profileButton.click();

	}

	@FindBy(xpath = "//span[@class=\"Header_headerLanguage__2iAiW Header_hideOnMobile___1v7f\"]")
	@CacheLookup
	WebElement languageTrigger;

	@FindBy(xpath = "//div[@title=\"FR\"]")
	@CacheLookup
	WebElement french;


	@FindBy(xpath = "//div[@class=\"ant-row ant-row-no-wrap " +
			"ant-row-space-between ant-row-middle Header_headerContainer__E8jjb\"]//span[@class=\"ant-select-selection-item\"]")
	@CacheLookup
	WebElement indicator;


	public void setLanguage() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(languageTrigger));

		languageTrigger.click();

		wait.until(ExpectedConditions.visibilityOf(french));

		french.click();

		Thread.sleep(4000);
	}

	public String actualLanguage(){

		wait.until(ExpectedConditions.visibilityOf(indicator));
		return indicator.getText();
	}


	@FindBy(xpath = "//p[@class=\"FreshiiText_freshiiText__aBp2F FreshiiText_textOneLine__OYYyF FreshiiText_textXLarge__fXgKH\"]")
	@CacheLookup
	List<WebElement> pickUpButtons;

	public String pickupText(){
		wait.until(ExpectedConditions.visibilityOf(pickUpButtons.get(0)));

		return pickUpButtons.get(0).getText();
	}
	public String deliveryText(){
		wait.until(ExpectedConditions.visibilityOf(pickUpButtons.get(1)));

		return pickUpButtons.get(1).getText();
	}

}

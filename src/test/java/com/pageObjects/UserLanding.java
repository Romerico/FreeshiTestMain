package com.pageObjects;

import com.base.BaseClass;
import com.utilities.XLUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.Color;
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

public class UserLanding extends BaseClass {

    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public UserLanding(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }
    WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(15));
    Wait wait = new FluentWait<>(driver).
			withTimeout(Duration.ofSeconds(30)).
			pollingEvery(Duration.ofSeconds(1)).
			ignoring(StaleElementReferenceException.class, NoSuchElementException.class);


    @FindBy(xpath = "//img[@alt=\"Profile\"]")
    @CacheLookup
    WebElement profileImg;

    @FindBy(xpath = "//a[@qaattr=\"details\"]")
    @CacheLookup
    WebElement accountDetails;

    public String accountText(){
        wait.until(ExpectedConditions.visibilityOf(accountDetails));

        return accountDetails.getText();
    }

    public void profileImgClick() throws InterruptedException {


        wait.until(ExpectedConditions.elementToBeClickable(profileImg));
        profileImg.click();
    }
    public void getInAccount() throws InterruptedException {

        wait.until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));
        wait.until(ExpectedConditions.elementToBeClickable(accountDetails));
        accountDetails.click();
    }

    @FindBy(xpath = "//nav[@class=\"bm-item-list\"]//div")
    @CacheLookup
    List<WebElement> options;

    public int optionsList(){

       return options.size();

    }

    @FindBy(xpath = "//button[@qaattr=\"orderHistory\"]")
    @CacheLookup
    WebElement getOrderHistory;

    public void clickOrderHistory(){
        wait.until(ExpectedConditions.elementToBeClickable(getOrderHistory));

        getOrderHistory.click();

        wait.until(ExpectedConditions.urlContains("order"));
    }

    @FindBy(xpath = "//button[@qaattr=\"logout\"]")
    @CacheLookup
    WebElement logoutBtn;

    public void logoutClick(){
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));

        logoutBtn.click();
    }



    @FindBy(xpath = "//h1[@class=\"FreshiiTitle_freshiiTitle___Fohf\"]")
    @CacheLookup
    WebElement title;


    public String titleText(){
        wait.until(ExpectedConditions.visibilityOf(title));
        return title.getText();
    }

    String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\inputs\\logpass.xlsx";
    String name =
            XLUtils.getCellData
                    (path, "username", 0, 0 );
    String lastname = XLUtils.getCellData
            (path, "username", 0, 1 );
    String email =
            XLUtils.getCellData
                    (path, "logpass", 0, 0 );

    @FindBy(xpath = "//input[@name=\"firstName\"]")
    @CacheLookup
    WebElement nameRow;

    @FindBy(xpath = "//input[@name=\"lastName\"]")
    @CacheLookup
    WebElement lastNameRow;

    @FindBy(xpath = "//input[@name=\"email\"]")
    @CacheLookup
    WebElement emailRow;

    @FindBy(xpath = "//input[@class=\"form-control FormPhoneInput_phoneInput__Wg1_D\"]")
    @CacheLookup
    WebElement phoneRow;

    @FindBy(xpath = "//button[@qaattr=\"commonButtonsSave\"]")
    @CacheLookup
    WebElement saveBtn;

    @FindBy(xpath = "//span[@aria-label=\"edit\"]")
    @CacheLookup
    WebElement editBtn;

    public void editBtnClick(){
        editBtn.click();
    }

    public void saveBtnClick(){
        wait.until(ExpectedConditions.visibilityOf(saveBtn));
        saveBtn.click();
    }

    public boolean checkName(){
        wait.until(ExpectedConditions.visibilityOf(nameRow));
        return nameRow.getAttribute("value").equals(name);
    }
    public String username(){
        wait.until(ExpectedConditions.visibilityOf(nameRow));
        return nameRow.getAttribute("value");
    }

    public boolean checkLastName(){
        wait.until(ExpectedConditions.visibilityOf(lastNameRow));
        return lastNameRow.getAttribute("value").equals(lastname);
    }
    public String userLastname() {
        waitt.ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    lastNameRow.getAttribute("value");
                    return true;
                });
        return lastNameRow.getAttribute("value");
    }

    public void editLastName(){
        wait.until(ExpectedConditions.visibilityOf(lastNameRow));
        lastNameRow.sendKeys(Keys.CONTROL + "a");
        lastNameRow.sendKeys(Keys.DELETE);
        lastNameRow.sendKeys(randomestring());

    }

    public boolean checkEmail(){
        wait.until(ExpectedConditions.visibilityOf(emailRow));

        return emailRow.getAttribute("value").equals(email);
    }
    public String userEmail(){
        return emailRow.getAttribute("value");
    }

    public boolean checkPhone(){
        wait.until(ExpectedConditions.visibilityOf(phoneRow));

        return phoneRow.getAttribute("value").isEmpty();
    }

    @FindBy(xpath = "//button[@qaattr=\"orderHistory\"]")
    @CacheLookup
    WebElement orderHistory;

    @FindBy(xpath = "//button[@qaattr=\"payMethod\"]")
    @CacheLookup
    WebElement paymentOptions;

    @FindBy(xpath = "//button[@qaattr=\"logout\"]")
    @CacheLookup
    WebElement logoutOption;

    public String orderText(){
        wait.until(ExpectedConditions.visibilityOf(orderHistory));

        return orderHistory.getText();
    }

    public String optionsText(){
        wait.until(ExpectedConditions.visibilityOf(paymentOptions));

        return paymentOptions.getText();
    }

    public String logoutText(){
        wait.until(ExpectedConditions.visibilityOf(logoutOption));
       return logoutOption.getText();
    }

    @FindBy(xpath = "//ul[@class=\"Header_headerActions__zmwIC\"]//button[@qaattr=\"store\"]")
    @CacheLookup
    WebElement storesLink;

    public void clickStoresLink() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(storesLink));
        storesLink.click();

       wait.until(webDriver -> ((JavascriptExecutor) webDriver).
                executeScript("return document.readyState").equals("complete"));

    }
//************************************************************


    @FindBy(xpath = "//span[@aria-label=\"info-circle\"]")
    @CacheLookup
    List<WebElement> infocircles;

    public void infoClick(){
        wait.until(ExpectedConditions.elementToBeClickable(infocircles.get(0)));

        infocircles.get(0).click();
    }

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]")
    @CacheLookup
    WebElement locationCard;

    public boolean locationCardDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(locationCard));
        return locationCard.isDisplayed();
    }

    @FindBy(xpath = "//span[@class=\"FreshiiText_withIcon__vQIS5\"]" +
            "//child::p[@class=\"FreshiiText_freshiiText__aBp2F FreshiiText_textLarge__4TEnj\"]")
    @CacheLookup
    WebElement address;

    public boolean addressDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(address));
        return address.isDisplayed();
    }

    @FindBy(xpath = "//div[@class=\"ant-row ant-row-space-between\"]/p[1]")
    @CacheLookup
    List<WebElement> scheduleDays;

    public int scheduleDaysAmount(){
        return scheduleDays.size();
    }

    @FindBy(xpath = "//a[@qaattr=\"phoneNumber\"]")
    @CacheLookup
    WebElement shopPhoneInfo;

    public boolean shopPhone(){
        wait.until(ExpectedConditions.visibilityOf(shopPhoneInfo));
        return shopPhoneInfo.isDisplayed();
    }





    @FindBy(xpath = "//p[text() = 'PICKUP']//ancestor::button")
    @CacheLookup
    WebElement pickupBtn;

    public String pickupBtnActiveColor(){
        wait.until(ExpectedConditions.visibilityOf(pickupBtn));
        String s = pickupBtn.getCssValue("background-color");
        return Color.fromString(s).asHex();

    }

    @FindBy(xpath = "//p[@class=\"FreshiiText_freshiiText__aBp2F FreshiiText_textLarge__4TEnj\"]")
    @CacheLookup
    List<WebElement> locationsDistance;

    static String extractInt(String str)
    {

        str = str.replaceAll("[^\\d.]", "");

                str = str.trim();

        return str;
    }

    public boolean closestLocationDisplayed() throws InterruptedException {
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(locationsDistance.get(0)));
        String loc1 = locationsDistance.get(0).getText();
        String loc2 = locationsDistance.get(1).getText();

        String distance1 = extractInt(loc1);
        String distance2 = extractInt(loc2);

        return Float.parseFloat(distance1)<Float.parseFloat(distance2);
    }

    public String closestLocation(){
        wait.until(ExpectedConditions.visibilityOf(locationsDistance.get(0)));

        return locationsDistance.get(0).getText();

    }



















}

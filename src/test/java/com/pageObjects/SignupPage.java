package com.pageObjects;

import com.utilities.XLUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.base.BaseClass.driver;

public class SignupPage {

    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public SignupPage(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }




    String path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\inputs\\signUp.xlsx";
    String firstName =
            XLUtils.getCellData
                    (path, "signUp", 2, 0 );
    String lastName =
            XLUtils.getCellData
                    (path, "signUp", 2, 1 );
    String emailId =
            XLUtils.getCellData
                    (path, "signUp", 2, 2 );
    String phone =
            XLUtils.getCellData
                    (path, "signUp", 2, 3 );
    String pass = XLUtils.getCellData
            (path, "signUp", 2, 4 );

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

//locating elements on SignUp page

    @FindBy(xpath = "//input[@placeholder='First Name']")
    @CacheLookup
    WebElement fName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    @CacheLookup
    WebElement lName;

    @FindBy(xpath = "//input[@placeholder='Email']")
    @CacheLookup
    WebElement email;

    @FindBy(xpath = "//input[@placeholder='Password']")
    @CacheLookup
    WebElement password;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    @CacheLookup
    WebElement confirmPassword;

    @FindBy(xpath = "//button[@qaattr='commonButtonsNext']")
    @CacheLookup
    WebElement nextButton;



//locating webElement on subsequent signUp page

    @FindBy(xpath = "//button[@qaattr='enterManually']")
    @CacheLookup
    WebElement manualLocationEntryOption;

    @FindBy(xpath = "//label//span//input[@type='checkbox']")
    @CacheLookup
    WebElement subscribe;

    @FindBy(xpath = "//button[@qaattr='createAccount']")
    @CacheLookup
    WebElement createAccountButton;





    public void enterSignUpDetails(){
        wait.until(ExpectedConditions.elementToBeClickable(fName));
        fName.sendKeys(firstName);
        lName.sendKeys(lastName);
        email.sendKeys(emailId);
        password.sendKeys(pass);
        confirmPassword.sendKeys(pass);
    }

    public boolean isNextOnSignUpPgButtonEnable() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // verify list size
        if (nextButton.isEnabled()) {
            return true;

        } else {
            return false;
        }
    }
    public void clickNextOnSignUpPg() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }
    public void clickCreateAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));
        createAccountButton.click();
    }

/* detailed component validations

    @FindBy(xpath = "//*[contains(text(),'Wrong email format.')]")
    @CacheLookup
    List<WebElement>  wrongEmailText;



    @FindBy(xpath = "//p[@class='FieldInfo_fieldInfoId___NJDP FieldInfo_fieldError__3dxwv FreshiiText_freshiiText__aBp2F FreshiiText_textSmall__E6QPd']")
    @CacheLookup
    WebElement passwordText;

    public void enterSignUpFirstnLastName() {
        wait.until(ExpectedConditions.elementToBeClickable(fName));
        fName.sendKeys(firstName);
        lName.sendKeys(lastName);}

    public void enterSignUpEmail()  {
        wait.until(ExpectedConditions.elementToBeClickable(email));
        email.sendKeys(emailId);
    }

    public boolean isWrongEmailTextPresent() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // verify list size
        if (wrongEmailText.size() > 0) {
            return true;

        } else {
            return false;
        }
    }

    public void enterSignUpPassword(){
        password.sendKeys(pass);
    }

    public String passwordTextColor() {
        wait.until(ExpectedConditions.visibilityOf(passwordText));
        String s = passwordText.getCssValue("color");
        return Color.fromString(s).asHex();
    }
    @FindBy(xpath = "//p[@class='FreshiiText_freshiiText__aBp2F FreshiiText_textRedColor__kKNXN FreshiiText_textSmall__E6QPd']")
    @CacheLookup
    WebElement ConfirmPasswordText;

    public String confirmPasswordValidationText() {

        wait.until(ExpectedConditions.visibilityOf(ConfirmPasswordText));
        String s1 = ConfirmPasswordText.getText();
        return s1;
    }

    public void enterSignUpConfirmPassword(){
        confirmPassword.sendKeys(pass);
    }

*/


}


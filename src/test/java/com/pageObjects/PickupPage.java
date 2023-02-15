package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PickupPage extends BaseClass {
    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public PickupPage(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    Wait wait = new FluentWait<>(driver).
            withTimeout(Duration.ofSeconds(5)).
            pollingEvery(Duration.ofMillis(500)).
            ignoring(ElementClickInterceptedException.class, NoSuchElementException.class);

    @FindBy(xpath = "//p[text() = 'PICKUP']//ancestor::button")
    List<WebElement> pickUpButton;

    public String checkEnabledColorForPickUpButton() {
        wait.until(ExpectedConditions.visibilityOf(pickUpButton.get(1)));
        String s = pickUpButton.get(1).getCssValue("background-color");
        return Color.fromString(s).asHex();
    }

    @FindBy(xpath = "//button[@class=\"Cart_dayContainer__eTmBq\"]")
    WebElement tomorrowButton;
    @FindBy(xpath = "//button[(text()='SCHEDULE')]")
    WebElement scheduleButton;

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]//span[@class=\"ant-select-selection-item\"]")
    WebElement timeList;

    @FindBy(xpath = "//div[@class=\"rc-virtual-list-holder-inner\"]")
    WebElement timesDropdown;

    @FindBy(xpath = "//div[@class=\"ant-modal-footer\"]//button[contains(text(),'SCHEDULE')]")
    WebElement confirmscheduleButton;
    
    public void selectTomorrow(){

        wait.until(ExpectedConditions.elementToBeClickable(scheduleButton));
        scheduleButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(tomorrowButton));
        tomorrowButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(timeList));
        timeList.click();


        Actions actions = new Actions(driver);
        actions.moveToElement(timesDropdown).clickAndHold().release().perform();

        wait.until(ExpectedConditions.elementToBeClickable(confirmscheduleButton));
        confirmscheduleButton.click();

    }
    @FindBy(xpath = "//p[contains(text(),'Pickup')]/b")
    WebElement pickUpByTime;

    public String getPickUptime(){

        wait.until(ExpectedConditions.visibilityOf(pickUpByTime));
        return pickUpByTime.getText();

    }
}

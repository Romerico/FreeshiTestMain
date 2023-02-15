package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    @FindBy(xpath = "//p[(text()='PICKUP')]//ancestor::button")
    List<WebElement> pickupBtns;

    public String pickupBtnClr() {
        wait.until(ExpectedConditions.visibilityOf(pickupBtns.get(1)));
        String s = pickupBtns.get(1).getCssValue("background-color");
        return Color.fromString(s).asHex();
    }

    @FindBy(xpath = "//p[contains(text(),\"Pickup\")]/b")
    WebElement pickupOption;

    @FindBy(xpath = "//span[@class=\"anticon anticon-edit\"]")
    WebElement editStoreBtn;

    @FindBy(xpath = "//span[@aria-label=\"loading-3-quarters\"]")
    WebElement spinLoader;

    @FindBy(xpath = "//p[contains(text(),'OPEN')]")
    List<WebElement> shopsWorkTimes;

    @FindBy(xpath = "//button[(text()='SCHEDULE')]")
    WebElement scheduleBtn;

    @FindBy(xpath = "//button[(text()='SCHEDULE ORDER')]")
    WebElement scheduleOrderPopupBtn;

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]//span[@class=\"ant-select-selection-item\"]")
    WebElement timeList;

    @FindBy(xpath = "//div[@class=\"ant-select-item ant-select-item-option\"]/div[@class=\"ant-select-item-option-content\" and contains(text(),\"PM\")]")
    List<WebElement> timesOptionsContent;

    @FindBy(xpath = "//div[@title=\"ASAP\"]")
    WebElement ASAPoption;

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]")
    WebElement storeClosedPopup;

    @FindBy(xpath = "//button[@qaattr=\"cart\"]")
    WebElement cartBtn;

    @FindBy(xpath = "//div[@class=\"ant-modal-footer\"]//button[@class=\"ant-btn ant-btn-primary ant-btn-lg ant-btn-block\"]")
    WebElement refuseAdditionals;

    @FindBy(xpath = "//span[(text()='Location is closed and not support scheduled orders')]")
    WebElement storeClosedNotice;

    @FindBy(xpath = "//div[@class=\"rc-virtual-list-holder-inner\"]")
    WebElement timesDropdown;

    @FindBy(xpath = "//div[@class=\"rc-virtual-list-scrollbar-thumb\"]")
    WebElement scrollbar;

    @FindBy(xpath = "//h4[contains(text(),'Manning')]")
    WebElement testedShop;


    public Boolean shopWorkTimePickupRatio() throws ParseException {

        editStoreBtn.click();

        wait.until(ExpectedConditions.urlContains("stores"));

        wait.until(ExpectedConditions.visibilityOf(testedShop));

        wait.until(ExpectedConditions.visibilityOf(shopsWorkTimes.get(0)));
        String timeText = shopsWorkTimes.get(0).getText();

        String[] workingHours = timeText.split("-");
        String endTimeText = workingHours[1].trim().replaceAll("[^\\d:]", "");

        // Convert the start time and end time to Date objects
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date endTime = formatter.parse(endTimeText);

        // Get the current time

        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        cartBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(refuseAdditionals));
        refuseAdditionals.click();

        wait.until(ExpectedConditions.invisibilityOf(spinLoader));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        wait.until(ExpectedConditions.elementToBeClickable(scheduleBtn));
        scheduleBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(timeList));
        timeList.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
//            ((JavascriptExecutor)driver).executeScript("arguments[0].style.maxHeight='1000px';", timesDropdown);
//            ***
//            Long offsetTop = (Long) js.executeScript("return arguments[0].offsetTop;", timesDropdown);
//            Long targetOffsetTop = (Long) js.executeScript("return arguments[0].offsetTop;", timesOptionsContent.get(1));
//            Long offsetDifference = targetOffsetTop - offsetTop;
//            js.executeScript("arguments[0].scrollTop += arguments[1];", timesDropdown, offsetDifference);
//            ***
//            js.executeScript("arguments[0].scrollTop = arguments[1];", timesDropdown, 1000);
//            ***
//            js.executeScript("arguments[0].scrollBy(0, 1000);", timesDropdown);


        Actions actions = new Actions(driver);

        actions.moveToElement(scrollbar).clickAndHold().moveByOffset(0, 221).release().perform();


        wait.until(ExpectedConditions.visibilityOf(timesOptionsContent.get(timesOptionsContent.size() - 2)));
        String latepickupTime = timesOptionsContent.get(timesOptionsContent.size() - 2).getText().
                trim().replaceAll("[^\\d:]", "");
        Date latePickup = formatter.parse(latepickupTime);


        // Subtract 15 minutes from the first time
        Calendar fifteenMinutesLess = Calendar.getInstance();
        fifteenMinutesLess.setTime(endTime);
        fifteenMinutesLess.add(Calendar.MINUTE, -15);
        Date firstTimeMinusFifteenMinutes = fifteenMinutesLess.getTime();


        return latePickup.equals(firstTimeMinusFifteenMinutes);

    }


    public void editStoreOrCheckPickupTime() throws ParseException, InterruptedException {

        try {
            if (driver.getCurrentUrl().contains("cart") && !storeClosedPopup.isDisplayed()) {

                wait.until(ExpectedConditions.elementToBeClickable(editStoreBtn));
                editStoreBtn.click();


            }
            if (driver.getCurrentUrl().contains("cart") && storeClosedPopup.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(scheduleOrderPopupBtn));
                scheduleOrderPopupBtn.click();

                wait.until(ExpectedConditions.elementToBeClickable(timeList));
                timeList.click();

                wait.until(ExpectedConditions.elementToBeClickable(timesOptionsContent.get(0)));
                timesOptionsContent.get(0).getText();

                String firtsPickupTime = timesOptionsContent.get(1).getText().trim().replaceAll("[^\\d:]", "");

                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date firstPickupTimeDate = formatter.parse(firtsPickupTime);
                Date requiredPickupTime = formatter.parse("11:15");

                softassert.assertFalse(ASAPoption.isEnabled(), "ASAP option is not grayed");
                Assert.assertTrue(firstPickupTimeDate.equals(requiredPickupTime)
                        , "First pickup time is :" + firtsPickupTime);
                logger.info("Early pick up time is 15 minutes after store opening");

            }
            if (driver.getCurrentUrl().contains("home") && storeClosedNotice.isDisplayed()) {

                logger.info(storeClosedNotice.getText());

                Assert.assertTrue(storeClosedNotice.getText().contains("closed"));
            }
        } catch (NoSuchElementException e) {
            logger.info("Store is opened");
        }
    }

    @FindBy(xpath = "//p[contains(text(),'Ready')]/b")
    WebElement readyByTime;

    @FindBy(xpath = "//p[contains(text(),'Pickup')]/b")
    WebElement pickUpByTime;

    @FindBy(xpath = "//div[@class=\"ant-modal-footer\"]//button[contains(text(),'SCHEDULE')]")
    WebElement confirmScheduleBtn;

    public void changeTimeAndConfirm(){

        wait.until(ExpectedConditions.elementToBeClickable(scheduleBtn));
        scheduleBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(timeList));
        timeList.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(timesDropdown).clickAndHold().release().perform();

        wait.until(ExpectedConditions.elementToBeClickable(confirmScheduleBtn));
        confirmScheduleBtn.click();
    }

    public String getTimeString() {
        try {

            wait.until(ExpectedConditions.visibilityOf(readyByTime));

            if (driver.getCurrentUrl().contains("cart") && storeClosedPopup.isDisplayed()) {
                logger.info("Store is closed");

            } else {
                logger.info("Store is closed");
            }


        } catch (NoSuchElementException s) {
        }
        return readyByTime.getText();

    }

    public String getPickUptime(){

        wait.until(ExpectedConditions.visibilityOf(pickUpByTime));
        return pickUpByTime.getText();

    }

    @FindBy(xpath = "//button[@class=\"Cart_dayContainer__eTmBq\"]")
    WebElement tomorrowBtn;

    public void setTomorrow(){

        wait.until(ExpectedConditions.elementToBeClickable(scheduleBtn));
        scheduleBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(tomorrowBtn));
        tomorrowBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(timeList));
        timeList.click();


        Actions actions = new Actions(driver);
        actions.moveToElement(timesDropdown).clickAndHold().release().perform();

        wait.until(ExpectedConditions.elementToBeClickable(confirmScheduleBtn));
        confirmScheduleBtn.click();

    }




    public String pickupOptTxt(){
        wait.until(ExpectedConditions.visibilityOf(pickupOption));
        return pickupOption.getText();
    }









}

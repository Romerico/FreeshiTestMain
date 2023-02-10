package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

public class UserLanding extends BaseClass {

    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public UserLanding(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(15));

    Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class, StaleElementReferenceException.class).ignoring(IndexOutOfBoundsException.class);



    }




















package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;

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


}






package com.pageObjects;

import com.base.BaseClass;
import org.openqa.selenium.*;
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

public class OrderPage extends BaseClass {

    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public OrderPage(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }
    Wait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(30))
            .pollingEvery(Duration.ofMillis(500))
            .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

    @FindBy(xpath = "//button[@class=\"ant-btn ant-btn-primary ant-btn-lg\"]")
    @CacheLookup
    WebElement orderButton;

    public void clickOrderButton() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(orderButton));
        orderButton.click();
    }

    @FindBy(xpath = "//button[@class=\"ant-btn ant-btn-primary ant-btn-lg ant-btn-block\"]")
    @CacheLookup
    WebElement confirmStoreButton;

    public void confirmStore() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmStoreButton));
        confirmStoreButton.click();
    }

    //locating and selecting Lemongrass soup
    @FindBy(xpath = "//img[@alt=\"Spicy Lemongrass Soup (Small)\"]")
    @CacheLookup
    WebElement lemongrass;

    public void selectSoup() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(lemongrass));
        lemongrass.click();
    }

    @FindBy(xpath = "//div[(text()='Add Ingredients')]")
    WebElement addIngredients;
    public void selectAddIngredients(){
        wait.until(ExpectedConditions.elementToBeClickable(addIngredients));
        addIngredients.click();
    }

    @FindBy(xpath = "//p[@class=\"ProductInfo_productName__dxNCp FreshiiText_freshiiText__aBp2F FreshiiText_textLarge__4TEnj\"]")
    List<WebElement> optionalIngredients;

    @FindBy(xpath = "//span[@qaattr=\"plus\"]")
    List<WebElement> add;

    public void addChicken(){

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");

        wait.until(ExpectedConditions.visibilityOf(add.get(0)));
        int count = optionalIngredients.size();
        for(int i = 0; i<count; i ++){
            String productName =  optionalIngredients.get(i).getText();
            if(productName.equalsIgnoreCase("Chicken")){
                add.get(i).click();
            }
        }
    }
    @FindBy(xpath = "//button[@qaattr=\"addToCart\"]")
    WebElement addToCart;

    public void clickAddToCart(){

        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();

    }

    @FindBy(xpath = "//button[@qaattr=\"addUpsell\"]")
    List<WebElement> addUpsellItemButtonLinks;

    public void addUpsellItem(){
        wait.until(ExpectedConditions.elementToBeClickable(addUpsellItemButtonLinks.get(0)));
        addUpsellItemButtonLinks.get(0).click();
    }

    @FindBy(xpath = "//p[@class=\"FreshiiText_freshiiText__aBp2F FreshiiText_textMiddle__j2zhA\"]")
    WebElement additionalItemText;

    public String getAdditionalItemName(){
        wait.until(ExpectedConditions.visibilityOf(additionalItemText));
//        String additionalItem = additionalItemText.getText();
//        String[] additionalItemText = additionalItem.split(" ");
//        additionalItem= additionalItemText[2]; //Will extract the added item name i.e 'Chicken' from "1 x Chicken"
        return additionalItemText.getText(); //"1 x Chicken"
    }

    @FindBy(xpath = "//div[@class='ant-col ant-col-xs-24']//button[@qaattr='addTocart']")
    WebElement confirmAddToCartButton;

    public void clickConfirmAddToCart(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(confirmAddToCartButton));
        confirmAddToCartButton.click();
    }

    @FindBy(xpath = "//div[@class=\"ant-message-notice-content\"]")
    WebElement productAddedMessage;

    public String getProductSuccessfullyAddedText(){
        // wait.until(ExpectedConditions.visibilityOf(productAddedMessage));
        return productAddedMessage.getText();
    }

    @FindBy(xpath = "//button[@qaattr=\"checkout\"]")
    WebElement checkOutButton;
    public void clickCheckOutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutButton));
        checkOutButton.click();
    }

    @FindBy(xpath = "//div[@class=\"ant-modal-footer\"]//button[@class=\"ant-btn ant-btn-primary ant-btn-lg ant-btn-block\"]")
    WebElement refuseAdditionals;

    @FindBy(xpath = "//span[@aria-label=\"loading-3-quarters\"]")
    WebElement spinLoader;
    public void refuseAdditionals(){
        wait.until(ExpectedConditions.elementToBeClickable(refuseAdditionals));
        refuseAdditionals.click();
        wait.until(ExpectedConditions.invisibilityOf(spinLoader));
    }
}

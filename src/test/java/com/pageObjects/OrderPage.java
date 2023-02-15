package com.pageObjects;

import com.base.BaseClass;
import org.apache.poi.ss.formula.functions.WeekNum;
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

public class OrderPage extends BaseClass {

    public WebDriver ldriver;
    SoftAssert softassert = new SoftAssert();

    public OrderPage(WebDriver driver) throws IOException {
        ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    Wait wait = new FluentWait<>(driver).
            withTimeout(Duration.ofSeconds(30)).
            pollingEvery(Duration.ofSeconds(1)).
            ignoring(ElementClickInterceptedException.class , NoSuchElementException.class);


    @FindBy(xpath = "//span[@aria-label=\"minus\"]/following::span[1]")
    @CacheLookup
    WebElement quantity;


    @FindBy(xpath = "//button[@class=\"ant-btn ant-btn-primary ant-btn-lg\"]")
    @CacheLookup
    WebElement order;


    @FindBy(xpath = "//button[@class=\"ant-btn ant-btn-primary ant-btn-lg ant-btn-block\"]")
    @CacheLookup
    WebElement confirmShopBtn;

    public void confirmShop() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmShopBtn));
        confirmShopBtn.click();
    }

    public void clickOrder() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(order));
        order.click();
    }

    public String getOrderButtonText() {
        wait.until(ExpectedConditions.visibilityOf(order));
        return order.getText();
    }


    public String amount() {
        return quantity.getText();
    }

    @FindBy(xpath = "//div[@class=\"Store_dietaryPrefBtnText__rSyta\"]")
    WebElement dietaryRestrictions;


    @FindBy(xpath = "//div[@class=\"ant-row Store_allergenRow__po205 Store_allergenRowBorder__qC2bq\"]/label")
    List<WebElement> allergens;

    @FindBy(xpath = "//button[@qaattr=\"save\"]")
    WebElement submitAllergensBtn;

    @FindBy(xpath = "//span[@class=\"ant-tag ant-tag-red\"]")
    @CacheLookup
    List<WebElement> allergenTag;

    @FindBy(xpath = "//span[@class=\"ant-scroll-number-only\"]")
    @CacheLookup
    WebElement currentAllergenQuantity;

    @FindBy(xpath = "//div[@role=\"dialog\"]//h3")
    @CacheLookup
    WebElement allergenDialogue;


    public void allergensClick() {
        wait.until(ExpectedConditions.elementToBeClickable(dietaryRestrictions));
        dietaryRestrictions.click();
    }

    public void checkAllergen() {
        wait.until(ExpectedConditions.elementToBeClickable(allergens.get(0)));
        allergens.get(0).click();
        allergens.get(1).click();
    }

    public void setSubmitAllergens() {
        submitAllergensBtn.click();
    }

    public boolean redTagVisible() {
        wait.until(ExpectedConditions.visibilityOf(allergenTag.get(0)));
        return allergenTag.get(0).isDisplayed();
    }


    public String allergenQuantity() throws InterruptedException {
        Thread.sleep(4000);
        wait.until(ExpectedConditions.visibilityOf(currentAllergenQuantity));
        return currentAllergenQuantity.getText();
    }


    public boolean allergenHeader() {
        wait.until(ExpectedConditions.visibilityOf(allergenDialogue));
        return allergenDialogue.isDisplayed();
    }

    @FindBy(xpath = "//span[@aria-label=\"delete\"]")
    List<WebElement> remove;

    @FindBy(xpath = "//div[(text()='Add Ingredients')]")
    WebElement addIngresients;

    public void removeItem() {
        wait.until(ExpectedConditions.elementToBeClickable(remove.get(0)));
        remove.get(0).click();
    }

    public void setAddIngresients(){
    wait.until(ExpectedConditions.elementToBeClickable(addIngresients));
    addIngresients.click();
        }

    @FindBy(xpath = "//p[@class=\"ProductInfo_productName__dxNCp FreshiiText_freshiiText__aBp2F FreshiiText_textLarge__4TEnj\"]")
    List<WebElement> optionalIngredients;

    @FindBy(xpath = "//span[@qaattr=\"plus\"]")
    List<WebElement> pluses;

    public void addChicken(){

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 200)");


        wait.until(ExpectedConditions.visibilityOf(pluses.get(0)));
        int count = optionalIngredients.size();
        for(int i = 0; i<count; i ++){
            String productName =  optionalIngredients.get(i).getText();
            if(productName.equalsIgnoreCase("Chicken")){
                pluses.get(i).click();
            }
        }
    }

    @FindBy(xpath = "//div[@class=\"ant-row FreshiiModal_modalContent__OnjkR\"]//button")
    List<WebElement> popupProteinPlus;

    @FindBy(xpath = "//div[@class=\"ant-modal-body\"]//div[@class=\"ant-row\"]")
    List<WebElement> popupProteins;

    public void addPopupChicken() {
        wait.until(ExpectedConditions.visibilityOf(popupProteinPlus.get(0)));
        int count = popupProteins.size();
        for (int i = 0; i < count; i++) {
            String productName = popupProteins.get(i).getText();
            if (productName.equalsIgnoreCase("Chicken")) {
                popupProteinPlus.get(i).click();
            }
        }
    }

    @FindBy(xpath = "//button[@qaattr=\"addToCart\"]")
    WebElement addToCart;

    public void setAddToCart(){

        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();

    }



    @FindBy(xpath = "//button[@qaattr=\"addUpsell\"]")
    List<WebElement> addUpsellItemBtn;

    public void addUpsellItem(){
        wait.until(ExpectedConditions.elementToBeClickable(addUpsellItemBtn.get(0)));
        addUpsellItemBtn.get(0).click();
    }

    @FindBy(xpath = "//div[@class=\"ant-modal-content\"]//button[@qaattr=\"addTocart\"]")
    WebElement confirmAddToCartBtn;

    public void confirmAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(confirmAddToCartBtn));
        confirmAddToCartBtn.click();
    }

    @FindBy(xpath = "//p[@class=\"FreshiiText_freshiiText__aBp2F FreshiiText_textMiddle__j2zhA\"]")
    WebElement addtext;

    public String additionalList(){
        wait.until(ExpectedConditions.visibilityOf(addtext));
        return addtext.getText();
//        "1 x Chicken"
    }


    @FindBy(xpath = "//h5[(text()='Teriyaki Twist Bowl')]")
    WebElement TeriyakiTwistbowl;

    @FindBy(xpath = "//img[@alt=\"Custom Bowl\"]")
    WebElement customBowl;


    @FindBy(xpath = "//a[(text()='Bowls')]")
    WebElement bowlsSection;

    @FindBy(xpath = "//a[(text()='Smoothies')]")
    WebElement smoothiesSection;

    @FindBy(xpath = "//img[@alt=\"12oz Tropical Mango\"]")
    WebElement smoothie;

    @FindBy(xpath = "//button[@class=\"ant-btn ant-btn-link\"]")
    WebElement gotIt;

    @FindBy(xpath = "//img[@alt=\"Spicy Lemongrass Soup (Small)\"]")
    @CacheLookup
    WebElement lemongrass;


    public void chooseSoup() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(lemongrass));
        lemongrass.click();
    }

    public void clickGotIt(){
        wait.until(ExpectedConditions.elementToBeClickable(gotIt));
        gotIt.click();
    }

    public void chooseBowl(){
        wait.until(ExpectedConditions.elementToBeClickable(bowlsSection));
        bowlsSection.click();
        bowlsSection.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
//        if(!customBowl.isDisplayed()) {
//            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");
//        }
        customBowl.click();

        wait.until(ExpectedConditions.elementToBeClickable(gotIt));

    }
    public void chooseSmoothie() {
        wait.until(ExpectedConditions.elementToBeClickable(smoothiesSection));
        smoothiesSection.click();
        smoothiesSection.click();

        wait.until(ExpectedConditions.visibilityOf(smoothie));
        smoothie.click();


    }

    @FindBy(xpath = "//div[@class=\"ant-tabs-nav-list\"]//div[@id=\"rc-tabs-1-tab-Extra Toppings\"]")
    WebElement ExtratoppingsTab;

    @FindBy(xpath = "//div[@class=\"ant-tabs-nav-list\"]//div[@id=\"rc-tabs-1-tab-Included Toppings\"]")
    WebElement Includedtoppings;

    @FindBy(xpath = "//div[@class=\"ant-tabs-nav-list\"]//div[@aria-controls=\"rc-tabs-1-panel-Included Dressing\"]")
    WebElement includedDressing;

    @FindBy(xpath = "//div[@class=\"ant-tabs-nav-list\"]//div[@id=\"rc-tabs-1-tab-Base\"]")
    WebElement baseTab;

    public void setBase(){
        wait.until(ExpectedConditions.elementToBeClickable(baseTab));
        baseTab.click();
    }


    public void setIncludedDressing(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", includedDressing);

        wait.until(ExpectedConditions.visibilityOf(includedDressing));
        includedDressing.click();
    }

    public void setIncludedtoppings(){
        wait.until(ExpectedConditions.visibilityOf(Includedtoppings));
        Includedtoppings.click();
    }

    public void setToppings(){
        wait.until(ExpectedConditions.visibilityOf(ExtratoppingsTab));
        ExtratoppingsTab.click();
    }

    public void addItem(){
        if(!pluses.get(0).isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");
        }
        wait.until(ExpectedConditions.elementToBeClickable(pluses.get(0)));
        pluses.get(0).click();
    }





    @FindBy(xpath = "//div[@class=\"ant-message-notice-content\"]")
    WebElement notice;

    public String noticeText(){
        wait.until(ExpectedConditions.visibilityOf(notice));
        return notice.getText();
    }


    @FindBy(xpath = "//button[@qaattr=\"cart\"]")
    WebElement cartBtn;

    @FindBy(xpath = "//div[@class=\"ant-modal-footer\"]//button[@class=\"ant-btn ant-btn-primary ant-btn-lg ant-btn-block\"]")
    WebElement refuseAdditionals;

    @FindBy(xpath = "//button[@class=\"Cart_selectTip__lkmyI\"]")
    List<WebElement> unselecterdTip;

    @FindBy(xpath = "//span[@class=\"anticon anticon-right ant-collapse-arrow\"]")
    WebElement collapseArrow;

    @FindBy(xpath = "//span[@class=\"anticon anticon-right ant-collapse-arrow\"]")
    List<WebElement> collapseArrows;

    @FindBy(xpath = "//button[@qaattr=\"remove\"]")
    WebElement removeBtn;

    @FindBy(xpath = "//span[@aria-label=\"loading-3-quarters\"]")
    WebElement spinLoader;

    public void goToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        cartBtn.click();
    }

    public void refuseAdditionals(){
        wait.until(ExpectedConditions.elementToBeClickable(refuseAdditionals));
        refuseAdditionals.click();

        wait.until(ExpectedConditions.invisibilityOf(spinLoader));

    }

    public void goToCartAndDeleteStuffForFutureTests(){
        wait.until(ExpectedConditions.elementToBeClickable(cartBtn));
        cartBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(refuseAdditionals));
        refuseAdditionals.click();

        wait.until(ExpectedConditions.invisibilityOf(spinLoader));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        wait.until(ExpectedConditions.elementToBeClickable(collapseArrow));
        collapseArrow.click();

        wait.until(ExpectedConditions.elementToBeClickable(removeBtn));
        removeBtn.click();


    }


}

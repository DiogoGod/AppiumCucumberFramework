package com.qa.pages;

import com.qa.utils.GlobalParams;
import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductsPage extends MenuPage {
    TestUtils utils = new TestUtils();

    //	@AndroidFindBy (xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup/android.widget.TextView")
    @AndroidFindBy (xpath = "//android.widget.TextView[@text='PRODUCTS']")
    @iOSXCUITFindBy (xpath ="//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
    private WebElement titleTxt;

    @iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-PRODUCTS\"]/XCUIElementTypeScrollView")
    private WebElement iOSSCrollView;
    @AndroidFindBy (xpath ="//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView")
    private WebElement cartButton;
    @AndroidFindBy (xpath="//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"]")
    private WebElement addToCartButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]")
    private WebElement checkoutButton;
    @AndroidFindBy(accessibility ="test-First Name" )
    private WebElement firstNametxt;
    @AndroidFindBy(accessibility = "test-Last Name")
    private WebElement lastNametxt;
    @AndroidFindBy(accessibility = "test-Zip/Postal Code")
    private WebElement zipCodetxt;
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-CONTINUE\"]")
    private WebElement continueButton;
    @AndroidFindBy(accessibility ="test-FINISH" )
    private WebElement Finish;
    public String getTitle() {
        return getText(titleTxt, "product page title is - ");
    }

    public String getProductTitle(String title) throws Exception {
        switch(new GlobalParams().getPlatformName()){
            case "Android":
                return getText(andScrollToElementUsingUiScrollable("text", title), "product title is: " + title);
            case "iOS":
                return getText(iOSScrollToElementUsingMobileScrollParent(iOSSCrollView, "label == '"+ title +"'"),
                        "product title is: " + title);
            default:
                throw new Exception("Invalid platform name");
        }
    }

    public By defProductPrice(String title) throws Exception {
        switch(new GlobalParams().getPlatformName()){
            case "Android":
                return By.xpath("//*[@text=\"" + title + "\"]/following-sibling::*[@content-desc=\"test-Price\"]");
            case "iOS":
                return By.xpath("//XCUIElementTypeOther[@name=\"" + title + "\"]/following-sibling::*[1]/child::XCUIElementTypeStaticText[@name=\"test-Price\"]");
            default:
                throw new Exception("Invalid platform name");
        }
    }

/*	public String getProductPrice(String title, String price) throws Exception {
		return getText(scrollToElement(defProductPrice(title), "up"), "product price is: " + price);
	}*/

    public void pressProductTitle(String title) throws Exception {
        switch(new GlobalParams().getPlatformName()){
            case "Android":
                click(andScrollToElementUsingUiScrollable("text", title), "press " + title + " link");
                new ProductDetailsPage();
                return;
            case "iOS":
                click(iOSScrollToElementUsingMobileScrollParent(iOSSCrollView, "label == '"+ title +"'"), "press " + title + " link");
                new ProductDetailsPage();
                return;
            default:
                throw new Exception("Invalid platform name");
        }
    }
    public void pressAddToCart(){

    click(addToCartButton,"press Add to cart button");
    }
    public void pressCart(){
        click(cartButton,"press Cart Button");
    }
    public void pressCheckOut(){
        click(checkoutButton,"press checkoutButton");

    }
    public ProductsPage enterFirstName(String firstName){
    clear(firstNametxt);
    sendKeys(firstNametxt, firstName,"checkOut with firstName:"+ firstName);
    return this;
    }
    public ProductsPage enterLastName(String lastName){
        clear(lastNametxt);
        sendKeys(lastNametxt,lastName,"checkOut with lastName:"+ lastName);
        return this;
    }
public ProductsPage enterZipCode(String zipCode){
        clear(zipCodetxt);
        sendKeys(zipCodetxt,zipCode,"checkout with zipcode:"+ zipCode);
        return this;
}
public void CheckoutContinueButton(){
        click(continueButton,"press continue Button");
}
public void Finish(){
        andScrollToElementUsingUiScrollable("description","test-FINISH");
        click(Finish,"press Finish button");
}
}

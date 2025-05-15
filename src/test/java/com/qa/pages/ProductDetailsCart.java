package com.qa.pages;

import com.qa.utils.TestUtils;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductDetailsCart extends MenuPage{
    TestUtils utils= new TestUtils();
    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]\n"+
    "")
    private WebElement title;
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]\n"+
            "")
    private WebElement description;
    @AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Price\"]/android.widget.TextView[1]\n"+
            "")
    private WebElement price;

    public String getCartTitle(){return getText(title,"title is:");}

    public String getCartDescription(){return getText(description,"description is:");}

    public String getCartPrice(){return getText(price,"price is:");}
}

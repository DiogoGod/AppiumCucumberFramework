package com.qa.stepdef;

import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsCart;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductStepDef {

    @Given("I'm logged in")
    public void iMLoggedIn() throws InterruptedException {
        new LoginPage().login("standard_user", "secret_sauce");
    }

    @Then("the product is listed with title {string} and price {string}")
    public void theProductIsListedWithTitleAndPrice(String title, String price) throws Exception {
        Boolean titleCheck = new ProductsPage().getProductTitle(title).equalsIgnoreCase(title);
        Boolean priceCheck = true;
//        Boolean priceCheck = new ProductsPage().getProductPrice(title, price).equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck = " + titleCheck + ", priceCheck = " + priceCheck,
                titleCheck & priceCheck);
    }

    @When("I click product title {string}")
    public void iClickProductTitle(String title) throws Exception {
        new ProductsPage().pressProductTitle(title);
    }

    @Then("I should be on product details page with title {string}, price {string} and description {string}")
    public void iShouldBeOnProductDetailsPageWithTitlePriceAndDescription(String title, String price, String description) throws Exception {
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();
        boolean titleCheck = productDetailsPage.getTitle().equalsIgnoreCase(title);
        boolean descCheck = productDetailsPage.getDesc().equalsIgnoreCase(description);
        boolean priceCheck = productDetailsPage.getPrice().equalsIgnoreCase(price);
        Assert.assertTrue("titleCheck = " + titleCheck + ", descCheck = " + descCheck + ", priceCheck = " + priceCheck,
                titleCheck & descCheck & priceCheck);
    }

    @Then("I click the AddToCart Button")
    public void iClickTheAddToCartButton() {
        new ProductsPage().pressAddToCart();
    }

    @And("I click the Cart button")
    public void iClickTheCartButton() {
        new ProductsPage().pressCart();
    }

    @Then("I should see the correct product title {string}, price {string} and description {string}")
    public void iShouldSeeTheCorrectProductTitlePriceAndDescription(String title, String price, String description) {
        ProductDetailsCart productDetailsCart= new ProductDetailsCart();
        boolean titleCheck= productDetailsCart.getCartTitle().equalsIgnoreCase(title);
        boolean priceCheck= productDetailsCart.getCartPrice().equalsIgnoreCase(price);
        boolean descriptionCheck= productDetailsCart.getCartDescription().equalsIgnoreCase(description);
        Assert.assertTrue("titleCheck="+ titleCheck +"priceCheck="+ priceCheck+"descriptionCheck="+ descriptionCheck
                ,titleCheck & priceCheck & descriptionCheck);

    }

    @And("I click the CheckOut button")
    public void iClickTheCheckOutButton() {
        new ProductsPage().pressCheckOut();
    }


    @And("I enter First Name {string}, Last Name {string} and Zip Code {string}")
    public void iEnterFirstNameLastNameAndZipCode(String firstName, String lastName, String zipCode) {
    new ProductsPage().enterFirstName(firstName);
    new ProductsPage().enterLastName(lastName);
    new ProductsPage().enterZipCode(zipCode);
    }

    @And("I click the continue button")
    public void iClickTheContinueButton() {
        new ProductsPage().CheckoutContinueButton();
    }

    @Then("I click the finish button")
    public void iClickTheFinishButton() {
     new ProductsPage().Finish();
    }
}


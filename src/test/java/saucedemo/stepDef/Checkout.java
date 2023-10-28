package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Checkout {
    String baseUrl = "https://www.saucedemo.com/";
    WebDriver driver;

    @Given("User has logged in to Swag Labs to checkout products")
    public void LoginToSwagLabsToCheckout() {
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //input form
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
    }

    @And("User has added products to the cart")
    public void AddedProductsToTheCart() {
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bike-light']")).click();
    }

    @Given("User has some products on the cart")
    public void userHasSomeProductsOnTheCart() {
        String totalProducts = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        Assert.assertTrue(Integer.parseInt(totalProducts) > 0);
    }

    @When("User clicks on Cart icon")
    public void userClicksOnCartIcon() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @And("clicks on checkout button")
    public void clicksOnCheckoutButton() {
        driver.findElement(By.cssSelector("[data-test='checkout']")).click();
    }

    @Then("User will see receiver information form")
    public void userWillSeeReceiverInformationForm() {
        WebElement formInformation = driver.findElement(By.xpath("//div[@class='checkout_info']"));
        Assert.assertTrue(formInformation.isDisplayed());
    }

    @When("User input First Name, Last Name, and Postal Code")
    public void userInputFirstNameLastNameAndPostalCode() {
        driver.findElement(By.cssSelector("[data-test='firstName']")).sendKeys("Fadhilatur");
        driver.findElement(By.cssSelector("[data-test='lastName']")).sendKeys("Rahmah");
        driver.findElement(By.cssSelector("[data-test='postalCode']")).sendKeys("67153");
    }

    @And("clicks on Continue button")
    public void clicksOnContinueButton() {
        driver.findElement(By.cssSelector("[data-test='continue']")).click();
    }

    @Then("User will see Payment Information, Shipping Information, and Price Total")
    public void userWillSeeCheckoutOverview() {
        WebElement paymentInfoLabel = driver.findElement(By.xpath("//div[contains(text(),'Payment Information')]"));
        WebElement shippingInfo = driver.findElement(By.xpath("//div[contains(text(),'Shipping Information')]"));
        WebElement itemTotal = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        WebElement tax = driver.findElement(By.xpath("//div[@class='summary_tax_label']"));
        WebElement totalPrice = driver.findElement(By.xpath("//div[@class='summary_info_label summary_total_label']"));

        Assert.assertTrue(paymentInfoLabel.isDisplayed());
        Assert.assertTrue(shippingInfo.isDisplayed());
        Assert.assertTrue(itemTotal.isDisplayed());
        Assert.assertTrue(tax.isDisplayed());
        Assert.assertTrue(totalPrice.isDisplayed());

        driver.close();
    }
}

package saucedemo.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class AddToCart {
    String baseUrl = "https://www.saucedemo.com/";
    int totalItem;
    WebDriver driver;

    @Given("User has logged in to Swag Labs to add to cart")
    public void LoggedInToAddProductToCart(){
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //input form
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
    }

    @Given("User is on Products page to add to cart")
    public void getProductsPageToAddToCart() {
        String pageTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals("Products", pageTitle);
    }

    @When("User clicks on Add to cart button")
    public void userClicksOnAddToCartButton() {
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']")).click();
    }

    @And("click on Cart icon")
    public void clickOnCartIcon() {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    @Then("The product will be displayed on cart page")
    public void theProductWillBeDisplayedOnCartPage() {
        String productName = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        String quantity = driver.findElement(By.xpath("//div[@class='cart_quantity']")).getText();

        Assert.assertEquals("Sauce Labs Backpack", productName);
        Assert.assertEquals("1", quantity);
    }

    @And("cart badge will increase")
    public void cartBadgeWillIncrease() {
        String badge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        Assert.assertEquals("1", badge);

        driver.close();
    }

    @And("User has added two products to the cart")
    public void userHasAddedProductsToTheCart() {
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']")).click();
        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bike-light']")).click();

        //get total items on the cart
        String badge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        totalItem = Integer.parseInt(badge);
    }

    @When("User clicks on Remove button")
    public void userClicksOnRemoveButton() {
        driver.findElement(By.xpath("//button[@data-test='remove-sauce-labs-bike-light']")).click();
    }

    @Then("The product is no longer display in the cart page")
    public void theProductIsNoLongerDisplayInTheCartPage() {
        boolean isPresent = driver.findElements(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]")).size() < 1;
        Assert.assertTrue(isPresent);
    }

    @And("cart badge will decrease")
    public void cartBadgeWillDecrease() {
        String badge = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText();
        Assert.assertEquals((totalItem-1),Integer.parseInt(badge));

        driver.close();
    }
}

package saucedemo.stepDef;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


public class ProductDetail{
    String baseUrl = "https://www.saucedemo.com/";
    String name = "Sauce Labs Bolt T-Shirt";
    String price = "$15.99";
    String description = "Get your testing superhero on with the Sauce Labs bolt T-shirt. From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    WebDriver driver;

    @Given("User has logged in to Swag Labs to see detail")
    public void LoggedInToSwagLabsToSeeDetail() {
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //input form
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
    }

    @Given("User is on Products page to see detail")
    public void getProductsPageToSeeDetail() {
        String pageTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals("Products", pageTitle);
    }

    @When("User clicks on product item")
    public void clickProductItem(){
        driver.findElement(By.xpath("//div[contains(text(),'Sauce Labs Bolt T-Shirt')]")).click();
    }

    @Then("User will be redirected to product detail page")
    public void getProductDetailPage() {
        String productName = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
        String productDesc = driver.findElement(By.xpath("//div[@class='inventory_details_desc large_size']")).getText();
        String productPrice = driver.findElement(By.xpath("//div[@class='inventory_details_price']")).getText();
        WebElement productImage = driver.findElement(By.xpath("//img[@alt='Sauce Labs Bolt T-Shirt']"));

        Assert.assertEquals(name, productName);
        Assert.assertEquals(description, productDesc);
        Assert.assertEquals(price, productPrice);
        Assert.assertTrue(productImage.isDisplayed());

        driver.close();
    }

    @And("User changes product Id on url with invalid Id")
    public void getInvalidProductDetail() {
        driver.get(baseUrl+"inventory-item.html?id=1000");
    }

    @Then("Item not Found message appears")
    public void itemNotFoundMessageAppears() {
        String productName = driver.findElement(By.xpath("//div[@class='inventory_details_name large_size']")).getText();
        WebElement productImage = driver.findElement(By.xpath("//img[@alt='ITEM NOT FOUND']"));

        Assert.assertEquals("ITEM NOT FOUND", productName);
        Assert.assertTrue(productImage.isDisplayed());

        driver.close();
    }
}

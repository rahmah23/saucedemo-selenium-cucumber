package saucedemo.stepDef;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class SortingProducts {
    String baseUrl = "https://www.saucedemo.com/";
    WebDriver driver;

    @Given("User has logged in to Swag Labs")
    public void LogintoSwagLabs(){
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //verify login form is displayed
        WebElement loginForm = driver.findElement(By.xpath("//div[@class='login-box']"));
        Assert.assertEquals(true, loginForm.isDisplayed());
        //input form
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();

    }

    @Given("User is on Products page")
    public void userIsOnProductsPage() {
        String pageTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
        Assert.assertEquals("Products", pageTitle);
    }

    @And("see sorting field")
    public void seeSortingField() {
        WebElement sortingField = driver.findElement(By.cssSelector("[data-test='product_sort_container']"));
        Assert.assertTrue(sortingField.isDisplayed());
    }

    @When("User clicks on sorting field")
    public void userClicksOnSortingField() {
        driver.findElement(By.cssSelector("[data-test='product_sort_container']")).click();
    }

    @And("choose (.*) as sorting option$")
    public void chooseOptionAsSortingOption(String option) {
        driver.findElement(By.xpath("//option[contains(text(),'"+option+"')]")).click();
    }

    @Then("products will be sorted and (.*) will display first$")
    public void productsWillBeSortedByOption(String product) {
        String firstProduct = driver.findElement(By.xpath("//div[@class='inventory_item_name ']")).getText();
        Assert.assertEquals(product, firstProduct);
        driver.close();
    }
}

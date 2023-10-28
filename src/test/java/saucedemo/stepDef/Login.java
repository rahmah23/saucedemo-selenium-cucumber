package saucedemo.stepDef;

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

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Login {
    String baseUrl = "https://www.saucedemo.com/";
    WebDriver driver;

    @Given("Swag Labs Login page")
    public void getSwagLabLoginPage(){
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
        //verify login form is displayed
        WebElement loginForm = driver.findElement(By.xpath("//div[@class='login-box']"));
        Assert.assertEquals(true, loginForm.isDisplayed());
    }

    @When("input (.*) as username in login form$")
    public void inputUsernameLogin(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }


    @And("input (.*) as password in login form$")
    public void inputPasswordLogin(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("click on Login button")
    public void clickOnLoginButton() {
        driver.findElement(By.cssSelector("[data-test='login-button']")).click();
    }

    @Then("verify login result (.*) and (.*)$")
    public void verifyLoginResultStatusAndMessage(String status, String message) {
        if(status.equals("success")) {
            WebElement dashboardLogo = driver.findElement(By.xpath("//div[@class='app_logo']"));
            String pageTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
            Assert.assertTrue(dashboardLogo.isDisplayed());
            Assert.assertEquals("Products", pageTitle);
        } else if(status.equals("failed")) {
            String errorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
            Assert.assertEquals(message, errorMessage);
        }
        driver.close();
    }
}

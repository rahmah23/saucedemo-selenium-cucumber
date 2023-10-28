package kasirAja.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    String baseUrl = "https://kasirdemo.belajarqa.com/";
    WebDriver driver;

    @Given("Kasir Aja Login page")
    public void getLoginPage(){
        WebDriverManager.safaridriver().setup();
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @When("input email")
    public void inputEmail() {
        driver.findElement(By.id("email")).sendKeys("rarashop@gmail.com");
    }

    @And("input password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @And("click Login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User is on Dashboard page")
    public void getDashboardPage() {
        String pageTitle = driver.findElement(By.xpath("//h3[@class='chakra-heading css-1wswht5']")).getText();
        Assert.assertEquals(pageTitle, "kasirAja");
        driver.close();
    }

    @And("input wrong password")
    public void inputWrongPassword() {
        driver.findElement(By.id("password")).sendKeys("123456");
    }

    @Then("User gets an error message")
    public void gErrorMessage() {
        String errorMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(errorMessage, "Kredensial yang Anda berikan salah");
        driver.close();
    }

    @When("input (.*) as email$")
    public void inputEmailDDT(String email){
        driver.findElement(By.id("email")).sendKeys(email);
    }

    @And("input (.*) as password$")
    public void inputPasswordDDT(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("verify (.*) login result$")
    public void verifyResultDDT(String status){
        if (status.equals("success")){
            String pageTitle = driver.findElement(By.xpath("//h3[@class='chakra-heading css-1wswht5']")).getText();
            Assert.assertEquals(pageTitle, "kasirAja");
        } else if(status.equals("failed")) {
            String errorMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
            Assert.assertEquals(errorMessage, "Kredensial yang Anda berikan salah");
        }
        driver.close();
    }
}

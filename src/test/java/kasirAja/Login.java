package kasirAja;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Login {
    String baseUrl = "https://kasirdemo.belajarqa.com/";

    @Test
    public void success_login(){
        WebDriverManager.safaridriver().setup();

        //apply safari driver setup
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        //set time out for web driver on waiting element
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //membuka halaman login kasirAja
        driver.get(baseUrl);
        //input email
        driver.findElement(By.id("email")).sendKeys("rarashop@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("password");
        //click Login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        driver.close();

    }

    @Test
    public void failed_login(){
        WebDriverManager.safaridriver().setup();

        //apply safari driver setup
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        //set time out for web driver on waiting element
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //membuka halaman login kasirAja
        driver.get(baseUrl);
        //input email
        driver.findElement(By.id("email")).sendKeys("rarashop@gmail.com");
        //input password
        driver.findElement(By.id("password")).sendKeys("123456");
        //click Login button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String errorMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(errorMessage, "Kredensial yang Anda berikan salah");

        driver.close();
    }
}

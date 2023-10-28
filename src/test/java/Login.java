import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Login {
    @Test
    public void get_title() {
        WebDriver driver;
        String baseUrl = "https://kasirdemo.belajarqa.com/";

        //WebDriverManager.chromedriver().setup();
        WebDriverManager.safaridriver().setup();

        //apply chrome driver setup
        //driver = new ChromeDriver();
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        /*String username = "username";

        WebElement element1 = driver.findElement(By.id(username));
        WebElement element2 = driver.findElement(By.className("button"));

        element1.click();
        element1.sendKeys("email.com");
        element1.getText();

        element2.isDisplayed();
        element2.click();

        driver.findElement(By.xpath("/*contains")).isDisplayed();
        driver.findElement(By.cssSelector("#button")).isDisplayed();
        */

        driver.close();
    }
}
package kasirAja;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {

    @Test
    public void login_ddt(){
        String baseUrl = "https://kasirdemo.belajarqa.com/";
        WebDriverManager.safaridriver().setup();

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data-login.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null){
                String email = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                WebDriver driver = new SafariDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

                driver.get(baseUrl);
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                if (status.equals("success")){
                    String pageTitle = driver.findElement(By.xpath("//h3[@class='chakra-heading css-1wswht5']")).getText();
                    Assert.assertEquals(pageTitle, "kasirAja");
                } else if(status.equals("failed")) {
                    String errorMessage = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(errorMessage, "Kredensial yang Anda berikan salah");
                }
                driver.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}

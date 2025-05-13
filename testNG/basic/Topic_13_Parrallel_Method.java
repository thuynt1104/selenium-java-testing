package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Parrallel_Method {
    WebDriver driver;
    //Nen order theo ten testcase
    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/en/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void register() {
        System.out.println("Register new Account");
    }
    @Test
    public void login() {
        System.out.println("Login to System");
    }
    @Test
    public void order() {
        System.out.println("Order Product");
    }
    @Test
    public void payment() {
        System.out.println("Payment to System");
    }
    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
}

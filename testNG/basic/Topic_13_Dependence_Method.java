package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_13_Dependence_Method {
    WebDriver driver;
    //Nen order theo ten testcase
    @Test
    public void register() {
        driver = new ChromeDriver();
        System.out.println("Register new Account");
        Assert.assertTrue(true);
    }
    @Test (dependsOnMethods = "register")
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

}

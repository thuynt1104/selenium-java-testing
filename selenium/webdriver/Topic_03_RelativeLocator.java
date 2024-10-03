package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_RelativeLocator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(150));
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
    }

    @Test
    public void TC_01_Relative_Locator(){
        driver.findElements(By.xpath("//span[@class='male']"));
        driver.findElements(By.xpath("//span[@class='female']"));
        driver.findElements(By.xpath("//input[@id='FirstName']"));
        driver.findElements(By.xpath("//input[@id='LastName']"));
        driver.findElements(By.xpath("//select[@name='DateOfBirthDay']"));
        driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']"));
        driver.findElements(By.xpath("//select[@name='DateOfBirthYear']"));
        driver.findElements(By.xpath("//input[@name='Email']"));
        driver.findElements(By.xpath("//input[@name='Company']"));
        driver.findElements(By.xpath("//input[@id='Newsletter']"));
        driver.findElements(By.xpath("//input[@id='Password']"));
        driver.findElements(By.xpath("//input[@id='ConfirmPassword']"));
        driver.findElements(By.xpath("//button[@id='register-button']"));
    }

    @AfterClass
    public void afterClass(){

        driver.quit();
    }
}

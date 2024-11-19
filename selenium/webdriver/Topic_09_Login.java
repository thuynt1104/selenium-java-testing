package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_Login {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void TC_01_Empty() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("#send2")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#aadvice-required-entry-pass")).getText(),"This is a required field.");
        

    }

    @Test
    public void TC_02() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
    }

    @Test
    public void TC_03() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
    }

    @Test
    public void TC_04() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
//        driver.get("https://login.mailchimp.com/signup");
//        //lowercase
//        driver.findElement(By.cssSelector("input#new_password")).sendKeys("mailchimp");
//        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
//
//        driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed();
//        driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed();


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

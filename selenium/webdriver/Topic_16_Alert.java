package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Alert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(3000);

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
        //Switch vao alert ma khong dung wait
//        Alert alert1 = driver.switchTo().alert();
        //Accept
        //alert.accept();

        //Cancel
        //alert.dismiss();

        //get text then verify
        //alert.getText();

        //Send key
        //alert.sendKeys("");

    }

    @Test
    public void TC_02_Confirm_Alert() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Thread.sleep(3000);

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");
        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");


    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Thread.sleep(2000);

        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS prompt");
        String name = "ABC";
        alert.sendKeys(name);
        Thread.sleep(2000);
        alert.accept();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: "+ name);
    }

    @Test
    public void TC_04() throws InterruptedException {
//        driver.get("http://the-internet.herokuapp.com/basic_auth");
//        Thread.sleep( 2000);
        String username = "admin";
        String password = "admin";
        // Truyền thăng U/ P vào trong URL
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Thread. sleep( 2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");

    }
    @Test
    public void TC_05_Authentication_Alert() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        driver.get ("https://the-internet.herokuapp.com/");
        Thread. sleep( 2000);
        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");
        System.out.println(authenLink);
        driver.get(passUserToAuthenLink(authenLink, username, password));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_06() throws InterruptedException {
        String username = "admin";
        String password = "admin";
        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");

    }

    public String passUserToAuthenLink(String authenLink, String username, String password) {
        String[] text = authenLink.split("//");
        return text[0] + "//" + username + ":" + password + "@" + text[1];
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_1_Element {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Visible() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        //wait to element visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='tel']")));

    }

    @Test
    public void TC_02_InVisible() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_03_Not_in_Html() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        driver.findElement(By.cssSelector("p.login-with-email")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='tel']")));
    }

    @Test
    public void TC_04_Presence() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        //hien thi tren ui
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.footer a[title='My Account']")));
        //khong hien thi tren ui nhung co trong html
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#header-account a[title='My Account']")));
    }

    @Test
    public void TC_05_Staleness() throws InterruptedException {
        driver.get("https://tiki.vn/");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);
        //Phone textbox appear
        WebElement phoneTexbox = driver.findElement(By.cssSelector("input[name='tel']"));
        Thread.sleep(2000);
        //Phone Textbox khong con trong html => wait staleness ( kiem tra khong con trong html nua)
        driver.findElement(By.cssSelector("p.login-with-email")).click();

        wait.until(ExpectedConditions.stalenessOf(phoneTexbox));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
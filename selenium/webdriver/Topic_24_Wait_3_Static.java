package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_3_Static {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test (description = "Thoi gian sleep = 0")
    public void TC_01() throws InterruptedException {
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        sleepSecond(0);
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @Test (description = "thoi gian implicit < time cho")
    public void TC_02() throws InterruptedException {
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        sleepSecond(3);
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @Test (description = "Thoi gian implicit > time cho")
    public void TC_03() throws InterruptedException {
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        sleepSecond(5);
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    public void TC_04() throws InterruptedException {
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        sleepSecond(10);
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    public void sleepSecond(long longTimeinSecound) {
        try {
            Thread.sleep(longTimeinSecound * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_24_Wait_3_Implicit {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }

    @Test (description = "Thoi gian implicit = 0")
    public void TC_01() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @Test (description = "thoi gian implicit < time cho")
    public void TC_02() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @Test (description = "Thoi gian implicit > time cho")
    public void TC_03() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @Test (description = "Thoi gian implicit > time cho")
    public void TC_04() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#start>button")).click();
        //cho 5s load
        //verify
        Assert.assertEquals(driver.findElement(By.cssSelector("#finish>h4")).getText(), "Hello World!");
    }

    @AfterMethod
    public void afterClass() {
        driver.quit();
    }
}

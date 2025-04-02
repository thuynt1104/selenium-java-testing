package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Wait_4_Explict {
    WebDriver driver;
    WebDriverWait explicitWait;
    By startButton = By.cssSelector("div#start>button");
    By loadingButton = By.cssSelector("div#loading");
    By helloText = By.cssSelector("#finish>h4");
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() throws InterruptedException {
        // Chờ cho 1 Alert được xuất hiện trong HTML + sau đó switch vào
        explicitWait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        // Element clickable (Button/ Checkbox/ Radio/ Link/ Image/..)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        driver.findElement(By.cssSelector("")).click();
        // Element visible (All element)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
        // Element invisible (All element)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());
        // Element selected (Checkbox/ Radio)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());
        // Invisible (All element)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());
        // Element size
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 15));
        Assert.assertEquals(driver.findElements(By.cssSelector("")).size(), 15);
        // Attribute
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""), "value" , "John")) ;
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute( "value"), "John");
        // Text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Selenium"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(), "Selenium");

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
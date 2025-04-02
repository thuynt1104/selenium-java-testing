package webdriver;

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

public class Topic_24_Wait_4_Explict_II {
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

    @Test (description = "Thoi gian Explicit = 0")
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(0));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();
        // 1. wait util step truoc hoan thanh va khong quan tam step sau( loading icon => bien mat) Hellotex
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));

        //2. Cho cho step sau xuat hien ( Hello text hien thi.visiable) khong quan tam step trc
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }
    @Test (description = "Thoi gian Explicit ngan hon dieu kien xay ra")
    public void TC_02() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();
        // 1. wait util step truoc hoan thanh va khong quan tam step sau( loading icon => bien mat) Hellotex
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));

        //2. Cho cho step sau xuat hien ( Hello text hien thi.visiable) khong quan tam step trc
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }

    @Test (description = "Thoi gian Explicit bang thoi gian element xuat hien")
    public void TC_03() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();
        // 1. wait util step truoc hoan thanh va khong quan tam step sau( loading icon => bien mat) Hellotex
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));

        //2. Cho cho step sau xuat hien ( Hello text hien thi.visiable) khong quan tam step trc
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }

    @Test (description = "Thoi gian Explicit dai hon thoi gian element xuat hien")
    public void TC_04() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();
        // 1. wait util step truoc hoan thanh va khong quan tam step sau( loading icon => bien mat) Hellotex
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingButton));

        //2. Cho cho step sau xuat hien ( Hello text hien thi.visiable) khong quan tam step trc
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));
        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
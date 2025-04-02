package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_24_Wait_6_Fluent {
    WebDriver driver;
    WebDriverWait explicitWait;
    FluentWait fluentWait;
    FluentWait<WebDriver> fluentWaitWeb;
    FluentWait<WebElement> elementFluentWait;
    FluentWait<Boolean> fluentWaitBoolean;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
//        fluentWaitWeb = new FluentWait<>(driver);
//        elementFluentWait = new FluentWait<>(driver.findElement(By.cssSelector("")));
//        fluentWaitBoolean = new FluentWait<>(true);
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();
        //Trong vong 5s cu moi 1s tim chu Hello World hien thi
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(5));
        fluentWait.pollingEvery(Duration.ofMillis(333));
        fluentWait.ignoring(NoSuchElementException.class);
        String helloText = (String) fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                System.out.println("++++in======");
                return webDriver.findElement(By.cssSelector("div#finish>h4")).getText();
            }
        });
        Assert.assertEquals(helloText, "Hello World!");
        boolean helloStatus = (boolean) fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return driver.findElement(By.cssSelector("div#finish>h4")).getText().equals("Hello World!");
            }
        });
        Assert.assertTrue(helloStatus);
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://automationfc.github.io/fluent-wait/");
        // Dem nguoc giay tu 12 ve 00 => thoa man dk
        fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class);
        boolean countDown = (boolean) fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                String text =  driver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
                System.out.println(text);
                return text.equals("01:01:00");
            }
        });
        Assert.assertTrue(countDown);
    }

    //tim element voi Polling time la 1s kiem tra 1 lan : WebElement
    public WebElement findElement(By by) {
        //Khai bao + khoi tao
        FluentWait fluentWait = new FluentWait(driver);

        //Config time
        fluentWait.withTimeout(Duration.ofSeconds(10));
        fluentWait.pollingEvery(Duration.ofSeconds(1));
        fluentWait.ignoring(NoSuchElementException.class);

        //condition
        return (WebElement) fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    //Kiem tra elenment hien thi isDisplay
    public boolean isElementisDisplayed(By by) {
        FluentWait fluentWait = new FluentWait(driver);
        //Config time
        fluentWait.withTimeout(Duration.ofSeconds(15));
        fluentWait.pollingEvery(Duration.ofSeconds(1));
        fluentWait.ignoring(NoSuchElementException.class);

        return (boolean) fluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return findElement(by).isDisplayed();
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_24_Wait_5_Mixing {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Only_Implicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php");
        //Dung timeout cua implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("input#search"));
    }

    @Test
    public void TC_02_Only_Explicit() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Lấy 10s của Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        // Os của Implicit
        driver.findElement(By.cssSelector("div#advice-required-entry-email"));

    }

    @Test
    public void TC_03_Implicit_and_Explicit() throws InterruptedException {
        //equal 10s = 10s
        //implicit>explicit 12s : 12s
        //Explicit > implicit 10s-12s: 20s
        driver.get ("https://live.techpanda.org/index.php/customer/account/login/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(4));
        System.out.println("Start time" + getDatetimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        } catch (Exception e) {
            System.out.println("End time" + getDatetimeNow());
            throw new RuntimeException(e);
        }

    }

    @Test
    public void TC_04_Only_Explicit_WebElement() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds (10));

        // Lấy 10s của Explicit
//        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));
        // Os của Implicit
        driver.findElement(By.cssSelector("div#advice-required-entry-email")) ;
    }
    @Test
    public void TC_05_Only_Explicit_WebElement() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        // Dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        // Của Explicit nhưng fail Os
        explicitWait.until(ExpectedConditions.visibilityOf(element));

    }
    String getDatetimeNow(){
        Date date = new Date();
        return date.toString();
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_24_Wait_7_PageReady {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Element_Invisible() throws InterruptedException {
        driver.get ("https://api.orangehrm.com/");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner")));
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#project h1"),"OrangeHRM REST API Documentation"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")) .getText(), "OrangeHRM REST API Documentation");
    }

    @Test
    public void TC_02_AllElement_Invisible() throws InterruptedException {
        driver.get ("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector ("input[name='username']")));
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")) .sendKeys ("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        // Chuyển qua trang PIM
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text(='PI™']")));
        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        explicitWait.until(ExpectedConditions. textToBe(By.cssSelector("div.oxd-topbar-header h6"), "PIM"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-topbar-header h6")).getText(),"PIM");
        // Chuyển qua trang
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']")));
        driver.findElement(By.xpath("//span[text()='Time']")).click();
        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.oxd-topbar-header h6"), "Time"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.oxd-topbar-header h6")) .getText(), "Time|");

    }

    @Test
    public void TC_03(){
        driver.get ("https://admin-demo.nopcommerce.com/");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#Email")));
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")) .sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang Product
        driver.get("https://admin-demo.nopcommerce.com/Admin/Product/List");
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang Categories
        driver.get("https://admin-demo.nopcommerce.com/Admin/Category/List");
        Assert.assertTrue(isPageLoadedSuccess());

        // Chuyển qua trang ProductReview
        driver.get("https://admin-demo.nopcommerce.com/Admin/ProductReview/List");
        Assert.assertTrue(isPageLoadedSuccess());
    }

    public boolean isAllLoadingSpinnerInvisible() {
        return explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_Browser_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Page_Url() {
        driver.get("http://live.techpanda.org/");

        // click My Account
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        // String loginUrl = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        // change link
        driver.findElement(By.cssSelector(".buttons-set a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertEquals(driver.getTitle(), "Customer Login");

        driver.findElement(By.cssSelector(".buttons-set a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_03_Navigation() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector(".buttons-set a[title='Create an Account']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }

    @Test
    public void TC_04_Get_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
//        Assert.assertEquals(driver.getPageSource(), "Login or Create an Account");
        driver.findElement(By.cssSelector(".buttons-set a[title='Create an Account']")).click();

//        Tuyet doi
//        Assert.assertEquals(driver.getPageSource(), "Create an Account");

//        Tuong doi
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }
    @Test
    public void TC_05() {
    }
    @Test
    public void TC_06() {
    }
    @Test
    public void TC_07() {
    }
    @Test
    public void TC_08() {
    }
    @Test
    public void TC_09() {
    }
    @Test
    public void TC_10() {
    }
    @Test
    public void TC_11() {
    }
    @Test
    public void TC_12() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

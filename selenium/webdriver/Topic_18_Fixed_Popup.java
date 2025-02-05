package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Fixed_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        driver = new EdgeDriver();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage ().window().maximize();

    }

    @Test
    public void TC_01_NgoaiNgu24h_Fixed_InDom_NotFound() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        //Kiem tra popup
        By loginDialog = By.cssSelector("div#custom-dialog div[role='dialog']");
        //Kiem tra popup hien thi
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());
        //input data
        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("test2");
        driver.findElement(By.cssSelector("input[placeholder='Mật khẩu']")).sendKeys("test21");

        driver.findElement(By.xpath("//div[@id='custom-dialog']//button[text()='Đăng nhập']")).click();
        //Close popup
        driver.findElement(By.cssSelector("div#custom-dialog h2>button")).click();
        Thread.sleep(3000);
        //Kiem tra popup khong con hien thi
        Assert.assertEquals(driver.findElements(loginDialog).size(), 0);

//        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

    }

    @Test
    public void TC_02_Fixed_InDom() throws InterruptedException {
        driver.get("https://zingpoll.com/");
        driver.findElement(By.cssSelector("a#Loginform")).click();
        Thread.sleep(2000);
        By loginPopup = By.cssSelector("div#Login div.modal-dialog");

        // kiem tra hien thi
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        //Close
        driver.findElement(By.cssSelector("div#Login div.modal-dialog button.close")).click();
        Thread.sleep(2000);
        //kiem tra hien thi
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);

        By loginPopup = By.cssSelector("div.ReactModalPortal div[role='dialog']");
        //kiem tra
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());
        // close
        driver.findElement(By.cssSelector("div.ReactModalPortal button.btn-close")).click();
        Thread.sleep(2000);
        //kiem tra
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_18_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage ().window().maximize();

    }

    @Test
    public void TC_01_Dehieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(5000);
        By popup = By.cssSelector("div.modal-content");
        if(driver.findElement(popup).isDisplayed()) {
            // th1 - Nếu popup hiển thị thì Close đi rồi click vào Đăng Nhập
            System.out.println("th1 - Nếu popup hiển thị");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
        }
        //th2 Login
        System.out.println("th2");
        driver.findElement(By.cssSelector("a.signin-site-menu")).click();
        // Verify form Login hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());
    }

    @Test
    public void TC_02_KMP_InDom() throws InterruptedException {
        driver.get("https://www.kmplayer.com/home");
        By popup = By.cssSelector("div.pop-container");
        if(driver.findElement(popup).isDisplayed()) {
            // th1 - Nếu popup hiển thị thì Close
            System.out.println("th1 - Nếu popup hiển thị");
            driver.findElement(By.cssSelector("div.pop-container div.close")).click();
            Thread.sleep(2000);
        }
        //th2 Login
        System.out.println("th2");
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='pc']/a[text()='KMPlayer']")).click();
        // Verify header text hiển thị
        Assert.assertEquals(driver.findElement(By.cssSelector("div.sub>h1")).getText(),"KMPlayer - Video Player for PC");
    }

    @Test
    public void TC_03_Javacodegeeks() throws InterruptedException {
        driver. get ("https://www.javacodegeeks.com/");
        Thread.sleep(2000);

        By popup = By.xpath("//div[starts-with(@data-title, 'Newsletter') and not(contains(@style, 'display:none'))]");
        if(driver.findElements(popup).size() > 0 && driver.findElements(popup).get(0).isDisplayed()) {
            System.out.println("Nếu popup hiển thị thì Close đi rồi qua step tiếp theo");
            driver.findElement(By.xpath("//div[starts-with(@data-title, 'Newsletter') and " + "not (contains (@style, 'display:none'))1//a[text()='*']")).click();
            Thread.sleep(2000);
        }
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Selenium");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(2000);
        List<WebElement> articles = driver.findElements(By.cssSelector("ul#posts-container h2.post-title>a"));
        for (WebElement article: articles) {
            System.out.println(article.getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

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
import java.util.List;

public class Topic_24_Wait_2_FindElements {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

    }

    @Test
    public void TC_01_FindElement() throws InterruptedException {
        //Tim thay 1 element
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_FindElement() throws InterruptedException {
        //Tim thay  0 element
        driver.findElement(By.cssSelector("input#emailAddress"));
    }

    @Test
    public void TC_02_MultiElement() throws InterruptedException {
        driver.findElement(By.cssSelector("input")).sendKeys("Test");
    }

    @Test
    public void TC_04_FindElements_Element() throws InterruptedException {
        List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
        System.out.println(elements.size());
    }

    @Test
    public void TC_05_FindElements_NoElement() throws InterruptedException {
        // Tìm thấy 0 element
        // không thấy element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hết time-out là 10s
        // Không đánh failed testcase mà sẽ trả về 1 list rỗng (empty) = 0
        List<WebElement> elementList = driver.findElements(By.cssSelector("input#emailAddress"));
        System.out.println(elementList.size());
    }

    @Test
    public void TC_06_FindElements() throws InterruptedException {
        List<WebElement> elementList = driver.findElements(By.cssSelector("input:not([type='hidden'])"));
        System.out.println(elementList.size());
        for (WebElement element : elementList) {
            element.sendKeys("Test");
        }
        List<WebElement> myAccounts = driver.findElements(By.cssSelector("a[title='My Account']"));
        System.out.println(myAccounts.size());
        myAccounts.get(1).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

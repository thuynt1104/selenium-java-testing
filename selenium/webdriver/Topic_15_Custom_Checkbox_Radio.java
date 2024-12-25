package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Custom_Checkbox_Radio {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://login.ubuntu.com/");
        Thread.sleep(2000);

        // Case 01:
        // Thẻ input ko click được
        // Thẻ input dùng verify được
        // driver.findElement(By.cssSelector("input#id_new_user")).click();
        // Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());
        // Case 02:
        // Ko dùng thẻ input để click // Dùng thẻ khác để thay thế
        // Thẻ khác này lại ko verify được
        // Thẻ label/ span/ li/ ul/... trạng thái selected luôn bằng false
        // driver.findElement(By.cssSelector("label.new-user")).click();
        // Thread.sleep (2000);
        // Assert.assertTrue(driver.findElement(By.cssSelector("label.new-user")).isSelected());
        // Case 03:
        // Dùng thẻ label để click // Dùng thẻ input để verify
//        driver.findElement(By.cssSelector("label.new-user")).click();
//        Thread.sleep(2000);
//        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());
        //Case4
        // click = js
        //verify bt
        By registerRadio = By.cssSelector("input#id_new_user");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));
        Thread.sleep( 2000);
        Assert.assertTrue(driver.findElement(registerRadio).isSelected());

        By termCheckbox = By.cssSelector("input#id_accept_tos");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver. findElement(termCheckbox));
        Thread.sleep( 2000);
        Assert.assertTrue(driver.findElement(termCheckbox).isSelected());
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(2000);

        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");
        // Click lên checkbox/ radio
        driver.findElement(canthoRadio).click();
        Thread.sleep(2000);

        // Verify = if (checkbox.getDomAttribute name: "aria-checked")) hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

        // Verify lấy thuộc tính ra
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute ( "aria-checked"), "true");

        By myQuangCheckbox = By.cssSelector("div[aria-label='Mì Quảng']");

        // Click lên checkbox/ radio
        driver.findElement(myQuangCheckbox).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(myQuangCheckbox).getDomAttribute( "aria-checked"),"true");

        // Select all checkboxes
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div[role='checkbox']"));
        for (WebElement checkbox: allCheckboxes) {
            if (!checkbox.getDomAttribute( "aria-checked").equals ("true")) {
                checkbox.click();
            }
        }

        //Verify all checkbox
        for (WebElement checkbox: allCheckboxes) {
            Assert.assertEquals(checkbox.getDomAttribute( "aria-checked"),"true");
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

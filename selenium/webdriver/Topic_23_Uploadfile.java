package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_23_Uploadfile {
    WebDriver driver;
    String uploadFilePath = System.getProperty("user.dir") + "/UploadFile/";
    String fileName1 = "bunhome.jpeg";
    String fileName2 = "giay.jpeg";
    String fileName3 = "tui.jpeg";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadFile = By.xpath("//input[@name='files[]']");


//        //load file
//        driver.findElement(uploadFile).sendKeys(uploadFilePath + fileName1);
//        driver.findElement(uploadFile).sendKeys(uploadFilePath + fileName2);
//        driver.findElement(uploadFile).sendKeys(uploadFilePath + fileName3);
        //multi load
        driver.findElement(uploadFile).sendKeys(uploadFilePath + fileName1 + "\n"+ uploadFilePath + fileName2 + "\n"+ uploadFilePath + fileName3);


        // Verify file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName1 + "']")).isDisplayed());


        // Upload từng File
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement startButton : startButtons) {
            startButton.click();
            Thread.sleep(2000);
        }

        // Verify file
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + fileName1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + fileName2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + fileName3 + "']")).isDisplayed());
    }

    @Test
    public void TC_02() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

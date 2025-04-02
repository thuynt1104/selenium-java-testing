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

public class Topic_24_Wait_4_Explict_III {
    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFilePath = System.getProperty("user.dir") + "/UploadFile/";
    String fileName1 = "bunhome.jpeg";
    String fileName2 = "giay.jpeg";
//    String fileName3 = "tui.jpeg";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //wait den khi calendar hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ctl00_ContentPlaceholder1_Panel1")));
        //wait den khi text dc hien thi
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
        // wait de select ngay hien tai
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()=2]")));
        driver.findElement(By.xpath("//td/a[text()=2]")).click();
        //wait den khi loading xong
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>.raDiv")));
        //wait ngay duoc chon hien thi
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("#ctl00_ContentPlaceholder1_Label1"), "Wednesday, April 2, 2025"));
        Assert.assertEquals(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, April 2, 2025");
        //wait attribute dc selected
        explicitWait.until(ExpectedConditions.attributeToBe(By.xpath("//a[text()='2']/parent::td"), "class" , "rcSelected")) ;
//        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='2']/parent::td")).getDomAttribute( "class"), "rcSelected");
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='2']/parent::td")).getDomAttribute("class").contains("rcSelected"));
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://gofile.io/?t=uploadFiles");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //wait den khi loading icon khong hien thi
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));
        //load file
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(uploadFilePath + fileName1 + "\n"+ uploadFilePath + fileName2 );
        //wait den khi process bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("#fileList div.progress-container"))));
        //wait den khi text hien thi
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(".text-center>h2"),"Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector(".text-center>h2")).getText(), "Upload Complete");
        String link = driver.findElement(By.cssSelector("a.linkSuccessCard")).getAttribute("href");
        driver.get(link);
        //wait den khi loading icon khong hien thi
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));
        //kiem tra
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='truncate']/a[text()='"+ fileName1+"']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+ fileName1 +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+ fileName2 +"']")).isDisplayed());
//        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='"+ fileName3 +"']")).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
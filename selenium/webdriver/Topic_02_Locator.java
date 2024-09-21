package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("txtSearch")).sendKeys("Samsung");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("btn-anis-effect"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("chkRight"));
    }

    @Test
    public void TC_04_Link() {
        driver.findElements(By.linkText("Đăng Nhập"));
    }

    @Test
    public void TC_05_Partial_Link() {
        driver.findElements(By.partialLinkText("Đăng"));
        driver.findElements(By.partialLinkText("Nhập"));
        driver.findElements(By.partialLinkText("Ký"));
    }

    @Test
    public void TC_06_TagName() {
        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Total of link = " + linkNumber);
    }

    @Test
    public void TC_07_Css() {
        //CSS vs ID
        driver.findElement(By.cssSelector("input#txtFirstname"));
        driver.findElement(By.cssSelector("#txtFirstname"));
        driver.findElement(By.cssSelector("input[id='txtFirstname']"));

        //CSS vs Class
        driver.findElement(By.cssSelector("div[class='box-search']"));
        driver.findElement(By.cssSelector(".box-search"));
        driver.findElement(By.cssSelector("[class='box-search']"));

        //CSS vs Name
        driver.findElement(By.cssSelector("input[name='txtEmail']"));
        driver.findElement(By.cssSelector("input[name='txtCEmail']"));
        driver.findElement(By.cssSelector("input[name='txtPassword']"));
        driver.findElement(By.cssSelector("input[name='txtCPassword']"));
        driver.findElement(By.cssSelector("input[name='txtPhone']"));

        //CSS vs Link
        driver.findElement(By.cssSelector("a[href='https://alada.vn/tai-khoan/dang-ky.html']"));
        driver.findElement(By.cssSelector("a[href='https://alada.vn/tai-khoan/dang-nhap.html']"));

        //CSS vs PartialLink
        driver.findElement(By.cssSelector("a[href^='https://alada.vn/tai-khoan/dang-ky.html']"));
        driver.findElement(By.cssSelector("a[href$='tai-khoan/dang-ky.html']"));

        //CSS vs tagName
        int numberTagName = driver.findElements(By.cssSelector("a")).size();
        System.out.println("Total of tag a = " + numberTagName);
    }

    @Test
    public void TC_08_Xpath() {
        //xpath vx ID
        driver.findElement(By.xpath("//input[@id='txtFirstname']"));
        driver.findElement(By.xpath("//input[@id='txtEmail']"));
        driver.findElement(By.xpath("//input[@id='txtCEmail']"));
        driver.findElement(By.xpath("//input[@id='txtPassword']"));
        driver.findElement(By.xpath("//input[@id='txtCPassword']"));
        driver.findElement(By.xpath("//input[@id='txtPhone']"));


        //xpath vs Class
        driver.findElement(By.xpath("//div[@class='box-search']"));

        //xpath vs Name
        driver.findElement(By.xpath("//input[@name='txtFirstname']"));
        driver.findElement(By.xpath("//input[@name='txtEmail']"));
        driver.findElement(By.xpath("//input[@name='txtCEmail']"));
        driver.findElement(By.xpath("//input[@name='txtPassword']"));
        driver.findElement(By.xpath("//input[@name='txtCPassword']"));
        driver.findElement(By.xpath("//input[@name='txtPhone']"));

        //xpath vs Link
        driver.findElement(By.xpath("//a[text()='Đăng Ký']"));
        driver.findElement(By.xpath("//a[@href='https://alada.vn/tai-khoan/dang-ky.html']"));

        //xpath vs PartialLink
        driver.findElement(By.xpath("//a[starts-with(@href,'https://alada.vn/')]"));
        driver.findElement(By.xpath("//a[contains(@href,'https://alada.vn/tai-khoan')]"));

        //xpath vs tagName
        int aNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Total = " + aNumber);

    }

    @AfterClass
    public void afterClass() {

        driver.quit();
    }
}

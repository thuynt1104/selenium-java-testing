package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Xpath {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_01() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //text()
        driver.findElement(By.xpath("//p[text()='Đăng ký tài khoản mới']"));
        //contains(text(),"")
        driver.findElement(By.xpath("//p[contains(text(),'tài khoản mới')]"));
        //contains(.,'')
        driver.findElement(By.xpath("//p[contains(.,'tài khoản mới')]"));
        //contains(string,'')
        driver.findElement(By.xpath("//p[contains(string(),'tài khoản mới')]"));
        driver.findElement(By.xpath("//h3[contains(string(),'ALADA')]"));
        //not
        driver.findElement(By.xpath("//p[not(contains(text(),'tài khoản mới'))]"));
        //and or
        driver.findElement(By.xpath("//p[contains(text(),'tài khoản mới') or contains(text(),'ký') ] "));
        driver.quit();

    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/basic-form/");
        //concat()
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\"',\", What's happened?\")]"));
        driver.quit();
    }

    @Test
    public void TC_03() {
        driver.get("https://automationfc.github.io/basic-form/");
        //position()
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()='1']"));
        //index
        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()='1']"));
        //last()
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]"));
        //last()-1 or count()-1
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()-1]"));
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)-1]"));

        driver.quit();
    }

//    @AfterClass
//    public void afterClass() {
//        driver.quit();
//    }
}

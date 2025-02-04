package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Topic_17_Action_PI {
    WebDriver driver;
    Actions action;


    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        //chay qua profile // chua lam dc nho lam
//        EdgeOptions edgeOptions = new EdgeOptions();
//        edgeOptions.addArguments("--user-data-dir=C:/Users/daomi/AppData/Local/Microsoft/Edge/User Data/");
//        edgeOptions.addArguments("--profile-directory=Profile 1");
//        driver = new EdgeDriver(edgeOptions) ;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage ().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
        action.pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");


    }

    @Test
    public void TC_02_Myntra() {
        driver.get ("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).pause(Duration.ofSeconds(2)).perform();
        action.click(driver.findElement(By.xpath( "//div[@class='desktop-navLink']//a[text()='Home & Bath']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
        System.out.println(driver.findElement(By.cssSelector("div.title-container")).getText());
    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
//        Thread.sleep(10000);
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).pause(Duration.ofSeconds(1)).perform();
        action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']"))).pause(Duration.ofSeconds(1)).perform();
        action.click(driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Gọt Bút Chì']"))).perform ();
        Assert.assertEquals(driver. findElement(By.cssSelector("ol.breadcrumb strong")) .getText(), "GỌT BÚT CHÌ");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_20_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Map() throws InterruptedException {
        //iframe A chua B
        driver.get("https://www.embedgooglemap.net/");
        Thread.sleep(3000);
        //switch iframe B
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas>iframe")));
        // driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div>iframe"))); // Nen dung
        // lam tren iframe B
        String addressName = driver.findElement(By.cssSelector("div.place-name")).getText();
        System.out.println(addressName);
        //Switch vao C
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div>iframe")));
        System.out.println(driver.getPageSource());
        //comeback B
        driver.switchTo().parentFrame();
        System.out.println(addressName);
        //B quay lai A
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input#s")).clear();
        driver.findElement(By.cssSelector("input#s")).sendKeys("22 Le Loi, Ho Chi Minh");

    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("button.osano-cm-accept-all")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(3000);
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("North Dorm");
        driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input#FSsubmit")).click();

        //switch ra ngoai
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav[class='header header--desktop'] a.menu-item-login")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error.auth-error")).getText(),"Username and password are both required.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_19_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
//        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage ().window().maximize();

    }

    @Test
    public void TC_01_Shadow_DOM() throws InterruptedException {
        driver.get("https://automationfc.github.io/shadow-dom/");
        // tim element chua shadow
        WebElement shadowElement = driver.findElement(By.cssSelector("div#shadow_host"));
        //get shadow
        SearchContext firstShadowRoot = shadowElement.getShadowRoot();

        //Element in shadow
        String shadowText = firstShadowRoot.findElement(By.cssSelector("a")).getText();
        System.out.println(shadowText);

        //check
        String checkText1 = firstShadowRoot.findElement(By.cssSelector("#shadow_content>span")).getText();
        Assert.assertEquals(checkText1, "some text");
        //Tim ra element chua cai shadow t2 shadow in shadow
        WebElement shadowElement2 = firstShadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
        //get shadow 2 in shadow 1
        SearchContext secondShadowRoot = shadowElement2.getShadowRoot();
        //get text and in
        System.out.println(secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText());

        WebElement checkCheckbox = firstShadowRoot.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkCheckbox.isSelected());
    }

    @Test
    public void TC_02_Appspot_Shadow_DOM() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(5000);

        WebElement shadowElement1 = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext firstShadowRoot = shadowElement1.getShadowRoot();

        firstShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(3000);

        WebElement shadowElement2 = firstShadowRoot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));
        SearchContext secondShadowRoot = shadowElement2.getShadowRoot();

        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        WebElement shadowElement3 = firstShadowRoot.findElement(By.cssSelector("main.main-content>book-explore"));
        SearchContext thirdShadowRoot = shadowElement3.getShadowRoot();

        List<WebElement> shadowElements4 = thirdShadowRoot.findElements(By.cssSelector("ul.books>li>book-item"));
        for (WebElement element : shadowElements4) {
            SearchContext shadowRoot = element.getShadowRoot();
            System.out.println(shadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_21_Window_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.addPreference("geo.enabled", false);
        firefoxOptions.addPreference("geo.provider.use_corelocation", false);
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String githubID = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='GOOGLE']")) .click();
        Thread.sleep (3000);
        switchWindowById(githubID);

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium Webdriver");
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        //comeback github
        String googleID = driver.getWindowHandle();
        switchWindowById(googleID);
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")) .click();
        Thread.sleep (3000);
        switchMultiWindowByTitle("Facebook");
        Thread.sleep (3000);
        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());
        switchMultiWindowByTitle("Selenium WebDriver");

    }

    @Test
    public void TC_02_Panda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")) .click();

        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath( "//button[@title='Compare']")).click();
        switchMultiWindowByContain("Products Comparison List");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        Thread.sleep (3000);
        switchMultiWindowByContain("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys ("Samsung Galaxy");
        driver.findElement(By.cssSelector("button.search-button")).click();
    }

    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://www.naukri.com/");
        driver.findElement(By.cssSelector("div.keywordSugg input.suggestor-input")).sendKeys("automation test");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("div.qsbSubmit")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()='HCLTech']/ancestor: :div[contains(@class, 'row2')]/preceding-sibling::div/a[text()='Senior Automation Test Engineer']")).click();
        Thread.sleep(3000);
        switchMultiWindowByContain("Senior Automation Test Engineer");

        driver.findElement(By.xpath( "//h3[text()='Sap Automation Tester with worksoft certify']")).click();
        switchMultiWindowByContain("Sap Automation Tester");
    }

    // Dung duy nhat cho 2 window
    private void switchWindowById(String windowID) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            if (!windowHandle.equals(windowID)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
    // Dung  cho 2 window tro len
    private void switchMultiWindowByTitle(String expectedTitle) throws InterruptedException {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            //switch tung window
            driver.switchTo().window(windowHandle);
            String currentTitle = driver.getTitle();
            if (expectedTitle.equals(currentTitle)) {
                Thread.sleep(2000);
                break;
            }
        }
    }

    // close all window except main
    private void closeAllExceptMain(String windowID) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            if (!windowHandle.equals(windowID)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }
    private void switchMultiWindowByContain(String containTitle) throws InterruptedException {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            //switch tung window
            driver.switchTo().window(windowHandle);
            String currentTitle = driver.getTitle();
            assert currentTitle != null;
            if (containTitle.contains(currentTitle)) {
                Thread.sleep(2000);
                break;
            }
        }
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_17_Action_PII {
    WebDriver driver;
    Actions action;


    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage ().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_ClickAndHover_Fix() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        action.clickAndHold(numbers.get(4)).pause(Duration.ofSeconds(2))
                .moveToElement(numbers.get(11))
                .pause(Duration.ofSeconds(2))
                .release()
                .perform();
        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(), 8);
    }

    @Test
    public void TC_02_ClickAndHover_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        Keys keys = null;
        if (osName.contains("Mac OS X")) {
            keys = Keys.COMMAND;
        } else {
            keys = Keys.CONTROL;
        }
        //Actual Number : 3 6 7 12 14 20
        List<String> actualNumbers = new ArrayList<String>();
        actualNumbers.add("3");
        actualNumbers.add("6");
        actualNumbers.add("7");
        actualNumbers.add("12");
        actualNumbers.add("14");
        actualNumbers.add("20");

        action.keyDown(keys).perform(); // nhan phim command
        //click 3 6 7 12 14 20
//        action.click(numbers.get(2))
//                .pause(Duration.ofSeconds(2))
//                .click(numbers.get(5))
//                .pause(Duration.ofSeconds(2))
//                .click(numbers.get(6))
//                .pause(Duration.ofSeconds(2))
//                .click(numbers.get(11))
//                .pause(Duration.ofSeconds(2))
//                .click(numbers.get(13))
//                .pause(Duration.ofSeconds(2))
//                .click(numbers.get(19))
//                .pause(Duration.ofSeconds(2)).perform();
        for( String number : actualNumbers ) {
            action.click(numbers.get(Integer.parseInt(number) - 1 ));
        }
        action.keyUp(keys).perform();
        List<WebElement> numbersSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numbersSelected.size(), 6);

        //Expected Number
        List<String> expectedNumbers = new ArrayList<String>();
        for(WebElement number : numbersSelected) {
            expectedNumbers.add(number.getText());
        }
        Assert.assertEquals(actualNumbers, expectedNumbers);
    }

    @Test
    public void TC_03_Double_Click() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).pause(Duration.ofSeconds(1)).perform();
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //firefox
        if (driver.toString().contains("Firefox")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClick);
            Thread.sleep(2000);
        }
        action.doubleClick(doubleClick).pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_03_Right_Click() throws InterruptedException {
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        //check quit khong hien thi
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        //right click
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).pause(Duration.ofSeconds(3)).perform();
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
        //hover vao quit
        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).pause(Duration.ofSeconds(1)).perform();
        //verify hover quit
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        //Click Quit
        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).pause(Duration.ofSeconds(1)).perform();
        Thread.sleep(3000);
        //accept
        driver.switchTo().alert().accept();
        //verify quit
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

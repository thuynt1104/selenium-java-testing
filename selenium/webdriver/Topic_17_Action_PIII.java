package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.reporters.Files.readFile;

public class Topic_17_Action_PIII {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    String projectPath = System.getProperty("user.dir") +"/dragDrop/drag_and_drop_helper.js";

    @BeforeClass
    public void initialBrowser() {
//        driver = new EdgeDriver();
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage ().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Scroll() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
//        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).pause(Duration.ofSeconds(1)).perform();
        WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //firefox
        if (driver.toString().contains("Chrome") || driver.toString().contains("Edge") ) {
            action.scrollToElement(doubleClick).perform();
            Thread.sleep(2000);
        }
        else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", doubleClick);
            Thread.sleep(2000);
        }
        action.doubleClick(doubleClick).pause(Duration.ofSeconds(3)).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_02_Drag_Drop_HTML4() throws InterruptedException {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement sourceCircle = driver.findElement(By.cssSelector("#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("#droptarget"));
        //Truoc khi scroll
        Assert.assertEquals(targetCircle.getText(), "Drag the small circle here.");

        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(2000);
        Assert.assertEquals(targetCircle.getText(), "You did great!");
        Assert.assertEquals(Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase(),"#03A9F4");
    }

    @Test
    public void TC_03_Drag_Drop_HTML5_Selenium() throws InterruptedException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#column-b"));

        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        action.dragAndDrop(sourceCircle, targetCircle).perform();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");

    }


    @Test
    public void TC_04_Drag_Drop_HTML5_Jquery() throws InterruptedException, IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        String jqueryContent = getContentFile(projectPath);

        String sourceCss = "div#column-a";
        String targetCss = "div#column-b";

        jqueryContent = jqueryContent + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \""+ targetCss + "\"});";

        System.out.println(jqueryContent);
        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        jsExecutor.executeScript(jqueryContent);
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");

    }

    @Test
    public void TC_05_Drag_Drop_HTML5_Robot() throws InterruptedException, IOException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        dragAndDropHTML5ByXpath(  "//div[@id='column-a']", "//div[@id='column-b']");
        Thread.sleep (3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        dragAndDropHTML5ByXpath( "//div[@id='column-a']", "//div[@id='column-b']");
        Thread.sleep (3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = StandardCharsets.UTF_8;
        try (FileInputStream stream = new FileInputStream(filePath)) {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        }
    }
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
}

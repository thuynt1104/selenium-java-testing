package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor ;
    String emailAddress = "emailAddress@gmail.com";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_WorkAround() throws InterruptedException {
        driver.get ("http://live.techpanda.org/index.php/");
        //Click element bi an or che
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")));
        Thread.sleep(5000);

        // Click vào 1 element mà không cần Hover chuột vào Menu/ Tooltip
        driver.get("https://www.fahasa.com/");
        jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Sách Trong Nước']")));
        Thread.sleep(5000);
    }

    @Test
    public void TC_02_TechPanda() throws InterruptedException {
        navigateToUrlByJS("http://live.techpanda.org/");
        String techPandaDomain = (String) executeForBrowser("return document.domain;");
        System.out.println(techPandaDomain);
        Assert.assertEquals(techPandaDomain, "live.techpanda.org");
        String homePageUrl = (String) executeForBrowser("return document.URL;");
        System.out.println(homePageUrl);
        Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
        clickToElementByJS("//a[text()='Mobile']");
        clickToElementByJS("//a[text ()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
        Assert.assertTrue(isExpectedTextInInnerText( "Samsung Galaxy was added to your shopping cart."));
        String innerText = (String) executeForBrowser ("return document.documentElement.innerText;");
        Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
        Assert.assertEquals(getElementTextByJS( "//li[@class='success-msg']//span"),"Samsung Galaxy was added to your shopping cart.");
        clickToElementByJS( "//a[text ()='Customer Service']");

        String customerServiceTitle = (String) executeForBrowser ("return document.title;");
        System.out.println(customerServiceTitle);
        Assert.assertEquals(customerServiceTitle,"Customer Service");
        scrollToElementOnTop("//input [@id='newsletter']");

//        sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
        sendkeyToElementByJS("//input[@id='newsletter']", emailAddress);
        clickToElementByJS("//button[@title='Subscribe']");
        Assert.assertEquals(getElementTextByJS("//li[@class='success-msg']//span"),"Thank you for your subscription.");
        navigateToUrlByJS("https://www.facebook.com/");
        String facebookDomain = (String) executeForBrowser ("return document.domain;");
        System.out.println(facebookDomain);
        Assert.assertEquals(facebookDomain,"www.facebook.com");
    }

    @Test
    public void TC_03_Links() throws InterruptedException {
        driver.get("https://warranty.rode.com/register");
        driver.findElement(By.xpath("//button[contains(string(), 'Register')]")).click();
//        String validationMessage;
//
//        // Cach 1: Selenium 4.x
//        WebElement element = driver.findElement(By.xpath("//input[@id='name']"));
//        validationMessage = element.getDomProperty("validationMessage");
//
//        // Cach 2: Selenium 3.x
//        validationMessage = getElementValidationMessage("//input[@id='name']");
        sleepInSecond(3);
        String validationMessage = getElementValidationMessage("//input[@id='name']");
        Assert.assertEquals(validationMessage,"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Automation FC");

        driver.findElement (By.xpath("//button[contains(string(), 'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='email']")) .sendKeys ("dam@gmail@hotmail.com");
        driver.findElement(By.xpath("//button[contains(string(),'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"),"Please enter an email address.");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")) .sendKeys("dam@hotmail.com");
        driver.findElement(By.xpath("//button[contains(string(), 'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[contains(string(), 'Register')]")).click();
        sleepInSecond(2);
        Assert.assertEquals(getElementValidationMessage("//input[@id='password_confirmation']"),"Please fill out this field.");

    }

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getElementTextByJS(String locator) {
        return (String) jsExecutor.executeScript ( "return arguments[0].textContent;", getElement(locator));
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSecond(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSecond(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    @Test
    public void TC_03() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

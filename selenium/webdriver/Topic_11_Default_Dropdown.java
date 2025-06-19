package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_11_Default_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass() {
        // EdgeOptions edgeOptions = new EdgeOptions();
        //  edgeOptions.addArguments("--user-data-dir=/Users/lap14811/Library/Application Support/Microsoft Edge/");
        // edgeOptions.addArguments("--profile-directory=Profile 8");
        // driver = new EdgeDriver(edgeOptions);
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("geo.enabled", false);
        option.addPreference("geo.provider.use_corelocation", false);
        driver = new FirefoxDriver(option);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Nopcommerce() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/register");
        Thread.sleep(5000);
        String firstName = "Trinh";
        String lastName = "Tô";
        String email = "Trinh" + new Random().nextInt(9999) + "@yopmail.com";
        String company = "ABC company";
        String birthDay = "1";
        String birthMonth = "May";
        String birthYear = "1980";
        //Input các thông tin hợp lệ vào form
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(birthDay);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(birthMonth);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(birthYear);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Company")).sendKeys(company);
        driver.findElement(By.id("Password")).sendKeys("Tt@123456");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("Tt@123456");
        // Click Register
        driver.findElement(By.id("register-button")).click();
        // Check text displayed sau khi đăng ký thành công
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
        // Click MyAccount
        driver.findElement(By.cssSelector("a.ico-account")).click();
        //Verify info
        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), birthDay);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth"))).getFirstSelectedOption().getText(), birthMonth);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), birthYear);

        Thread.sleep(5000);

    }

    @Test
    public void TC_02_Rode() throws InterruptedException {
        driver.get("https://www.rode.com/wheretobuy");
        // Check dropdown không hỗ trợ thuộc tính multi select
        // Chọn giá trị VietNam trong dropdown và HCM trong city textbox
        // Click Search btn
        new Select(driver.findElement(By.cssSelector("select[id='country']")))
                .selectByVisibleText("Vietnam");
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input[id='map_search_query'")).sendKeys("Ho Chi Minh");
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        Thread.sleep(3000);

        List<WebElement> dealerBranches = driver.findElements(By.cssSelector("div.dealer_branch h4"));
        Assert.assertEquals(dealerBranches.size(),16);

        // In ra console tất cả các Dealers name
        for (WebElement dealerName : dealerBranches){
            System.out.println(dealerName.getText());
        }


    }
    @AfterClass
    public  void afterClass(){
        driver.quit();
    }
}

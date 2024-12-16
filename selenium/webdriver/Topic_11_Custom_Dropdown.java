package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Custom_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        FirefoxOptions option = new FirefoxOptions();
        option.addPreference("geo.enabled", false);
        option.addPreference("geo.provider.use_corelocation", false);

        driver = new FirefoxDriver(option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_02_Nopcomer() {
        driver.get("https://rode.com/en/support/where-to-buy");
    }

    @Test
    public void TC_03() {
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_AAA_Pattern {
    //Arrange
    //Setup//Initial Data/ Browser/Driver/Variable,...
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //Action
    @Test
    public void register() throws InterruptedException {
        // Action

        //Open Page

        //Fill data

        //Assert

    }
}

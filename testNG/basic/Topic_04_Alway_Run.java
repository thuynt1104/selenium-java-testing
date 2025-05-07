package basic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Alway_Run {
    //Arrange
    //Setup//Initial Data/ Browser/Driver/Variable,...
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        System.out.println("Pass Before Class");
    }
    //Action
    @Test
    public void register() throws InterruptedException {
        // Action

        //Open Page

        //Fill data

        //Assert
        System.out.println("Pass Test");

    }
    @AfterClass (alwaysRun = true)
    public void afterClass() {
        driver.quit();
        System.out.println("Pass Action and verify");
    }
}

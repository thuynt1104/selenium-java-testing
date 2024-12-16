package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_14_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver =  new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Checkbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(3000);

        By dualZoneCheckbox= By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(2000);

        //Verify selected
        if(!driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        //Verify unselected
        if (driver.findElement(dualZoneCheckbox).isSelected()) {
            driver.findElement(dualZoneCheckbox).click();
            Thread.sleep(2000);
        }

        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }


    @Test
    public void TC_02_Radiobutton() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        Thread.sleep(3000);

        By petrol2 = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(2000);

        //Verify selected
        if (!driver.findElement(petrol2).isSelected()) {
            driver.findElement(petrol2).click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(petrol2).isSelected());

    }
    @Test
    public void TC_03() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");
        Thread.sleep(2000);

        By summerText = By.xpath("//input[@id='mat-radio-2-input']");

        //Verify selected
        if (!driver.findElement(summerText).isSelected()) {
            driver.findElement(summerText).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(summerText).isSelected());
        Thread.sleep(2000);

        driver.get("https://material.angular.io/components/checkbox/examples");
        By checkOption = By.xpath("//div[@class='mdc-checkbox']//input[@id='mat-mdc-checkbox-0-input']");
        By indeterminateOption = By.xpath("//div[@class='mdc-checkbox']//input[@id='mat-mdc-checkbox-1-input']");

        if (!driver.findElement(checkOption).isSelected() && !driver.findElement(indeterminateOption).isSelected()) {
            driver.findElement(checkOption).click();
            driver.findElement(indeterminateOption).click();
            Thread.sleep(2000);
        }
        Assert.assertTrue(driver.findElement(checkOption).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateOption).isSelected());


        if (driver.findElement(checkOption).isSelected() && driver.findElement(indeterminateOption).isSelected()) {
            driver.findElement(checkOption).click();
            driver.findElement(indeterminateOption).click();
            Thread.sleep(2000);
        }
        Assert.assertFalse(driver.findElement(checkOption).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateOption).isSelected());

    }

    @Test
    public void TC_04_Select_All () throws InterruptedException {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        // 1. Select all checkbox
         for (WebElement checkbox : allCheckboxes) {
             if (!checkbox.isSelected()) {
                 checkbox.click();
             }
         }

         // 4. verify all check
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }
        // 2. Deselect all checkbox
        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.isSelected()) {
                checkbox.click();
            }
        }
        // 4. verify all check
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertFalse(checkbox.isSelected());
        }
        // 3. Select/Deselect 1 item in all
        for (WebElement checkbox : allCheckboxes) {
            if(checkbox.getDomAttribute("value").equals("Fainting Spells") && !checkbox.isSelected()) {
                    checkbox.click();
             }
        }
        // 4. verify all check
        for (WebElement checkbox : allCheckboxes) {
            if(checkbox.getDomAttribute("value").equals("Fainting Spells")){
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

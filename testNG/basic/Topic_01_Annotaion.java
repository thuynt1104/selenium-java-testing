package basic;

import org.testng.annotations.*;

public class Topic_01_Annotaion {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }
    @Test
    public void test() {
        System.out.println("test");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite");
    }
    @AfterTest
    public void afterTest() {
        System.out.println("After Test");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("After Class");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method");
    }
}

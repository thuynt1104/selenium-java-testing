package platformMobile;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Payment {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }
    @Test(groups = {"platformMobile"})
    public void User_01_CreateNewUser() {
        System.out.println("User_01_CreateNewUser");
    }
    @Test(groups = {"platformMobile"})
    public void User_02_EditNewUser() {
        System.out.println("User_01_CreateNewUser");
    }
    @Test(groups = {"platformMobile"})
    public void User_03_ViewNewUser() {
        System.out.println("User_01_CreateNewUser");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}

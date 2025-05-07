package platformWeb;

import org.testng.annotations.*;

public class Topic_01_User {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }
    @Test (groups = "tesst")
    public void User_01_CreateNewUser() {
        System.out.println("User_01_CreateNewUser");
    }
    @Test(groups = {"platformWeb", "test"})
    public void User_02_EditNewUser() {
        System.out.println("test_02_EditNewUser");
    }
    @Test(groups = {"platformWeb", "user"})
    public void User_03_ViewNewUser() {
        System.out.println("User_01_CreateNewUser");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}

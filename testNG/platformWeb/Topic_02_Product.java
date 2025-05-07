package platformWeb;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Product {

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class");
    }
    @Test(groups = {"platformWeb"})
    public void User_01_CreateNewProduct() {
        System.out.println("User_01_CreateNewProduct");
    }
    @Test(groups = {"platformWeb"})
    public void User_02_EditNewProduct() {
        System.out.println("User_01_CreateNewProduct");
    }
    @Test(groups = {"platformProduct"})
    public void User_03_ViewNewUser() {
        System.out.println("User_01_CreateNewProduct");
    }
    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
    }
}

package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Priority {
    //Nen order theo ten testcase
    @Test (priority = 1)
    public void register() {
        System.out.println("Register new Account");
    }
    @Test (priority = 2)
    public void login() {
        System.out.println("Login to System");
    }
    @Test (priority = 3)
    public void order() {
        System.out.println("Order Product");
    }
    @Test(priority = 4)
    public void payment() {
        System.out.println("Payment to System");
    }
}

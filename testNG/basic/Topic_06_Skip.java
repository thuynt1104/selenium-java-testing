package basic;

import org.testng.annotations.Test;

public class Topic_06_Skip {
    //Nen order theo ten testcase
    @Test
    public void register() {
        System.out.println("Register new Account");
    }
    @Test
    public void login() {
        System.out.println("Login to System");
    }
    @Test
    public void order() {
        System.out.println("Order Product");
    }
    @Test (enabled = false)
    public void payment() {
        System.out.println("Payment to System");
    }
}

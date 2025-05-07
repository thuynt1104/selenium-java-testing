package basic;

import org.testng.annotations.Test;

public class Topic_07_Description {
    //Nen order theo ten testcase
    @Test(description = "Jira1111-Register new Account")
    public void register() {
        System.out.println("Register new Account");
    }
    @Test(description = "Jira1112-Register new Account")
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

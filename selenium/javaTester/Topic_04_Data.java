package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Topic_04_Data {
    public static void main(String[] args) {
        String authenLink = "https://the-internet.herokuapp.com/basic_auth";
        String username = "admin";
        String password = "admin";

        String[] text = authenLink. split ("//");
        System.out.println(text[0]);
        System.out.println(text[1]);
        authenLink =  text[0] + "//" + username + ":" + password + "@" +  text[1];
        System.out.println(authenLink);

    }
}

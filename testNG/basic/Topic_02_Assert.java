package basic;

import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_02_Assert {
    @Test
    public void login() {
        //bat buoc kieu du lieu Boolean
        //Selenium: tien to la isZZZ : isDisplayed/isTrue/...
        //Ham tu define tra ve boolean
        //True: kiem tra ham tra ve la dung
        //Fail: khi can kiem tra du lieu tra ve la sai
        Assert.assertTrue(5 > 3);
        Assert.assertFalse(5 == 3);
        boolean status = 5 > 3;
        System.out.println(status);
        Assert.assertTrue(status);

        //Equals: kiem tra du lieu mong doi va thuc te giong nhau
        //2 ve cung 1 kieu du lieu
        //Kiem tra ve gia tri cua du lieu va kieu du lieu
        String studentName = "James";
        Assert.assertEquals(studentName, "james");

        Object name = "Nguyen";
        Assert.assertEquals(studentName, name);
    }
}

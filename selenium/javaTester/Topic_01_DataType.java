package javaTester;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Topic_01_DataType {
    // Bài toán là quản lý 1 công ty về IT
    // Thông tin về công ty: Tên/ Địa chỉ/ Mã số thuế/..
    // Có bao nhiêu phòng ban
    // Có bao nhiêu nhân viên
    // Thông tin về nhân viên: Tên/ Tuổi/ Địa chỉ/ Giới tính/ Mã số thuế/..
    // Giờ giấc làm việc
    // Lương/ thưởng

    // Tên người có dùng số để đặt tên?
    // Quản lý trường học/ khách sạn/ kho bãi
    // Công ty/ quán ăn/ bệnh viện/...

    // 1 kiểu dữ liệu sẽ được sử dụng cho 1 thông tin/ thuộc tính nào đó
    // Mỗi ngôn ngữ lập trình sẽ có quy ước các kiểu dữ liệu khác nhau

    // Java có hai nhóm kiểu dữ liệu
    // 1 - Kiểu dữ liệu nguyên thủy (Primitive Type)
    // 8 kiểu đại diện (chia ra gồm 4 nhóm)
    // Kiểu kí tự (đại diện cho 1 kí tự)
    char c = 'B';
    // Kiểu số nguyên (không có thập phân)
    byte bNumber = 10;
    short sNumber = 100;
    int iNumber = 70000;
    long lNumber = 1000000;
    // Kiểu số thực (có thập phân)
    float fNumber = 10.5f;
    double dNumber = 15.8d;
    // Kiểu logic
    boolean sex = true;
    // 2 - Kiểu dữ liệu tham chiêú (Reference Type)
    // Kiểu mảng (Array)
    // Mảng kiểu string
    String[] studentName = {"Nguyễn Thuys", "Nguyen Thi Nga", "Nguyen Phuong Tho"};
    int[] studentAge = {18, 18, 18};

    // Kiểu Object (đại diện cho các kiểu dữ liệu khác)
    // Đối tượng => chuyển đổi qua các kiểu dữ liệu khác
    Object studentAddress = "123 PO Box";
    Object employeeAge = 35;
    Object employeeSex = false;
    // Kiểu chuỗi (string)
    String name = "Thuy";
    String employeeNumber = "123456789";
    // Class
    FirefoxDriver ffDriver = new FirefoxDriver();
    // public class FirefoxDriver
    // Interface
    WebDriver driver = new ChromeDriver();
    // public interface Webdriver
    // Collection
    List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    ArrayList<String> studentCity = new ArrayList<String>();
}

package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_WebDriver_Wait_Implicit_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.chrome.driver",projectPath + "\\BrowserDriver\\chromedriver.exe");

		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		
		// Nó bị ảnh hưởng bởi timeout của implicit
		// 20s là thời gian chờ tối đa để tìm nó trong DOM
		// Nếu trong 3s đã thấy element thì bỏ qua 17s còn lại
		// Nếu ko tìm thấy Element trong timeut thì sẽ phụ thuộc vào findElement/FindElemnets sẽ cho ra result
		// Sau 0.5s sẽ tìm lại 1 lần

	}
	
	@Test
	public void TC01_FindElement() {
		
		WebElement  element = driver.findElement(By.xpath(""));
		
		//1.Tìm được 1 machhing node
		//- không cần chờ hết timeout -> Pass
		
		//2.Không tìm dc maching node
		//- chờ hết timeout -> though ra 1 ngoại lệ (exception) : NoSuchElementException
		//- Đánh fail testcase đó và ko chạy step tiếp theo nữa
		
		//3.Tìm được nhiều hơn 1 machhing node
		//- Sẽ thao tác với node đầu tiên -> ko quan tâm các node sau
	}
	@Test
  public void TC02_FindElements() {
		
		List<WebElement> listOfElements = driver.findElements(By.xpath(""));
		
		
		// 1.Tìm được 1 machhing node
		// - trả về list có 1 element
		
		// 2.Không tìm dc maching node
		// - Chờ hết timeout của implicit cơ chế tìm lại 0.5s 
		// - Chờ hết timeout -> ko fail , ko though exception 
		// - Chả về list rỗng
		
		// 3.Tìm được nhiều hơn 1 machhing node
		// - Chả về List chưa n Element
  }

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}



}

package webdriver;

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

public class Topic_Iframe_Frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver",".\\BrowserDriver\\geckodriver.exe");

		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		

	}
	
	@Test
	public void TC01_Iframe() {
		driver.get("https://kyna.vn/");
		
		// switch to iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
		
		// validate text in iframe
		String pageTitle = driver.findElement(By.xpath("//div[@class='lfloat']//a")).getText();
		System.out.println(pageTitle);
		
		// back to patent page
		driver.switchTo().defaultContent();
		
		// Validate đăng nhập button is enable
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Đăng nhập']")).isEnabled());
		
		
	}
	
	
  public void TC02_Frame() {
	  driver.get("https://netbanking.hdfcbank.com/netbanking/");
	  
	  // thẻ input nằm trong frame
	  // cần switch vào frame để sendheys
	  
	  driver.switchTo().frame(driver.findElement(By.name("login_page")));
	  
	  driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("Automation");
	  
		
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

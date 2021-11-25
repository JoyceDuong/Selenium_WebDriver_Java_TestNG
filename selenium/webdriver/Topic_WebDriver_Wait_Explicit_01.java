package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_WebDriver_Wait_Explicit_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver",projectPath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void TC_01_Visible() {
		
		// Hiển thì trên UI và Có trong HTML/DOM
		
	}
	@Test
	public void TC_02_Invisible() {
		
		// điều kiện là ko hiển thị trên UI
		
		// Case 1: Ko hiển thị và có trong DOM
		// Case 2 : ko hiển thị và ko có trong DOM
		
		// trong case 2 : do ko có trong DOM nên sẽ mất thời gian của implicit wait để tìm element nên sẽ mất tg lâu hơn
		// Bản chất wait nào cũng cần find element
	}
	@Test
	public void TC_03_Presence() {
		// Chỉ cần có trong DOM
		// CÓ hiển thị hoặc không
		
	}
	@Test
	public void TC_04_Staleness() {
		// Element bị update trong DOM nên sau khi update sẽ ko còn là element trước
		// có thể coi là 1 trường hợp của invisible
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

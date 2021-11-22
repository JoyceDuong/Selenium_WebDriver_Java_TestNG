package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_Handle_Window_Tabs {
	WebDriver driver;
	String url;

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	
	public void TC_01_ID() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// get Window ID
		String parentWindowID = driver.getWindowHandle();
		System.out.println(" parentWindowID :" + parentWindowID);

		// Tab B : Googgle page
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		switchToWindowByID(parentWindowID);

		String googleID = driver.getWindowHandle();
		System.out.println(" googleID :" + googleID);

		// switch back laij
		switchToWindowByID(googleID);
	}
	@Test
	public void TC_02_Title() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// click Google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByTitle("Google");
		url = driver.getCurrentUrl();
		System.out.println("Google url : " + url);

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		url = driver.getCurrentUrl();
		System.out.println("Selenium url : " + url);

		// click fACEBOOK
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		
		switchToWindowByTitle("Facebook");
		url = driver.getCurrentUrl();
		System.out.println("Facebook url : " + url);

	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void switchToWindowByID(String windowPageID) {
		// chỉ dùng cho 2 tab/Window
		// get ra tất cả ID của Window/Tab
		Set<String> allWindowIDs = driver.getWindowHandles();
		
		for (String window : allWindowIDs) {
			// kiểm tra khác với parent ID
			if (!window.equals(windowPageID)) {
				driver.switchTo().window(window);
			}
		}
	}
	public void closeAllWindowWithoutParent(String parentID) {
		
		// get ra tất cả ID của Window/Tab
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String window : allWindowIDs) {
			// kiểm tra khác với parent ID
			if (!window.equals(parentID)) {
				driver.switchTo().window(window);
				
				//close curent window
				driver.close();
			}
		}
		// Switch qua parent window
		driver.switchTo().window(parentID);
	}

	public void switchToWindowByTitle(String expectedWindowPageID) {
		// dùng cho > = 2 tab/Window
		// get ra tất cả ID của Window/Tab
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String window : allWindowIDs) {
			// cho switch qua truoc
			driver.switchTo().window(window);
			sleepInSecond(2); // để load dc page completely

			// kiểm tra sau
			String actuaTitle = driver.getTitle().trim();
			if (actuaTitle.equals(expectedWindowPageID)) {
				break;
			}

		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

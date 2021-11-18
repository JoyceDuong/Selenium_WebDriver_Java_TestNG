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

public class Topic_Popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver",".\\BrowserDriver\\geckodriver.exe");

		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		

	}
	

	public void TC01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		// Fixed Pop-up luôn có trong DOM , ko bị mất trong DOM khi ko hiển thị
		
		// Validate Pop-upo ko hiển thị
		
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());
		
		// click vào login button
		
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(2);
		
		// validate Pop-up displayed
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1']")).isDisplayed());
		
		// tiếp theo tương tác như bt
	}

	public void TC02_Random_Popup_In_DOM() {
		
		driver.get("https://blog.testproject.io/");
		WebElement popup= driver.findElement(By.cssSelector("div#mailch-bg"));
		
		// nếu popup xuát hiện thì close 
		if (popup.isDisplayed()) {
			
			driver.findElement(By.cssSelector("close-mailch")).click();;
			
		}
		
		// nếu popup ko xuất hiện thì chuyển sang step 3
		
		// với site này cần wait page is ready mới pass dc
	}
	@Test
  public void TC03_Random_Popup_Not_In_DOM() {
		
		//step 1
		driver.get("https://shopee.vn/");
	
		
		//step 2: nếu popup hiển thị thì clsoe
		// ko hiển thị thì qua step 3
		
		By shopeePopupBy = By.cssSelector(".shopee-popup__container");
		List<WebElement> shopeePopupElement = driver.findElements(shopeePopupBy);
		
		if (shopeePopupElement.size()>0) {
			System.out.println("Pop-up hiển thị rồi -> Close please !");
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		}
		
		//Step 3 : tìm kiếm
	
		driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys("Macbook Pro");
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

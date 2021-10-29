package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Web_Browser_Command {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();

	}

	@Test
	public void TC01_Web_Browser_Command() {
		
		                // Nội Dung Chính
		// Mở ra Url                                   /*
		// Đóng 1 tab đang Active ( handle Tab/Window)
		// Đóng trình duyệt                            /*
		// Lấy ra ID của window/Tab đang active
		// Lấy ra tất cả ID của các window/Tab đang có
		// Nhảy đến wIndow/tab nào đó                  /*
		// Tìm ra elemenr với locator nào đó           /*
		// tìm ra tất cả element với locator nào đó    /*
		// Trả về Url của page hiện tại                /*
		// Trả về HTML của page hiện tại
		// Trả về title của page hiện tại
		// Get/ xóa cookie của page 
		// Get ra log của browser
		// Chờ cho vc tìm element(find element)        /*
		// Chờ cho page dc load thành công
		// Chờ cho 1 script dc execute thành công
		// Mở browser full màn hình
		// Maximize màn hình                           /*
		// Lấy ra vị trí hiện tại của browser
		// Back to page
		// Forward to page
		// Refresh page
		// Keep history 
		// Đi tới 1 window/Tab Alert Frame/iframe       /*
//........................................................................................................................................

		
		// Mở ra Url
		driver.get("");
		
		// Đóng 1 tab đang Active ( handle Tab/Window)
		driver.close();
		
		// Đóng trình duyệt
		driver.quit();
		
		// Lấy ra ID của window/Tab đang active
		String MesID =driver.getWindowHandle();
		
		// Lấy ra tất cả ID của các window/Tab đang có
		
		Set<String> MesIDs = driver.getWindowHandles();
		
		// Nhảy đến wIndow/tab nào đó                  /*
		driver.switchTo().window(MesID);
		
		// Tìm ra elemenr với locator nào đó           /*
		WebElement emailTextbox = driver.findElement(By.id(""));
		
		// tìm ra tất cả element với locator nào đó    /*
		List<WebElement> textbox = driver.findElements(By.className(""));
		
		// Trả về Url của page hiện tại                /*
		String Url =driver.getCurrentUrl();
		
		// Trả về HTML của page hiện tại
		driver.getPageSource();
		
		// Trả về title của page hiện tại
		driver.getTitle();
		
		// Get/ xóa cookie của page 
		
		driver.manage().deleteAllCookies();
		// Get ra log của browser
		driver.manage().logs().getAvailableLogTypes();
		
		// Chờ cho vc tìm element(find element)        /*
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// Chờ cho page dc load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ cho 1 script dc execute thành công
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Mở browser full màn hình
		driver.manage().window().fullscreen();
		
		// Maximize màn hình                           /*
		driver.manage().window().maximize();
		
		// Lấy ra vị trí hiện tại của browser
		driver.manage().window().getPosition();
		
		
		// Back to page
		driver.navigate().back();
		
		// Forward to page
		driver.navigate().forward();
		
		 
		// Keep history 
		driver.navigate().to("");
		
		// Đi tới 1 window/Tab Alert Frame/iframe       /*
		driver.switchTo().alert();
		driver.switchTo().window("");
		driver.switchTo().frame("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

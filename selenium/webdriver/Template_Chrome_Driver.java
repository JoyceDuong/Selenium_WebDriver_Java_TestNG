package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Template_Chrome_Driver {
	WebDriver driver;

	@Test
	public void TC_01() {
	}

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}

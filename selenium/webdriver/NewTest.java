package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {
	WebDriver driver; 
	@BeforeClass
	public void beforeClass() {
		
	}
	@Test
	public void OpenFirefox() {
		
		driver = new FirefoxDriver();
		
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void byID() throws InterruptedException {
		driver.findElement(By.className("search-box-text ")).sendKeys("duongminhhieudeptrai");
		Thread.sleep(2000);
		
	}
	
	@Test
	public void byClass() throws InterruptedException {
		driver.findElement(By.className("button-1 ")).click();
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

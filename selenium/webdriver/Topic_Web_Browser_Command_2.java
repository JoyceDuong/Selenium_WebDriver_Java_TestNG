package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Web_Browser_Command_2 {
	WebDriver driver;

	By myAccountLink = By.xpath("//div[@class='footer']//a[@title='My Account']");

	@Test
	public void TC_01() {
		
		driver.get("http://live.techpanda.org/");

		driver.findElement(myAccountLink).click();
		String myAccountTitle = driver.getTitle();
		
		Assert.assertEquals(myAccountTitle, "Customer Login");

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String createUrl = driver.getCurrentUrl();

		Assert.assertEquals(createUrl, "http://live.techpanda.org/customer/account/create/");

	}

	@Test
	public void TC_02() {
		
		driver.get("http://live.techpanda.org/");

		driver.findElement(myAccountLink).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.navigate().back();
	
		// Assert Account Title
		String myAccountTitle = driver.getTitle();
		
		Assert.assertEquals(myAccountTitle, "Customer Login");
		driver.navigate().forward();
		
		// Assert Create account Url
		
		String createUrl = driver.getCurrentUrl();

		Assert.assertEquals(createUrl, "http://live.techpanda.org/customer/account/create/");


		


	}

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

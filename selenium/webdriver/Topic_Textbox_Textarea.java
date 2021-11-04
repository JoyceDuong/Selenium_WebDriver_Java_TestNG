package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Topic_Textbox_Textarea {
	WebDriver driver;
	String loginPageUrl,userID,password; 

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
	}
	
	@Test
	public void TC_01_Register() {
		
		loginPageUrl = driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.name("emailid")).sendKeys("Minhhieu03@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	@Test
	public void TC_02_Login() {
		
		driver.get(loginPageUrl);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		
		driver.findElement(By.name("btnLogin")).click();
		
	}
	@Test
	public void TC_01_New_Customer() {
		
	}
	@Test
	public void TC_01_Edit_Customer() {
		
	}



	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}

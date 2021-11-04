package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Web_Element_Password_Validate {
	WebDriver driver;
	

	@Test
  public void TC01() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("minhhieu@gmail.net");
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("Joyce Duong");
		
		// nhập số
		driver.findElement(By.id("new_password")).sendKeys("1");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		// nhập chữ thường		
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("a");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed()); //
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		// nhập chữ hoa
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("A");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		// nhập kí tự đặc biệt
		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("@");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		// nhập lơn hơn 8 kí tự

		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("1234567879");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		
		// nhập Valid password

		driver.findElement(By.id("new_password")).clear();
		driver.findElement(By.id("new_password")).sendKeys("Minhhieu@123");
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		
		// validate checkbox after clicking successful
		driver.findElement(By.xpath("//input[@id='marketing_newsletter']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='marketing_newsletter']")).isSelected());
  }

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver",".\\BrowserDriver\\geckodriver.exe");

		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.mailchimp.com/signup/");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}



}

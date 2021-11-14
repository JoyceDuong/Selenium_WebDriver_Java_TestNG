package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Alert_Authen_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");

		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	
	public void TC01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Validate alert text

		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept alert
		alert.accept();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked an alert successfully");

	}
	
	public void TC02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Validate alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Accept alert
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked: Ok");
		
		// Cancel alert
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Validate alert text

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Accept alert
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked: Cancel");
	}
	@Test
	public void TC03_Prompt_Alert() {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Validate alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String data = "Duong Minh Hieu";
				// Sendkey to alert
				alert.sendKeys(data);
		
		// Accept alert
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You entered: " + data);
		
		// Candel alert
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Validate alert text
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String data1 = "Duong Minh Hieu";
		// Sendkey to alert
		alert.sendKeys(data);
		
		// Cancel alert
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You entered: null");
	}

	public void TC04_Authentication_Alert() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
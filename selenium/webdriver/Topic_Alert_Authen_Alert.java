package webdriver;

import java.io.IOException;
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
	String projectPath = System.getProperty("user.dir");
	String authenChromeAutoIT =projectPath + "\\autoIT\\authen_chrome.exe";
	String authenFireFoxAutoIT =projectPath + "\\autoIT\\authen_firefox.exe";
	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", projectPath + "\\BrowserDriver\\geckodriver.exe");

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

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Ok");

		// Cancel alert
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Wait + Switch to Alert ( gộp luôn )
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		// Validate alert text

		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Accept alert
		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");
	}

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

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + data);

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

		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: null");
	}


	public void TC04_Authentication_Alert() {
		
		String userName = "admin";
		String password = "admin";
		String url = "http://"+userName+":"+password+"@"+"the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	@Test
	public void TC05_Authentication_Alert() {
		
		// handle authen sau khi click vafo 1 link to move to other page
		// Currently work only with Chrome
		
		String userName = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getLinkByUserPass(basicAuthLink, userName, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public void TC06_Authentication_Alert_AutoIT() throws IOException {
		
		// work for chrome
		String userName = "admin";
		String password = "admin";
		String url = "http:the-internet.herokuapp.com/basic_auth";
		
		
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[]{authenFireFoxAutoIT,userName,password});
		} else  {
			Runtime.getRuntime().exec(new String[]{authenChromeAutoIT,userName,password});

		}
		
		
		driver.get(url);
		
	}
	public String getLinkByUserPass(String link , String user , String pass) {
		String[] links = link.split("//");
		return links[0] + "//" + user + pass + "@" + links[1];
		
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
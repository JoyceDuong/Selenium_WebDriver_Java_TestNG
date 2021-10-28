package webdriver;

import java.util.Random;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Xpath_Technical_02 {
	WebDriver driver;
	String name, email, re_email, pass, re_pass, phone;

	By Myaccount = By.xpath("(//li[@class='first']/a[text()='My Account'])[2]");
	By Emailtextbox = By.xpath("//input[@id='email']");
	By Passtextbox = By.xpath("//input[@id='pass']");
	By Loginbt = By.xpath("//button[@id='send2']");

	// Create random email
	public String getRandomEmail() {
		Random rand = new Random();
		return "testing" + rand.nextInt(99999) + "@live.com";
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/");

		driver.findElement(Myaccount).click();

	}

	@BeforeMethod
	public void beforeMethod() {
		// refresh before each Tcs
		driver.navigate().refresh();
	}

	@Test
	public void TC01_Login_Empty_Filed() {

		driver.findElement(Loginbt).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");

	}

	@Test
	public void TC02_Login_Invalid_Email() {

		driver.findElement(Emailtextbox).sendKeys("123546@123455");
		driver.findElement(Passtextbox).sendKeys("123546");

		driver.findElement(Loginbt).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test
	public void TC03_Login_Pass_lessthan_6() {

		driver.findElement(Emailtextbox).sendKeys("minhhieu@gmail.com");
		driver.findElement(Passtextbox).sendKeys("12356");

		driver.findElement(Loginbt).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC04_Login_Incorrect_Account() {

		driver.findElement(Emailtextbox).sendKeys("minhhieu@gmail.com");
		driver.findElement(Passtextbox).sendKeys("123567");

		driver.findElement(Loginbt).click();

		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(),
				"Invalid login or password.");

	}

	@Test
	public void TC05_Create_New_Account() {

		driver.findElement(By.xpath("//a[@class='button']")).click();

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Hieu");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Minh");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Duong");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("MInhhieu123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("MInhhieu123");

		driver.findElement(By.xpath("//button[@class='button' and @title='Register']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering')]")).getText(),
				"Thank you for registering with Main Website Store.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

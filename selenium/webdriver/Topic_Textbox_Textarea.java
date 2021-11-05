package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_Textbox_Textarea {
	WebDriver driver;
	String loginPageUrl, userID, password;
	String nameTextbox, gender, dateOfBirth, address, city, state, pin, phoneNumber, email;
	
	By nameTextboxBy = By.name("name");
	By femaleBy = By.name("rad1");
	By dateOfBirthBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By phoneBy = By.name("telephoneno");
	By emailBy = By.name("emailid");
	By passwordBy = By.name("password");
	
	
	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		
		// data for new customer
		nameTextbox = " Joyce Duong";
		gender = " female";
		dateOfBirth = "";
		address = "70\nDinh Quan\nHaNoi";
		city = "Ha Noi";
		state = "Bac Tu Liem";
		pin = "123456";
		phoneNumber = "0364785945";
		email = getRandomEmail();
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

		// validate userID displayed
		Assert.assertEquals(driver.findElement(By.xpath("//tr[@class='heading3']/td")), "Manger Id : " + userID);

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// insert data for new customer
		
		driver.findElement(nameTextboxBy).sendKeys(nameTextbox);
		driver.findElement(femaleBy).click();
		driver.findElement(dateOfBirthBy).sendKeys(dateOfBirth);
		driver.findElement(addressBy).sendKeys(address);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phoneNumber);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(email).sendKeys(city);
	

	}

	@Test
	public void TC_01_Edit_Customer() {

	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "Joyceduong"+rand.nextInt(9999)+"@gmail.com";

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

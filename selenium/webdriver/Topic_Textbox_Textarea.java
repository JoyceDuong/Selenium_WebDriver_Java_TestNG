package webdriver;
 
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_Textbox_Textarea {
	WebDriver driver;
	String loginPageUrl, userID, password, customerID;
	String nameTextbox, gender, dateOfBirthInput, dateOfBirthOutput, addressInput, addressOutput, city, state, pin,
			phoneNumber, email;
	String addressEditInput, addressEditOutput, cityEdit, stateEdit, pinEdit, phoneNumberEdit, emailEdit;
	JavascriptExecutor jsExecutor;

	By nameTextboxBy = By.name("name");
	By femaleBy = By.xpath("//input[@value='f']");
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

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		// data for new customer
		nameTextbox = "Joyce Duong";
		gender = "female";
		dateOfBirthInput = "01/01/1990";
		dateOfBirthOutput = "1990-01-01";
		addressInput = "70\nDinh Quan\nHaNoi";
		addressOutput = "70 Dinh Quan HaNoi";
		city = "Ha Noi";
		state = "Bac Tu Liem";
		pin = "123456";
		phoneNumber = "0364785945";
		email = getRandomEmail();

		// Data for Edit

		addressEditInput = "20\nPhuBinh\nThaiNguyen";
		addressEditOutput = "20 PhuBinh ThaiNguyen";
		cityEdit = "Thai Nguyen";
		stateEdit = "Phu Binh";
		pinEdit = "654321";
		phoneNumberEdit = "0396476029";
		emailEdit = getRandomEmail();
	}

	@Test
	public void TC_01_Register() {

		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
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

		// validate userID displayed
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// insert data for new customer

		driver.findElement(nameTextboxBy).sendKeys(nameTextbox);
		driver.findElement(femaleBy).click();

		// xóa thuôc tính datetime picker
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthBy));
		
		driver.findElement(dateOfBirthBy).sendKeys(dateOfBirthInput);
		driver.findElement(addressBy).sendKeys(addressInput);
		driver.findElement(cityBy).sendKeys(city);
		driver.findElement(stateBy).sendKeys(state);
		driver.findElement(pinBy).sendKeys(pin);
		driver.findElement(phoneBy).sendKeys(phoneNumber);
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);

		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// validate new customer information

		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),
				"Customer Registered Successfully!!!");

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				nameTextbox);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();

	}

	@Test
	public void TC_01_Edit_Customer() {

		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		
	// Edit data
		driver.findElement(addressBy).sendKeys(addressEditInput);
		driver.findElement(cityBy).sendKeys(cityEdit);
		driver.findElement(stateBy).sendKeys(stateEdit);
		driver.findElement(pinBy).sendKeys(pinEdit);
		driver.findElement(phoneBy).sendKeys(phoneNumberEdit);
		driver.findElement(emailBy).sendKeys(emailEdit);
		driver.findElement(By.name("sub")).click();

	
	}

	public String getRandomEmail() {
		Random rand = new Random();
		return "Joyceduong" + rand.nextInt(9999) + "@gmail.com";

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

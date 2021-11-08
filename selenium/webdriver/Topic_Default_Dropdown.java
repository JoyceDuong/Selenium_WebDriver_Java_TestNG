package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstname, lastname, dateOfBirthDay, dateOfBirthMounth, dateOfBirthYear, email, companyName, password;

	@Test
	public void TC01_Default_Dropdown() {

		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.cssSelector("#gender-male")).click();
		driver.findElement(By.cssSelector("#FirstName")).sendKeys(firstname);
		driver.findElement(By.cssSelector("#LastName")).sendKeys(lastname);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));

		// Select 1 item A

		select.selectByVisibleText(dateOfBirthDay);

		/*
		 * // Bỏ chọn item A select.deselectByVisibleText("");
		 * 
		 * // Kiểm tra dropdown này có phải multiple hay ko
		 * 
		 * select.isMultiple(); // trả về true/failse nên cần assert
		 * 
		 * // kiểm tra xem đã chọn đúng item A chưa
		 * 
		 * select.getFirstSelectedOption().getText(); // validate dùng assertequal
		 * 
		 * // Get ra tổng số item trong dropdown ;à bao nhiêu
		 * 
		 * select.getOptions().size()
		 * 
		 */

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));

		select.selectByVisibleText(dateOfBirthMounth);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));

		select.selectByVisibleText(dateOfBirthYear);

		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.cssSelector("#Company")).sendKeys(companyName);
		driver.findElement(By.cssSelector("#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys(password);
		driver.findElement(By.cssSelector("#register-button")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Your registration completed']")).getText(),
				"Your registration completed");

		driver.findElement(By.cssSelector(".ico-account")).click();

		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName")).getAttribute("value"), firstname);
		Assert.assertEquals(driver.findElement(By.cssSelector("#LastName")).getAttribute("value"), lastname);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthDay);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthMounth);

		select = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthYear);

		Assert.assertEquals(driver.findElement(By.cssSelector("#Email")).getAttribute("value"), email);
		Assert.assertEquals(driver.findElement(By.cssSelector("#Company")).getAttribute("value"), companyName);
	}

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		firstname = "Hieu";
		lastname = "Duong";
		dateOfBirthDay = "24";
		dateOfBirthMounth = "September";
		dateOfBirthYear = "1998";
		email = "Joyceduong" + getRandomNumber() + "@gmail.com";
		companyName = "mokirawa";
		password = "Minhhieu123";
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

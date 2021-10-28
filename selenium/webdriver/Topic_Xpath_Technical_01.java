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

public class Topic_Xpath_Technical_01 {
	WebDriver driver;
	String name, email, re_email, pass, re_pass, phone;
	/*
	 * public void sleepInSecond(long timeoutInSecond) { try {
	 * Thread.sleep(timeoutInSecond * 1000); } catch (InterruptedException e) {
	 * e.printStackTrace(); }
	 */
	By insertname = By.xpath("//input[@id='txtFirstname']"); // technical 01
	By insertemail = By.xpath("//div[@class='field']/input[@name='txtEmail']"); // technical 02 from patent
	By re_insertemail = By.xpath("//input[contains(@id,'tCEmail')]"); // technical03 tuong doi
	By insertpass = By.xpath("//input[starts-with(@name,'txtPass')]"); // tech 04 starts-with
	By re_insertpass = By.xpath("//input[contains(@name,'txtCPassword')]");
	By insertphone = By.xpath("//input[@id='txtPhone' and contains(@class,'text form')]"); // ket hop

	@BeforeClass
	public void beforeClass() {
		
		//Gecko Driver
		System.setProperty("webdriver.gecko.driver",".\\BrowserDriver\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// declare correct value
		name = "Duongminhhieu";
		email = "mokirawa99@gmail.com";
		re_email = "mokirawa99@gmail.com";
		pass = "123456abc";
		re_pass = "123456abc";
		phone = "0367424444";

	}

	@BeforeMethod
	public void beforeMethod() {
		// refresh before each Tcs
		driver.navigate().refresh();
	}

	@Test
	public void TC01_Login_Empty_Filed() {

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		// Assert text error
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC02_Login_Invalid_Email() {

		// insert value
		driver.findElement(insertname).sendKeys(name);
		driver.findElement(insertemail).sendKeys("466sdfg5646");
		driver.findElement(re_insertemail).sendKeys("466sdfg5646");
		driver.findElement(insertpass).sendKeys(pass);
		driver.findElement(re_insertpass).sendKeys(re_pass);
		driver.findElement(insertphone).sendKeys(phone);

		// click on register button
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp");
		// Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
		// "Email nhập lại không đúng");

	}

	@Test
	public void TC03_Login_Incorrrect_confirm_email() {

		driver.findElement(insertname).sendKeys(name);
		driver.findElement(insertemail).sendKeys("email");
		driver.findElement(re_insertemail).sendKeys("minhhieu@gmail.com");
		driver.findElement(insertpass).sendKeys(pass);
		driver.findElement(re_insertpass).sendKeys(re_pass);
		driver.findElement(insertphone).sendKeys(phone);

		// click on register button
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC04_Login_Pass_Lessthan_6() {

		driver.findElement(insertname).sendKeys(name);
		driver.findElement(insertemail).sendKeys("email");
		driver.findElement(re_insertemail).sendKeys("re_email");
		driver.findElement(insertpass).sendKeys("12345");
		driver.findElement(re_insertpass).sendKeys("12345");
		driver.findElement(insertphone).sendKeys(phone);

		// click on register button
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//label[contains(text(),'Mật khẩu phải có') and @id='txtPassword-error']"))
						.getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC05_Login_Incorrrect_confirm_Pass() {

		driver.findElement(insertname).sendKeys(name);
		driver.findElement(insertemail).sendKeys("email");
		driver.findElement(re_insertemail).sendKeys("re_email");
		driver.findElement(insertpass).sendKeys("pass");
		driver.findElement(re_insertpass).sendKeys("1234567");
		driver.findElement(insertphone).sendKeys(phone);

		// click on register button
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC06_Login_Invalid_Phone() {

		driver.findElement(insertname).sendKeys(name);
		driver.findElement(insertemail).sendKeys("email");
		driver.findElement(re_insertemail).sendKeys("re_email");
		driver.findElement(insertpass).sendKeys("pass");
		driver.findElement(re_insertpass).sendKeys("pass");
		driver.findElement(insertphone).sendKeys("abcjhu");

		// click on register button
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập con số");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

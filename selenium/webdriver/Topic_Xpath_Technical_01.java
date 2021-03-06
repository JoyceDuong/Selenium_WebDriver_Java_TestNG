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
	public void sleepInSecond(long timeoutInSecond) { try {
	 hread.sleep(timeoutInSecond * 1000); } catch (InterruptedException e) {
	 e.printStackTrace(); }
	 */
	By insertname = By.xpath("//input[@id='txtFirstname']"); // technical 01
	By insertemail = By.xpath("//div[@class='field']/input[@name='txtEmail']"); // technical 02 from patent
	By re_insertemail = By.xpath("//input[contains(@id,'tCEmail')]"); // technical03 tuong doi
	By insertpass = By.xpath("//input[starts-with(@name,'txtPass')]"); // tech 04 starts-with
	By re_insertpass = By.xpath("//input[contains(@name,'txtCPassword')]");
	By insertphone = By.xpath("//input[@id='txtPhone' and contains(@class,'text form')]"); // ket hop

	@BeforeClass
	public void beforeClass() {

		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");

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
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui l??ng nh???p h??? t??n");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui l??ng nh???p email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui l??ng nh???p l???i ?????a ch??? email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui l??ng nh???p m???t kh???u");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui l??ng nh???p l???i m???t kh???u");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui l??ng nh???p s??? ??i???n tho???i.");

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

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui l??ng nh???p email h???p");
		// Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),
		// "Email nh???p l???i kh??ng ????ng");

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

		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nh???p l???i kh??ng ????ng");
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
				driver.findElement(By.xpath("//label[contains(text(),'M???t kh???u ph???i c??') and @id='txtPassword-error']"))
						.getText(),
				"M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"M???t kh???u ph???i c?? ??t nh???t 6 k?? t???");

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

		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "M???t kh???u b???n nh???p kh??ng kh???p");

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

		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui l??ng nh???p con s???");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

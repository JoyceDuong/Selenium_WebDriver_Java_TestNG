package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Default_Button_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	WebDriverWait explicitWait;

	// @Test
	public void TC01_Default_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");

		By loginForm = By.xpath("//ul[@id='popup-login-tab_list']//a[text()='Đăng nhập']");
		By loginButton = By.xpath("//button[@class='fhs-btn-login']");

		driver.findElement(loginForm).click();

		// validate button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		// Send valid data
		driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("minhhieu@gmail.com");
		driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Minhhieu123");
		// validate button is Enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		// get background color then validate

		String backgroundColor = driver.findElement(loginButton).getCssValue("background-color");

		Assert.assertEquals(Color.fromString(backgroundColor).asHex().toUpperCase(), "#C92127");

		driver.navigate().refresh();
		driver.findElement(loginForm).click();

		// remove disable attribute of login button

		jsExcutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));

		driver.findElement(loginButton).click();

		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//div[@class='fhs-input-box checked-error']//div[text()='Thông tin này không thể để trống']"))
				.isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath(
				"//div[@class='fhs-input-box fhs-input-display checked-error']//div[text()='Thông tin này không thể để trống']"))
				.isDisplayed());

	}

	@Test
	public void TC01_Default_Checkbox() {

		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//span[@class='kd-loader']"))));

		WebElement dual_zoneCheckbox = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding::input[@id='eq5']"));

		// Select then Validate
		dual_zoneCheckbox.click();
		Assert.assertTrue(dual_zoneCheckbox.isSelected());

		// Deselect then validate
		dual_zoneCheckbox.click();
		Assert.assertFalse(dual_zoneCheckbox.isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		
		WebElement petrol_147KW = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding::input[@id='engine3']"));
		if (petrol_147KW.isSelected()) {
			System.out.println("Expected Element is selected");
		} else {
			petrol_147KW.click();		
		} 
		
		Assert.assertTrue(petrol_147KW.isSelected());

	}
	public void checkToElement(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}
	public void uncheckToElement(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

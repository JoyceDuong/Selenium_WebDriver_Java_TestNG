package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExcutor;
	WebDriverWait explicitWait;

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

	//@Test
	public void TC01_Custom_Checkbox() {

		// cách 1 : Dùng Span để click và input để verify -> sẽ phải define 2 locator

		// cách 2 : dùng javascript để click vào thẻ input bi ẩn -> chỉ cần khai báo 1
		// locator

		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkTextboxBy = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminate = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

		checkToElementByJS(checkTextboxBy);
		checkToElementByJS(indeterminate);

		Assert.assertTrue(driver.findElement(checkTextboxBy).isSelected());
		Assert.assertTrue(driver.findElement(indeterminate).isSelected());

		uncheckToElementByJS(checkTextboxBy);
		uncheckToElementByJS(indeterminate);

		Assert.assertFalse(driver.findElement(checkTextboxBy).isSelected());
		Assert.assertFalse(driver.findElement(indeterminate).isSelected());

	}

	@Test
	public void TC02_Custom_Checkbox_Radio_GoogleDoc() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		// verify bằng isSelected() chỉ dùng cho thẻ input -> ko dùng dc trong bài này

		By cantho = By.xpath("//div[@data-value='Cần Thơ']");

		// Verify deselected
		// cách 1
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false'] ")).isDisplayed());
		// cách 2
		Assert.assertEquals(driver.findElement(cantho).getAttribute("aria-checked"), "false");

		driver.findElement(cantho).click();

		// Verify selected
		// cách 1
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true'] ")).isDisplayed());
		// cách 2
		Assert.assertEquals(driver.findElement(cantho).getAttribute("aria-checked"), "true");

		driver.navigate().refresh();
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(1);			
		}
		for (WebElement checkbox : checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		
		}
	}

	public void checkToElementByJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExcutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}

	public void uncheckToElementByJS(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExcutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}

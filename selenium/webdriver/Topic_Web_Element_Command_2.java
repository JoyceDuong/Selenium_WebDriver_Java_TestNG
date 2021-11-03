package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_Web_Element_Command_2 {
	WebDriver driver;
	By email = By.xpath("//input[@id='mail']");
	By under18Radio = By.xpath("//div[@class='container']/input[@id='under_18']");
	By education = By.xpath("//textarea[@id='edu']");
	By imageCaption5 = By.cssSelector("div.figcaption h5");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	// Disable
	By password = By.cssSelector("input[id='password'][placeholder='Textbox is disabled']");
	By disableRadioAge = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole03 = By.cssSelector("#job3");
	By slider02 = By.cssSelector("#slider-2");

	// Selected

	By javaCheckbox = By.xpath("//input[@id='java']");

	@Test
	public void TC_01_IsDisplayed() {

		if (Is_Displayed(email)) {
			senKeyToElenemt(email, "minhieu@gmail.com");
		}
		if (Is_Displayed(under18Radio)) {
			clickToElenemt(under18Radio);
		}
		if (Is_Displayed(education)) {
			senKeyToElenemt(education, "Dai hoc cong nghiep HN");
		}
		Assert.assertFalse(Is_Displayed(imageCaption5));
	}

	@Test
	public void TC_02_IsEnable() {
		driver.navigate().refresh();


		// Validate Enable
		Assert.assertTrue(Is_Enable(email));
		Assert.assertTrue(Is_Enable(education));
		Assert.assertTrue(Is_Enable(jobRole01));
		Assert.assertTrue(Is_Enable(jobRole02));
		Assert.assertTrue(Is_Enable(slider01));

		// Validate Disable
		Assert.assertFalse(Is_Enable(password));
		Assert.assertFalse(Is_Enable(biography));
		Assert.assertFalse(Is_Enable(jobRole03));
		Assert.assertFalse(Is_Enable(slider02));
		Assert.assertFalse(Is_Enable(disableRadioAge));

	}

	@Test
	public void TC_02_Is_Selected() {
		
		driver.navigate().refresh();

		// Click to element
		driver.findElement(under18Radio).click();
		driver.findElement(javaCheckbox).click();

		// Validate selected

		Assert.assertTrue(Is_Selected(under18Radio));
		Assert.assertTrue(Is_Selected(javaCheckbox));

		// Click to uncheck element
		driver.findElement(javaCheckbox).click();

		// Validate selected

		Assert.assertFalse(Is_Selected(javaCheckbox));
	}

	public boolean Is_Displayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println(by + "Is display");
			return true;
		} else {
			System.out.println(by + "Is not display");
			return false;
		}
	}

	public boolean Is_Enable(By by) {
		WebElement element = driver.findElement(by);

		if (element.isEnabled()) {
			System.out.println(by + "Is Enable");
			return true;

		} else {
			System.out.println(by + "Is not Enable");
			return false;
		}
	}

	public boolean Is_Selected(By by) {
		WebElement element = driver.findElement(by);

		if (element.isSelected()) {
			System.out.println(by + "Is Selected");
			return true;

		} else {
			System.out.println(by + "Is not Selected");
			return false;
		}
	}

	public void senKeyToElenemt(By by, String Value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(Value);
	}

	public void clickToElenemt(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

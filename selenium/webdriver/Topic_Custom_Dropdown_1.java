package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Custom_Dropdown_1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDriver\\geckodriver.exe");

		driver = new FirefoxDriver();

		// Wait để apply trạng thái của element visible , invisible, precence ,
		// clickable
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		jsExcutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parentBy = By.cssSelector("#number-button");
		By childBy = By.cssSelector("#number-menu  div");

		selectItemInDropdownList(parentBy, childBy, "19");
		sleepInSecond(2);
		Assert.assertTrue(Is_Displayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")));

		selectItemInDropdownList(parentBy, childBy, "5");
		sleepInSecond(2);
		Assert.assertTrue(Is_Displayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemInDropdownList(parentBy, childBy, "10");
		sleepInSecond(2);
		Assert.assertTrue(Is_Displayed(
				By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='10']")));

	}

	// @Test
	public void TC02_ReactJS() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parentBy = By.xpath("//i[@class='dropdown icon']");
		By childBy = By.xpath("//div[@class='visible menu transition']//span");

		selectItemInDropdownList(parentBy, childBy, "Matt");
		sleepInSecond(2);
		Assert.assertTrue(Is_Displayed(By.xpath("//div[@role='alert' and text()='Matt']")));
	}

	// @Test
	public void TC03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By parentBy = By.cssSelector("li.dropdown-toggle");
		By childBy = By.cssSelector("ul.dropdown-menu a");

		selectItemInDropdownList(parentBy, childBy, "Third Option");

		Assert.assertTrue(
				Is_Displayed(By.xpath("//li[@class='dropdown-toggle' and contains(string(),'Third Option')]")));

	}

	//@Test
	public void TC04_Angular() {

		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		By parentBy = By.cssSelector("div#local span[role='listbox']");
		By childBy = By.xpath("//ul[@id='games_options']");
		selectItemInDropdownList(parentBy, childBy, "Cricket");

	}
	@Test
		public void TC05_Editable() {
		
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		
		By parentBy= By.cssSelector("#default-place input");
		By childBy= By.cssSelector("div#basic-place li");
		selectItemInEditableDropdown(parentBy, childBy, "volvo");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible selected']")).getText(), "Volvo");
			
		}

	public void selectItemInDropdownList(By parentBy, By childBy, String expectedTextItem) {
		
		// 1 click vào element để xổ ra các items
		driver.findElement(parentBy).click();
		
		// chờ cho tất cả các element đc load thành công trong DOM/HTML
		// reprence
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// 3.nếu item mình cần chọn nằm trong view thì click vào
		// 4.nếu item mình cần chọn không nằm trong view ( ko nhìn thấy ) thì croll dơn
		// rồi click vào
		
		List<WebElement> allItems = driver.findElements(childBy);
		
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
					
				} else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
			
		}
	}
	public void selectItemInEditableDropdown(By parentBy, By childBy, String expectedTextItem) {

		// 1 click vào element để xổ ra các items
		driver.findElement(parentBy).sendKeys(expectedTextItem);
		
		// chờ cho tất cả các element đc load thành công trong DOM/HTML
		// reprence

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		// 3.nếu item mình cần chọn nằm trong view thì click vào
		// 4.nếu item mình cần chọn không nằm trong view ( ko nhìn thấy ) thì croll dơn
		// rồi click vào

		List<WebElement> allItems = driver.findElements(childBy);

		for (WebElement item : allItems) {
			if (item.getText().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();

				} else {
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
			}

		}
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}

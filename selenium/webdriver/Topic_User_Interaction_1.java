package webdriver;

import org.testng.annotations.Test;

import jdk.internal.org.jline.utils.Colors;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_User_Interaction_1 {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExcutor;

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();

		action = new Actions(driver);
		jsExcutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}
	
	public void TC_01_Hover_Element() {

		driver.get("http://www.myntra.com/");

		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();

		WebElement dresses = driver.findElement(By.xpath("//a[@data-reactid='364']"));
		
		dresses.click();
		
		String newUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(newUrl, "https://www.myntra.com/kids?f=Categories%3ADresses%3A%3AGender%3Aboys%20girls%2Cgirls&plaEnabled=false");

	}


	public void TC_02_Hover_Tooltip() {

		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.cssSelector("#age"))).perform();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");

	}
	
	public void TC_03_Click_And_Hold() {

		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));
		
		action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
		// clickand hold : Chọn và giữ vào 1 item
		// movetoElement di chuyển đến element
		// release thả chuột
		
		Assert.assertEquals(driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']")).size(), 4);

	}
	
	public void TC_04_Click_And_Hold_Random() {

		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> allItems = driver.findElements(By.cssSelector("#selectable>li"));
		
		// nhấn phím control and click
		action.keyDown(Keys.CONTROL).click(allItems.get(0)).click(allItems.get(5)).click(allItems.get(8)).perform();
		
		// Thả phím control
		action.keyUp(Keys.CONTROL).perform();
		

	}


	public void TC_05_Double_Click() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		// sẽ move tới element trước khi double click
		// chỉ move dc tới khoảng nhìn thấy
		
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
	}

	public void TC_06_Double_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		
		// verify visible and hover
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		
	}
	@Test
	public void TC_07_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		action.dragAndDrop(driver.findElement(By.cssSelector("div.demo-section>div#draggable")), driver.findElement(By.cssSelector("div.demo-section>div#droptarget"))).perform();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.demo-section>div#droptarget")).getText(), "You did great!");
		
		
		String backgroundColor = driver.findElement(By.cssSelector("div.demo-section>div#droptarget")).getCssValue("background-color");

		Assert.assertEquals(Color.fromString(backgroundColor).asHex(), "#03a9f4");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}

package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Template_Chrome_Driver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	
	String dalatName = "dalat.jpg";
	String hanoiName = "hanoi.jpg";
	String hueName = "hue.jpg";

	String dalatPath = projectPath + "\\uploadFile\\" + dalatName;
	String hanoiPath = projectPath + "\\uploadFile\\" + hanoiName;
	String huePath = projectPath + "\\uploadFile\\" + hueName;

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 20);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	// chỉ sendkey vào input có type = file
	// Có thể sử dụng mọi trường hợp mọi OS
	
	public void TC_01_Sendkey_Single_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dalatPath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiPath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(huePath);
		sleepInSecond(1);

		// Validate load
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hanoiName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hueName + "']")).isDisplayed());

	}

	public void TC_01_Sendkey_Multiple_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Load cả 3 file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dalatPath + "\n" + hanoiPath + "\n" + huePath);
		sleepInSecond(1);

		// Validate load
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hanoiName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hueName + "']")).isDisplayed());

	}

	@Test
	public void TC_03_Real_TC() {
		driver.get("https://gofile.io/?t=uploadFiles");

		// Load cả 3 file
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dalatPath + "\n" + hanoiPath + "\n" + huePath);
		sleepInSecond(1);
		
		// Wait success bar disappear
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress "))));
		
		// Wait spinner load
		explicitWait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div#rowLoading i.fa-spinner"))));
		
		// Wait uccessful message displyed
		
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]"))));

		
		
		
		// Validate successful message displyed
		Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'Your files have been successfully uploaded')]")).isDisplayed());
		
		// Getlink and navigate to link
		
		driver.get(driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).getAttribute("href"));
		
		// Validate load successfully
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + dalatName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + hanoiName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='contentName' and text()='" + hueName + "']")).isDisplayed());

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

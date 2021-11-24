package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_Upload_File_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	String dalatName = "dalat.jpg";
	String hanoiName = "hanoi.jpg";
	String hueName = "hue.jpg";

	String dalatPath =projectPath + "\\uploadFile\\" + dalatName;
	String hanoiPath = projectPath + "\\uploadFile\\" + hanoiName;
	String huePath = projectPath + "\\uploadFile\\" + hueName;
	
	String autoITChromeSinglePath = projectPath + "\\autoIT\\" + "chromeUploadOneTime.exe";
	String autoITFirefoxSinglePath = projectPath + "\\autoIT\\" + "firefoxUploadOneTime.exe";
	String autoITChromeMultiplePath = projectPath + "\\autoIT\\" + "chromeUploadMultiple.exe";
	String autoITFirefoxMultiplePath = projectPath + "\\autoIT\\" + "firefoxUploadMultiple.exe";
	

	@BeforeClass
	public void beforeClass() {

		// Chrome Driver
		System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	// AutoIT chỉ :
	// chạy trên window
	// đôi khi cùng site nhưng các browser khác nhau cần bắt element khác nhau
	// Java Robot CHỉ dùng cho window
	
	public void TC_01_AutoIT_Single_File() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Click vào load file button
		//driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		clickToElementByJS(".btn-success");
		// Sử dụng autoIT để load file
		Runtime.getRuntime().exec(new String[] {autoITChromeSinglePath,dalatPath});
		sleepInSecond(2);
		System.out.println("Load xong image  dalat");
		
		
		// Click vào load file button
		//driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		clickToElementByJS(".btn-success");
		// Sử dụng autoIT để load file
		Runtime.getRuntime().exec(new String[] {autoITChromeSinglePath,hanoiPath});
		sleepInSecond(2);
		System.out.println("Load xong image hanoi");
		
		// Click vào load file button
		//driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		clickToElementByJS(".btn-success");
		// Sử dụng autoIT để load file
		Runtime.getRuntime().exec(new String[] {autoITChromeSinglePath,huePath});
		sleepInSecond(2);
		System.out.println("Load xong image hue");
	}
	
	public void TC_02_AutoIT_Multiple_File() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {autoITChromeMultiplePath,dalatPath,hanoiPath,huePath});
		} else if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {autoITFirefoxMultiplePath,dalatPath,hanoiPath,huePath});
		}
		
		// Chus ý đến số lượng chữ trong khung tối đa 259 kí tự -> Quá là failse
	}
	@Test
	public void TC_03_Java_Robot() throws AWTException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		// Specific the file location with extension
		StringSelection select = new StringSelection(hanoiPath);
		
		// Copy path của file rồi lưu vào clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		
		// Bật Open file dialog
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();
		sleepInSecond(2);
		
		Robot robot = new Robot();
		sleepInSecond(2);
		
		// Press Enter (optional)		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Press Ctrl V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		// Release Ctrl V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		
		// Press Enter to load and close window
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
		
		
		
		
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}
	public WebElement getElement(String locator) {
		return driver.findElement(By.cssSelector(locator));
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

package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_Web_Element_Command_1 {
	WebDriver driver;

	@Test
  public void TC01_Name() {
		
		// Web Element / Method/ API
		WebElement element = driver.findElement(By.id(""));
		
		// Xóa dữ liệu trong textbox , textearea , dropdown
		element.clear();
		
		// Nhập Dữ liệu vào editable field
		
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		// Click on button/link/radio/checkbox
		
		element.click();
		
		// trả về dư liệu nằm trong attribute
		
		element.getAttribute("Placeholder"); // usefull for verifing textbox
		
		element.getCssValue("background-color");
		
		// làm về GUI
		
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//take screenshot -> attach vào report
		element.getScreenshotAs(OutputType.FILE);
		element.getScreenshotAs(OutputType.BASE64);
		
		
		// tên thẻ HTML
		// Dung cho by.id,class,name,css
		// đầu ra step này là đầu vào step kia
		
		element.getTagName();
		
		String saveButtonTagName = element.getTagName();
		
		driver.findElement(By.xpath("//" + saveButtonTagName + "[@name='email']"));
		
		// Get Text
		
		element.getText();
		
		// Element có hiển thị hay không ( nhìn và thao tác dc )
		
		element.isDisplayed();
		
		// verify có thao tác dc hay ko 
		
		element.isEnabled();
		
		//kt đã dc chọn hay chưa
		
		element.isSelected();
		
		
		
		
		
		
		
  }

	@BeforeClass
	public void beforeClass() {
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver",".\\BrowserDriver\\geckodriver.exe");

		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}



}

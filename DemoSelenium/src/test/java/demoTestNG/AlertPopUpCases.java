package demoTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Anupam
 * Problem Statement : In case you are working on a web site and
 * on that sudden pop up or alert comes then this solution will help to solve
 * that problem.
 * 
 */
public class AlertPopUpCases {
	public WebDriver driver;

	@Test
	public void testAlertPopups() {
		// System.setProperty("webdriver.chrome.driver","path of chrome driver");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		driver.get("https://www.way2automation.com/way2auto_jquery/frames-and-windows.php#load_box");
		driver.manage().window().maximize();

		// click on Alert menu
		driver.findElement(By.xpath("//*[@id='main-nav']//child::a[text()='Alert']")).click();

		// swtich inside iframe of - simple Alert
		WebElement insideSimpleAlertFrame = driver.findElement(By.xpath("//*[@src='alert/simple-alert.html']"));

		driver.switchTo().frame(insideSimpleAlertFrame);

		driver.findElement(By.xpath("//*[text()='Click the button to display an alert box:']")).click();

		// accept or clicking Ok
		driver.switchTo().alert().accept();

		// test input alert
		driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//*[text()='Input Alert']")).click();

		// switch input alert iframe
		WebElement insideInputAlertFrame = driver.findElement(By.xpath("//*[@src='alert/input-alert.html']"));

		driver.switchTo().frame(insideInputAlertFrame);

		// click input box - enter data in alert

		driver.findElement(By.xpath("//*[text()='Click the button to demonstrate the Input box.']")).click();

		// Close or Dissmiss
		driver.switchTo().alert().sendKeys("Anupam");
		driver.switchTo().alert().accept();

		//
		WebElement uiData = driver.findElement(By.xpath("//*[@id='demo']"));

		System.out.println(uiData.getText());
		driver.switchTo().defaultContent();
		driver.close();

	}
}

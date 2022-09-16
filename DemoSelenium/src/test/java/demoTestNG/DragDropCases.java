package demoTestNG;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Anupam 
 * To perform drag and drop operation use this solution.
 *
 */
public class DragDropCases {
	public WebDriver driver;

	@Test
	public void dragDropFeatureAutomation() {
		// System.setProperty("webdriver.chrome.driver","path of chrome driver");

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		// launch website
		driver.get("https://www.way2automation.com/way2auto_jquery/frames-and-windows.php#load_box");
		driver.manage().window().maximize();

		// click on interation meanu using actions using mouse hover

		WebElement interationMenu = driver
				.findElement(By.xpath("//*[@id='main-nav']//descendant::a[text()='Interaction']"));
		WebElement draggableSubMenu = driver
				.findElement(By.xpath("//*[@id='main-nav']//descendant::a[text()='Draggable']"));
		Actions actObj = new Actions(driver);

		// mouse hover on interation menu
		actObj.moveToElement(interationMenu).perform();

		// click on draggable subMenu
		draggableSubMenu.click();

		System.out.println("Sucessfully clicked on draggable");

		// to perfrom drag and drop first switch to frame
		WebElement insideFrame = driver.findElement(By.xpath("//*[@src='draggable/default.html']"));

		driver.switchTo().frame(insideFrame);
		System.out.println("inside frame");

		Actions act = new Actions(driver);

		WebElement dragMe = driver.findElement(By.xpath("//*[@id='draggable']//child::p[text()='Drag me around']"));

		act.dragAndDropBy(dragMe, 60, 40).build().perform();
		System.out.println("Sucessfully completed drag and drop");

		driver.close();
		driver.quit();

	}
}

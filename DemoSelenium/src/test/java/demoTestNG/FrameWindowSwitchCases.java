package demoTestNG;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameWindowSwitchCases {
	public WebDriver driver;

	@Test
	public void testGoogle() {
		// facing issue hence using web driver manager
		// System.setProperty("webdriver.chrome.driver", "E:\\July 2022
		// -Automation\\Selenium Project\\DemoSelenium\\driver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.manage().window().maximize();

		driver.get("https://www.way2automation.com/way2auto_jquery/frames-and-windows.php#load_box");
		System.out.println("website launched sucessfully");

		// click on open new seprate window

		driver.findElement(By.xpath("//*[text()='Open Seprate New Window']")).click();
		System.out.println("Sucessfully clicked on new seprate window");

		// url of parent window

		String parentWindow = driver.getWindowHandle();

		// click on link - open new seprate window

		WebElement insideFrame = driver.findElement(By.xpath("//*[@src='frames-windows/defult2.html']"));

		driver.switchTo().frame(insideFrame);
		System.out.println("Swtiched inside frame");

		driver.findElement(By.xpath("//*[@class='farme_window']//following::a[text()='Open New Seprate Window']"))
				.click();
		System.out.println("Clicked the link to open new window");

		Set<String> newWindow = driver.getWindowHandles();

		Iterator<String> I = newWindow.iterator();

		while (I.hasNext()) {
			String childWindow = I.next();
			if (!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println("Switched to Child window");
				System.out.println("Title of child window is " + driver.getTitle());
				driver.close();
			}

		}
		driver.switchTo().window(parentWindow);

		driver.close();
		driver.quit();

	}
}

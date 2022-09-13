package basefunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFactory 
{
	public WebDriver launchBrowser() 
	{
		System.setProperty("webdriver.chrome.driver", "D:\\2022\\Software\\chromedriver_win32 (1)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;

		/*	2. Firefox driver
		//	System.setProperty("webdriver.gecko.driver", "D:\\2022\\Software\\geckodriver-v0.30.0-win64\\geckodriver.exe");
		//	WebDriver driver = new FirefoxDriver();
		//	driver.manage().window().maximize();
		//	return driver;
3. Edge driver
		//	System.setProperty("webdriver.edge.driver", "D:\\2022\\Software\\edgedriver_win64\\msedgedriver.exe");
		//	WebDriver driver = new EdgeDriver();
		//	driver.manage().window().maximize();
		//	return driver;*/
	}
}
		
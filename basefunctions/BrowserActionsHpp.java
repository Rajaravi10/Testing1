package basefunctions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import basefunctions.BrowserActionsHpp;
//import basefunctions.BrowserFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BrowserActionsHpp {

	public void navigateUrl(WebDriver driver) 
	{
		driver.get("https://uatisando.ecentric.co.za/hpptester");
	}
	public void clearAndEnter(WebDriver driver, By locator, String input, String message) 
	{
		//wait for element
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(input);
			System.out.println("Successfully entered text "+ message);
			
		}catch (Exception e) {
			System.out.println("Failure to enter text "+ message);
			Assert.fail("Failure to enter text "+ message);
			//Assert.assertEquals("abcd", "");
		}
	}
	public void Click1(WebDriver driver, By locator,String message)  
	{
		try {
			waitForClickable(driver,locator);
			driver.findElement(locator).click();
			//WebElement inputBox1 = driver.findElement(By.name(sRequestButton));
//			inputBox1.click();
			System.out.println("Successfully Clicked "+ message);
		}catch (Exception e) {
			System.out.println("Failure to Click "+ message);
//			Assert.fail("Failure to Click "+ message);
			e.printStackTrace();
		}
	}
	public void Clickjs(WebDriver driver, By locator,String message)  
	{
		try {
			WebElement ele = waitForClickable(driver,locator);
			JavascriptExecutor js =(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()", ele);
			//driver.findElement(locator).click();
			//WebElement inputBox1 = driver.findElement(By.name(sRequestButton));
//			inputBox1.click();
			System.out.println("Successfully Clicked "+ message);
		}catch (Exception e) {
			System.out.println("Failure to Click "+ message);
			Assert.fail("Failure to Click "+ message);
			e.printStackTrace();
		}
	}
	public String getElementText(WebDriver driver, By locator,String message) throws InterruptedException 
	{
		try {
			WebElement ele = waitForClickable(driver,locator);
			System.out.println("Successfully Get element text "+ message);
			return ele.getText();			
		}catch (Exception e) {
			System.out.println("Failure to Get element text "+ message);
			Assert.fail("Failure to Get element text "+ message);
			e.printStackTrace();
			return null;
		}
	}
	public void ClickEleWithoutWait(WebDriver driver, By locator,String message) throws InterruptedException 
	{
		try {
			Thread.sleep(3000);
			driver.findElement(locator).click();
			//WebElement inputBox1 = driver.findElement(By.name(sRequestButton));
//			inputBox1.click();
			System.out.println("Successfully Clicked ");
		}catch (Exception e) {
			System.out.println("Failure to Click");
			Assert.fail("Failure to Click");
		e.printStackTrace();
		}
	}
	public WebElement waitForClickable(WebDriver driver, By locator) 
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}catch (Exception e) {
			
			return null;
		}
	}
	public void switchToFrame(WebDriver driver, By locator, String message)
	{
		try {
			WebElement ele = waitForClickable(driver, locator);
			driver.switchTo().frame(ele);
			System.out.println("Successfully switch frame "+ message);
		}catch (Exception e) {
			System.out.println("Failure to switch frame "+ message);
			Assert.fail("Failure to switch frame "+ message);
		}
	}
	public void switchToDefault(WebDriver driver)
	{
		try {
//			WebElement ele = waitForClickable(driver, locator);
			WebDriver driver1 = driver.switchTo().defaultContent();
			System.out.println("Successfully switched to Default Frame ");
			//return driver1;
		}catch (Exception e) {
			System.out.println("Failed to switch to Default Frame  ");
			Assert.fail("Failed to switch to Default Frame  ");
			//return null;
		}
	
	}
	public void selectValfromDDUsingText(WebDriver driver, By locator,String input, String message)
	{
		try {
			WebElement ele = waitForClickable(driver, locator);
			Select select = new Select(ele);
			select.selectByVisibleText(input);
			System.out.println("Successfully Select vlaue from dropdown "+ message);
		}catch (Exception e) {
			System.out.println("Failure to Select vlaue from dropdown "+ message);
			Assert.fail("Failure to Select vlaue from dropdown "+ message);
		}
	
	}
	/*7-8-2022*/
	public void numofframes(WebDriver driver) {
		int ele2 =driver.findElements(By.tagName("iframe")).size();
		
		System.out.println("Number of iframes in the page: "+ ele2);
	}
		/*12-8-2022*/
	public void EnterRefNumbers(WebDriver driver,By locator, String input, String message) {
		
		try {
			Random random = new Random();
			int randomNumber = random.nextInt(1000000000);
			String refNumber = Integer.toString(randomNumber);
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(refNumber);
			System.out.println("Successfully entered text "+ message);
			System.out.println(refNumber);
/*how to check which data type is that variable belongs*/
			System.out.println(refNumber.getClass().getName());
		}catch (Exception e){
			System.out.println("Failure to enter the Reference number  ");
		}
	}
	public void RefNumbersWithDate(WebDriver driver,By locator, String input, String message) {
		
		try {
		Date thisDate = new Date();
		SimpleDateFormat randomNumber = new SimpleDateFormat("yyyyMMddhhmmss");
		String refNumber = randomNumber.format(thisDate);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(refNumber);
		System.out.println("Successfully entered text "+ message);
		System.out.println(refNumber);
/*how to check which data type is that variable belongs*/
		System.out.println(refNumber.getClass().getName());
	}catch (Exception e){
		System.out.println("Failure to enter the Reference number  ");
	}
	}
	/*13-8-2022*/
	public String getResponseText(WebDriver driver, By locator,String message) throws InterruptedException 
	{
		try {
			WebElement ele = waitForClickable(driver,locator);
			System.out.println("Successfully Got element text "+ message);
			return ele.getText();			
		}catch (Exception e) {
			WebElement ele1 = waitForClickable(driver,locator);
			System.out.println("Failure to Get element text "+ message);
			return ele1.getText();
		//			Assert.fail("Failure to Get element text "+ message);
		//			e.printStackTrace();
		//			return null;
		}
	}
		/*15-8-2022*/
	public void AutoRefNumbers(WebDriver driver,By locator, String input, String message) {
		
		try {
			Random random = new Random();
			int randomNumber = random.nextInt(100000000);
			String refNumber = Integer.toString(randomNumber);
			String refNumber2 = "Isnd"+refNumber;
			driver.findElement(locator).clear();
			driver.findElement(locator).sendKeys(refNumber2);
			System.out.println("Successfully entered "+ message);
			System.out.println(refNumber2);
/*how to check which data type is that variable belongs*/
//			System.out.println(refNumber.getClass().getName());
		}catch (Exception e){
			System.out.println("Failure to enter the Reference number  ");
		}
	}
}
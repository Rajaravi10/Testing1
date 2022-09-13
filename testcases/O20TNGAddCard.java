package testcases;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basefunctions.BrowserActionsHpp;
import basefunctions.BrowserFactory;
import basefunctions.ExcelUtils;
import repository.HPPpage;
public class O20TNGAddCard {
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	
// Launch browser method
	@BeforeMethod
	public void beforeMethod() {
	BrowserFactory factory1 = new BrowserFactory();
	driver = factory1.launchBrowser();
	
	BrowserActionsHpp actions = new BrowserActionsHpp();
	actions.navigateUrl(driver);

	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
}
	@DataProvider
    public Object[][] dataProvi(){
    	
    	int i=1;
    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,0);
		String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,1);
		String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","CNP",i,2);
		String name = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,3);
		String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,4);
		String EMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,5);
		String Eyear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",i,6);
		
		Object data[][]={{marchantId,MerchantKey,Url,name,CardNumber,EMonth,Eyear}};
		return data;
    }
    @Test (dataProvider = "dataProvi")
	public void ADDcardRond(String marchantId,String MerchantKey,String Url,String name,
			String CardNumber,String EMonth,String Eyear) throws InterruptedException 
		{
		BrowserActionsHpp actions = new BrowserActionsHpp();
		actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
		actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
		actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
		Thread.sleep(2000);
		actions.Click1(driver, HPPpage.btn_AddCard,"Add Card");
		actions.clearAndEnter(driver, HPPpage.NameOnCard, name,"Name on CArd");
		actions.clearAndEnter(driver, HPPpage.CardNumber, CardNumber,"Card Number");
		actions.clearAndEnter(driver, HPPpage.EMonth, EMonth,"EMonth");
		actions.clearAndEnter(driver, HPPpage.Eyear, Eyear,"Eyear");
		actions.Click1(driver, HPPpage.btn_Addcard,"btn AddCard");
		//==============This Worked Page=================
		Thread.sleep(10000);
		String ref= actions.getElementText(driver, HPPpage.txt_RReference,"MReference");
		System.out.println(ref);
		}
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.SUCCESS) {
			excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",1,7,"Pass");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","Addcard",1,7,"Fail");
		}
		driver.quit();
	}
}

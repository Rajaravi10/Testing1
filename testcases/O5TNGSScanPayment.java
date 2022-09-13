package testcases;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import basefunctions.BrowserActionsHpp;
import basefunctions.BrowserFactory;
import basefunctions.ExcelUtils;
import basefunctions.RefNumber;
import repository.HPPpage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class O5TNGSScanPayment {
	
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	RefNumber RetrefNumber = new RefNumber();
	String ref1;
/*Launch browser method*/ 	
	@BeforeMethod
	public void beforeMethod() 
	{
		BrowserFactory factory1 = new BrowserFactory();
		driver = factory1.launchBrowser();
	
		BrowserActionsHpp actions = new BrowserActionsHpp();
		actions.navigateUrl(driver);
	
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);	
	}
	
	@DataProvider
	 public Object[][] dataProvi()
	 {
	    	int i=1;
	    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",i,0);
			String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",i,1);
			String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",i,2);
			String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",i,3);
			String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",i,4);
//			String MerReference = excelUtils.readDataBasedOnRowAndCell("\\TestData\\CardsTesting.xlsx","SScan",i,4);
			String referenceNum = RetrefNumber.refNumber;
			Object data[][]={{marchantId,MerchantKey,Url,Amount,UserID, referenceNum}};
			return data;
	    }
	
//	@DataProvider
//	 public Object[][] readAllData()
//	 {
//		 return excelUtils.readAllData(".\\TestData\\CardsTesting.xlsx","SScan");
//	    	
//	    }
	@Test (dataProvider = "dataProvi")
//	@Test (dataProvider = "readAllData")
	public void SnapScanTran(String marchantId,String MerchantKey,String Url,String Amount,String UserID, String referenceNum
			) throws InterruptedException 
		{
    	BrowserActionsHpp actions = new BrowserActionsHpp();
		actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
		actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
		actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
		actions.clearAndEnter(driver, HPPpage.txt_Amount, Amount,"Amount");
		actions.clearAndEnter(driver, HPPpage.txt_UserID, UserID,"UserID");
		Thread.sleep(2000);
		actions.Clickjs(driver, HPPpage.chkbx_GenRuntime,"Gen Runtime checkbox");
		actions.AutoRefNumbers(driver, HPPpage.txt_MerReference, referenceNum,"MerReference");
		actions.Click1(driver, HPPpage.btn_submit,"Submit");
		String ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
		System.out.println("Reference of Isando Snapscan transaction: "+ref1);
	
		actions.Click1(driver, HPPpage.radbtn_SScan,"Snapscan radio button");
		actions.Click1(driver, HPPpage.btn_continue,"ContinuePAY");
		Thread.sleep(4000);	
/*Snap scan Result page*/
/*getElementText*/
		Thread.sleep(10000);
		String ref= actions.getElementText(driver, HPPpage.txt_RReference,"MReference");
		System.out.println(ref);
	}
	@AfterMethod
	public void afterMethod(ITestResult result) 
	{
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",1,5,ref1);
		}else if(result.getStatus() == ITestResult.FAILURE) {
			excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","SScan",1,5,ref1);
		}
		driver.quit();
	}	

}
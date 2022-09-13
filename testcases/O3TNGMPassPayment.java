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

public class O3TNGMPassPayment {
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	RefNumber RetrefNumber = new RefNumber();
	String ref1;
	// Launch browser method	
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
		    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPass",i,0);
				String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPass",i,1);
				String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPass",i,2);
				String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPass",i,3);
				String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPass",i,4);
//				String MerReference = excelUtils.readDataBasedOnRowAndCell("\\TestData\\CardsTesting.xlsx","MPass",i,4);
				String referenceNum = RetrefNumber.refNumber;
				Object data[][]={{marchantId,MerchantKey,Url,Amount,UserID,referenceNum}};
				return data;
		    }

		@Test (dataProvider = "dataProvi")
		public void MasterPasstran(String marchantId,String MerchantKey,String Url,String Amount,String UserID
				,String referenceNum) throws InterruptedException 
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
			actions.Click1(driver, HPPpage.radbtn_mpass,"Masterpass radio button");
			ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
			System.out.println("Reference of Isando Masterpass transaction: "+ref1);
			actions.Click1(driver, HPPpage.btn_continue,"ContinuePAY");
			Thread.sleep(3000);	
//getElementText
			Thread.sleep(45000);
		}
		@AfterMethod
		public void afterMethod(ITestResult result) 
		{
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\TestData\\CardsTesting.xlsx","MPass",1,4,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\TestData\\CardsTesting.xlsx","MPass",1,4,ref1);
			}
			driver.quit();
		}
}

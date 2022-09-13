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

public class O2TNGMPGSPayment {
	
	WebDriver driver ;
	ExcelUtils excelUtils = new ExcelUtils();
	RefNumber RetrefNumber = new RefNumber();
//	static int resultRowNum;
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
		    	String marchantId = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,0);
				String MerchantKey = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,1);
				String Url = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,2);
				String Amount = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,3);
				String UserID = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,4);
				String CardName = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,5);
				String CardNumber = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,6);
				String ExpMonth = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,7);
				String ExpYear = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,8);
				String cvv = excelUtils.readDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",i,9);
				String referenceNum = RetrefNumber.refNumber;
				Object data[][]={{marchantId,MerchantKey,Url,Amount,UserID,referenceNum,CardName,CardNumber, ExpMonth, 
					ExpYear, cvv}};
				return data;
		    }
		@Test (dataProvider = "dataProvi")
		public void MPGSpayment(String marchantId,String MerchantKey,String Url,String Amount,String UserID, String referenceNum,  String CardName, 
				String CardNumber, String ExpMonth, String ExpYear, String cvv
				) throws InterruptedException 
			{
	    	BrowserActionsHpp actions = new BrowserActionsHpp();
			actions.clearAndEnter(driver, HPPpage.txt_MerchantGUID, marchantId,"MerchantGUID");
			actions.clearAndEnter(driver, HPPpage.txt_MerchantKey, MerchantKey,"MerchantKey");
			actions.clearAndEnter(driver, HPPpage.txt_Url, Url,"Url");
			actions.clearAndEnter(driver, HPPpage.txt_Amount, Amount,"Amount");
			actions.clearAndEnter(driver, HPPpage.txt_UserID, UserID,"UserID");
			actions.ClickEleWithoutWait(driver, HPPpage.cur_Botswana, "clicked on currency Botswana");
			Thread.sleep(2000);
			actions.Clickjs(driver, HPPpage.chkbx_GenRuntime,"Gen Runtime checkbox");
			actions.AutoRefNumbers(driver, HPPpage.txt_MerReference, referenceNum,"MerReference");
			actions.Click1(driver, HPPpage.btn_submit,"Submit");
			ref1= actions.getElementText(driver, HPPpage.RefNumoftran,"Reference number in tran");
			System.out.println("Reference of MPGS transaction: "+ref1);
			actions.clearAndEnter(driver, HPPpage.txt_CardName, CardName,"CardName");
			actions.clearAndEnter(driver, HPPpage.txt_CardNumber, CardNumber,"CardNumber");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpMonth, ExpMonth,"ExpMonth");
			actions.selectValfromDDUsingText(driver, HPPpage.drp_ExpYear, ExpYear,"ExpYear");
			actions.clearAndEnter(driver, HPPpage.mpgs_cvv, cvv,"CVV");
			actions.Click1(driver, HPPpage.btn_PayNow,"PAYNOW");
//			Thread.sleep(3000);	
/*MPGS screen*/
			actions.numofframes(driver);
			actions.switchToFrame(driver, HPPpage.frame5, "MPGS Fram - 5");
			actions.Click1(driver, HPPpage.mpgs_submit,"MPGS Submit");
			driver.switchTo().parentFrame();
			Thread.sleep(3000);	
		}
		@AfterMethod
		public void afterMethod(ITestResult result) 
		{
			if(result.getStatus() == ITestResult.SUCCESS) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",1,10,ref1);
			}else if(result.getStatus() == ITestResult.FAILURE) {
				excelUtils.writeDataBasedOnRowAndCell(".\\IsandoData\\IsandoDailyReport.xlsx","MPGS",1,10,ref1);
			}
			driver.quit();
		}
}
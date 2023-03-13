package AakashVeloity;


import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.pages.HomePage2;
import com.crm.qa.pages.LoginPage2;

import Utils.Utility;
import browser.Base;


public class LoginPageTestAakash extends Base{
    
	WebDriver driver;
    LoginPage2 loginpage;
	HomePage2 homepage;
	SoftAssert soft;
	int testId;	
	static ExtentTest test;
	static ExtentHtmlReporter reporter;
	
    @Parameters ("browserName")
	
	@BeforeTest
	public void launchBrowser(String browser) {
    	reporter = new ExtentHtmlReporter("test-output/ExtendReport/Extent.html");
		ExtentReports extend = new ExtentReports();
		extend.attachReporter (reporter);
		if (browser.equals("Chrome")) {
			driver =openChromeBrowser();
		}
		if (browser.equals("Firefox")) {
			driver =openFirefoxBrowser();
		}
		if (browser.equals("Edge")) {
			driver = openEdgeBrowser();
		}
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();

	}
	
	@BeforeClass
	public void createPOMObject() {
		loginpage = new LoginPage2(driver);
		homepage = new HomePage2 (driver);
	}
	@BeforeMethod
	public void SetUp() throws EncryptedDocumentException, IOException, InterruptedException {
		
		//initializeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		loginpage.validateLoginePageTitle();
		loginpage.validateLoginPageURL();
		
		String userName= Utility.newfetchDataFromExcel("LogineData", 1	, 0);
		loginpage.sendUserName(userName);
		
		String password= Utility.newfetchDataFromExcel("LogineData", 1	, 1);
        loginpage.sendpassword(password);
		loginpage.validateLoginbtText();
		loginpage.Clicklogin();
		soft = new SoftAssert();
	}
	
	@Test (priority =0)
	public void loginBtText() throws IOException {
		testId= 101;
		
		String text = loginpage.validateLoginbtText();
		soft.assertEquals(text, "Login");
		soft.assertAll();
		Utility.CaptureScreenShot(driver, testId);
	}
	@Test (priority=1)
	public void loginpageTitleTest()
	{
		testId= 102;

		String title =loginpage.validateLoginePageTitle();
		soft.assertEquals( title,"Let's Shop");
		
		soft.assertAll();
	}
	
	@Test (priority=2)
	public void loginPageURL() {
		
		testId= 103;

		String url = loginpage.validateLoginPageURL();
		soft.assertEquals(url, "https://rahulshettyacademy.com/client/auth/login");	
		soft.assertAll();
		}
	
  //@DataProvider
	//public void getTestData() {
	//	Utility.newfetchDataFromExcel(sheetName, int rownum,int cellnum);
	//	return data; 
//	}
	
	
	
	//@Test
	//public void logoTest() {
		//boolean flag = loginpage.validateLogo();
		//Assert.assertTrue(flag);
	//}
	
	
  
	
   @AfterMethod
   public void logout(ITestResult result) throws IOException, InterruptedException {
	   if (ITestResult.FAILURE==result.getStatus())
	   {
		   Utility.CaptureScreenShot(driver, testId);
	   }
	   homepage.clickLogout();
   }
   @AfterClass
	public void clearObjects() {
		loginpage = null;
		homepage = null;
	}
	
   @AfterTest
  	public void CloseBrowser() {
  		driver.quit();
  		driver = null;
  		System.gc();
  	}
}

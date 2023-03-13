package AakashVeloity;

import java.io.IOException;
import java.time.Duration;

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
import com.crm.qa.pages.CartPage;
import com.crm.qa.pages.HomePage2;
import com.crm.qa.pages.LoginPage2;

import Utils.Utility;
import browser.Base;
	
public class HomePageTestAakash extends Base{
    WebDriver driver;
    LoginPage2 loginpage;
	HomePage2 homepage;
	SoftAssert soft;
	CartPage cartpage;
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
		cartpage =new CartPage();
	}
	
	@BeforeMethod
	public void SetUp() throws IOException{
		
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
	
	@Test (priority =1)
	public void homepageTitleTest()
	{   testId= 10;
		String title =homepage.validateHomePageTitle();
		soft.assertEquals("Let's Shop", title);
		soft.assertAll();
	}
	
	@Test (priority =2)
	public void homePageURL() {
		testId= 11;
		String url = homepage.validateHomePageURL();
		soft.assertEquals(url,"https://rahulshettyacademy.com/client/auth/login");
		soft.assertAll();
}
	
	@Test (priority =3)
	public void logoTest() {
		testId= 12;
		boolean flag = homepage.validateLogo();
		soft.assertTrue(flag);
		soft.assertAll();
}
	
	@Test (priority =4)
	public void addToCartCart()  {
		testId= 13;
		homepage.clickAddRToCart();
	}
	@Test (priority =5)
	public void clickCart() {
		testId= 14;
		homepage.clickCart();
	}
	
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
		//cartpage = null;
	}
	
	@AfterTest
  	public void CloseBrowser() {
  		driver.quit();
  		driver = null;
  		System.gc();
  	}
	
}

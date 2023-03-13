package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.TestUtil;

public class TestBase {

	 public WebDriver driver;
	 public static Properties prop;
		
	public WebDriver initializeDriver() throws IOException
	   {
		   Properties prop = new Properties();
		   //Add Path of GlobalData. Properties 
		   FileInputStream fis = new FileInputStream ("C:\\Users\\jumal\\eclipse-workspace\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
				   
		   prop.load(fis);
		   String browserName = prop.getProperty("browser");
		   if (browserName.equalsIgnoreCase("chrome"))
		   { 
			driver = new ChromeDriver();
		   }
		   else if (browserName.equalsIgnoreCase("firefox"))
		   {
			   driver = new FirefoxDriver();
		   }
		   else if (browserName.equalsIgnoreCase("edge"))
				   
				   {
				    driver = new EdgeDriver();

				   }
		 // else if (browserName.equalsIgnoreCase("opera"))
			//  {
			  //      driver = new OperaDriver();
				// }
		    driver.manage().deleteAllCookies();
		  
		    driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get(prop.getProperty("url"));
          return driver;
	
}
}
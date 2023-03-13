package com.crm.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage2 {

	@FindBy (xpath = "//input[@id='userEmail']")         //
	private WebElement userName;
	
    @FindBy (xpath = "//input[@type='password']")        //
	private WebElement password;
    
    @FindBy (xpath = "//input[@name='login']")        //
   	private WebElement login;
    
    //If their is Logo Then
 //  @FindBy (xpath = "")        //
   //private WebElement logo;
    
  //If CheckBox then DO THIS
    //  @FindBy (xpath = "")        //
    //private WebElement contacts;

    
    private WebDriver driver;
	private  Actions act; 
	private Select s; 
	private WebDriverWait wait;	
		
	
	public LoginPage2(WebDriver driver) {
    PageFactory.initElements(driver, this);
	this.driver = driver;
	wait= new WebDriverWait(driver, Duration.ofSeconds(40));
	act = new Actions (driver);
	} 
	
	public String validateLoginePageTitle() {
		
		return driver.getTitle();
	}
	public String validateLoginPageURL() {
		return driver.getCurrentUrl();
	}
	public String validateLoginbtText()   {
        
		String text=login.getAttribute("value");
		System.out.println(text);
		return text;
	}
	
	
	//public boolean validateLogo() {    // If Logo then do this
		//return logo.isDisplayed();
	//}
	
	//If CheckBox then DO THIS
	//public void selectContactsByName(String name)
	//{
	//contacts.click;
   // }
	
	
	public void sendUserName(String user) {
		wait.until(ExpectedConditions.visibilityOf(userName));
		userName.sendKeys(user);
		
	}
	public void sendpassword(String paswrd) {
		wait.until(ExpectedConditions.visibilityOf(password));
		password.sendKeys(paswrd);
	}
	public HomePage2 Clicklogin() {
		
		wait.until(ExpectedConditions.visibilityOf(login));
    	login.click();
    	return new HomePage2(driver);
    }
	
	
}

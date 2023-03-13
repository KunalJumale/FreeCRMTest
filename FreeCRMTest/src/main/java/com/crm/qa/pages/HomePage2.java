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


public class HomePage2  {

	@FindBy (xpath = "(//button[@class='btn w-10 rounded'])[2]")         
	private WebElement addTocart;
	
	@FindBy (xpath = "(//button[@class='btn btn-custom'])[3]")         
	private WebElement cart;
	
	@FindBy (xpath = "//h3[text()='Automation']")         
	private WebElement logo;
	
	@FindBy (xpath = "(//button[@style='margin-top: -10px;'])[3]")         
	private WebElement logout;
	
	
	private WebDriver driver;
	private  Actions act; 
	private Select s; 
	private WebDriverWait wait;
		
		
	
	public HomePage2(WebDriver driver) {
    PageFactory.initElements(driver, this);
	this.driver = driver;
	act = new Actions (driver);
	wait= new WebDriverWait(driver, Duration.ofSeconds(40));
	} 
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public String validateHomePageURL() {
		return driver.getCurrentUrl();
	}
	
	public boolean validateLogo() {    // If Logo then do this
			return logo.isDisplayed();
		}
	
	public void clickAddRToCart() {
		wait.until(ExpectedConditions.visibilityOf(addTocart));		
		addTocart.click();}
	public void clickCart() {
		wait.until(ExpectedConditions.visibilityOf(cart));
	    cart.click();
}
	public void clickLogout() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(logout));	
		Thread.sleep(2000);
		logout.click();
		
	}
}

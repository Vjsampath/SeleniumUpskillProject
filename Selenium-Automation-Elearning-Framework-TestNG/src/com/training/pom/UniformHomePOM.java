package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformHomePOM {
	private WebDriver driver; 
	
	public UniformHomePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
//@FindBy(cssSelector(".caret"))
	@FindBy(xpath= "//li[@class='dropdown myaccount']//span[@class='caret']")
	private WebElement myAccountDropdown;
	
	@FindBy(xpath ="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Login')]")
	private WebElement login; 
	
	@FindBy(xpath ="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Register')]")
	private WebElement register; 
	
	
	
	
	
	
	public void myAccountDropdownBtn() {
		this.myAccountDropdown.click(); 
	}
	
	public void clickmyAccountLogin() {
		this.login.click();
	}
	
	public void clickmyAccountRegister() {
		this.register.click();;
	}
	
	
	
}

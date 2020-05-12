package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniformMyAccountPagePOM {
	private WebDriver driver; 
	
	public UniformMyAccountPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
//@FindBy(cssSelector(".caret"))
	@FindBy(xpath= "//li[@class='dropdown myaccount']//span[@class='caret']")
	private WebElement myAccountDropdown;
	
	@FindBy(xpath ="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'Logout')]")
	private WebElement Logout; 
	
	@FindBy(xpath ="//ul[@class='dropdown-menu dropdown-menu-right myaccount-menu']//a[contains(text(),'My Account')]")
	private WebElement MyAccount; 
	
	
	
	
	
	
	public void myAccountDropdownBtn() {
		this.myAccountDropdown.click(); 
	}
	
	public void clickmyAccountLogout() {
		this.Logout.click();
	}
	
	public void clickmyAccountMyAccount() {
		this.MyAccount.click();;
	}
	
	
	
}

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
	
	
	@FindBy(xpath ="//div[@class='banner hb-animate-element right-to-left']//img[@class='img-responsive']")
	private WebElement shopUniform; 
	
	@FindBy(xpath ="//div[@class='prod_detail_container']//a[contains(text(),'Regular T-Shirt (Maroon)')]")
	private WebElement regTshirtsMaroon; 
	
	public void myAccountDropdownBtn() {
		this.myAccountDropdown.click(); 
	}
	
	public void clickmyAccountLogin() {
		this.login.click();
	}
	
	public void clickmyAccountRegister() {
		this.register.click();
	}
	
	public void clickShopUniform() {
		this.shopUniform.click();
	}
	
	public void clickRegTshirtMaroon() {
		this.regTshirtsMaroon.click();
	}
	
	public WebElement regTshritsReturn()
	{
		return (this.regTshirtsMaroon);
	}
}

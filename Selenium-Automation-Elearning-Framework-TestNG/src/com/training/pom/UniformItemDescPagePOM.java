package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UniformItemDescPagePOM {
	private WebDriver driver; 
	
	public UniformItemDescPagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath ="//select[@id='input-option386']")
	private WebElement chestSize; 
	
	@FindBy(id="button-cart")
	private WebElement addToCart;
	
	@FindBy(id="input-quantity")
	private WebElement sendQty; 
	
	@FindBy(xpath="//div[@class='alert alert-success']")
	private WebElement succMsg;
	
	@FindBy(xpath="//div[@id='cart']//button[@class='btn btn-inverse btn-block btn-lg dropdown-toggle']")
	private WebElement cartIcon;
	
	@FindBy(xpath="//strong[contains(text(),'View Cart')]")
	private WebElement viewCartbtn;
	
	@FindBy(xpath="//a[@class='btn btn-primary' and contains(text(),'Checkout')]")
	private WebElement shoppingCartCheckOutbtn;
	
	public void clickShoppingCartCheckOutBtn() {
		this.shoppingCartCheckOutbtn.click();
	}
	
	public void clickViewCartBtn() {
		this.viewCartbtn.click();
	}
	
	public void clickCartIcon() {
		this.cartIcon.click();
	}
	
	public String alertSuccMsg() {
		String alertSuccessMsg = this.succMsg.getText();
		return(alertSuccessMsg);
	}
	
	public void selchestSize(String chestSize) {
		this.chestSize.click();
		Select chestSizeSel = new Select(this.chestSize);
		chestSizeSel.selectByVisibleText(chestSize);
	}
	
	public void sendQty(String sendQty) {
		this.sendQty.clear(); 
		this.sendQty.sendKeys(sendQty); 
	}
	
	public void clickAddToCartBtn() {
		this.addToCart.click(); 
	}
}
